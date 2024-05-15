import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class App {

  private static final String FILENAME = "Question";
  private static final String FILE_TYPE = ".java";
  private static final String MESSAGE_PATH_INTRO = "./messages/intro-en.txt";
  private static final String QUESTIONS_MAPPING_PATH = "./mappings/questions-mapping.csv";
  private static final String LOCAL_DATE_TIME_FORMAT = "yyyyMMddHHmm";
  private static final Scanner SCANNER = new Scanner(System.in);
  private static final String QUESTION_PLACEHOLDER = "REPLACE_WITH_QUESTIONS";

  public static void main(String[] args) {

    clearScreen();
    List<QuestionMapping> questionMappings = readQuestionMappingFile();

    List<String> availableIds =
        questionMappings.stream().map(QuestionMapping::getId).collect(Collectors.toList());

    String chosenId = showIntroAndOptions(availableIds);

    generateQuestionnare(chosenId, questionMappings);
  }

  private static String showIntroAndOptions(List<String> availableIds) {

    String chosenId = "";

    List<String> defaultIds = Arrays.asList(new String[] {"A", "B", "C", "X"});

    File introMessageFile = new File(MESSAGE_PATH_INTRO);

    try (FileInputStream is = new FileInputStream(introMessageFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
      String line = "";
      while ((line = br.readLine()) != null) {
        System.out.println(line);
      }

      boolean optionChosen = false;

      String input = "";
      while (!optionChosen) {
        input = SCANNER.nextLine();

        if (availableIds.stream().anyMatch(input::equalsIgnoreCase)
            || defaultIds.stream().anyMatch(input::equalsIgnoreCase)) {
          if ("x".equalsIgnoreCase(input)) {

            System.out.println(String.format("You have chosen %s to exit", input));
            System.exit(1);
          }

          chosenId = input.toUpperCase();
          optionChosen = true;
          System.out.println(String.format("You have chosen: %s", input));
        } else {

          System.out.println(
              String.format("You have chosen an invalid option. please choose again: %s", input));
        }
      }

      input = SCANNER.nextLine();

    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
    return chosenId;
  }

  private static List<QuestionMapping> readQuestionMappingFile() {

    List<QuestionMapping> questionMappings = new ArrayList<>();
    File file = new File(QUESTIONS_MAPPING_PATH);

    if (!file.exists()) {

      System.err.println(QUESTIONS_MAPPING_PATH + " does not exist. Application will exit");
      System.exit(1);
    }

    try (FileInputStream fis = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis))) {

      String line = "";
      // skip file header
      br.readLine();
      while ((line = br.readLine()) != null) {

        questionMappings.add(parseQuestionMappingLine(line));
      }

    } catch (IOException e) {
      System.err.println(e.getMessage());
    }

    return questionMappings;
  }

  private static QuestionMapping parseQuestionMappingLine(String line) {

    String[] split = line.split(",");

    QuestionMapping mapping =
        new QuestionMapping.QuestionMappingBuilder()
            .setId(split[0])
            .setJavaFile(split[1])
            .setQuestionFile(split[2])
            .setType(split[3])
            .setCategory(split[4])
            .build();

    return mapping;
  }

  private static void generateQuestionnare(
      String chosenId, List<QuestionMapping> questionMappings) {

    String directoryName = generateDirectory();
    System.out.println("created directory to contain the quetions: " + directoryName);

    Map<String, QuestionMapping> questionMappingsById =
        questionMappings.stream().collect(Collectors.toMap(QuestionMapping::getId, qm -> qm));

    QuestionMapping questionMapping = questionMappingsById.get(chosenId);

    if (questionMapping == null) {
      //  generate random questions
      return;
    }

    generateQuestionFile(questionMapping, directoryName);
  }

  private static void generateQuestionFile(QuestionMapping questionMapping, String directoryName) {

    String javaFilePath = questionMapping.getJavaFile();
    String questionFilePath = questionMapping.getQuestionFile();
    validateJavaAndQuestionFiles(javaFilePath, questionFilePath);

    String[] javaFilePathSplit = javaFilePath.split("/");
    String javaFileName = javaFilePathSplit[2];
    String questionnaireFilePath = "./" + directoryName + "/" + javaFileName;

    try (FileInputStream javaFis = new FileInputStream(javaFilePath);
        BufferedReader javaBr = new BufferedReader(new InputStreamReader(javaFis));
        FileInputStream questionFis = new FileInputStream(questionFilePath);
        BufferedReader questionBr = new BufferedReader(new InputStreamReader(questionFis));
        FileWriter fw = new FileWriter(questionnaireFilePath);
        BufferedWriter bw = new BufferedWriter(fw)) {

      String javaLine = "";
      String questionLine = "";
      File questionnaireFile = new File(questionnaireFilePath);
      questionnaireFile.createNewFile();
      if (!questionnaireFile.exists()) {
        return;
      }

      while ((javaLine = javaBr.readLine()) != null) {
        if (javaLine.toLowerCase().contains(QUESTION_PLACEHOLDER.toLowerCase())) {
          // read question file
        } else {
          bw.write(javaLine);
          bw.newLine();
        }
      }
      bw.close();
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }

  private static void validateJavaAndQuestionFiles(String javaFilePath, String questionFilePath) {

    if (!fileExists(javaFilePath)) {

      System.err.println(javaFilePath + "does not exist. Application will exit");
      System.exit(1);
    }

    if (!fileExists(questionFilePath)) {

      System.err.println(questionFilePath + "does not exist. Application will exit");
      System.exit(1);
    }
  }

  private static boolean fileExists(String filePath) {

    File file = new File(filePath);
    return file.exists();
  }

  private static String generateDirectory() {

    String directoryName = generateDirectoryName();
    File directory = new File(directoryName);

    if (!directory.exists()) {

      directory.mkdir();
    }

    return directoryName;
  }

  private static String generateDirectoryName() {

    LocalDateTime localDateTime = LocalDateTime.now();
    String directoryName =
        localDateTime
            .atZone(ZoneId.of("Asia/Manila"))
            .format(DateTimeFormatter.ofPattern("yyyyMMddHHmm").withZone(ZoneOffset.of("+16:00")));
    return directoryName;
  }

  private static void createJavaQuestionnaire() {

    String filename = generateFilename();
    File file = new File(filename);
    try {

      file.createNewFile();

      if (file.exists()) {

        try (FileWriter fr = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fr)) {
          bw.write("banana");
          bw.newLine();
          bw.close();
        }
      }
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }

  private static String generateFilename() {

    LocalDateTime localDateTime = LocalDateTime.now();
    String dateStr =
        localDateTime
            .atZone(ZoneId.of("Asia/Manila"))
            .format(
                DateTimeFormatter.ofPattern(LOCAL_DATE_TIME_FORMAT)
                    .withZone(ZoneOffset.of("-16:00")));

    return FILENAME + dateStr + FILE_TYPE;
  }

  private static void clearScreen() {

    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}

class QuestionMapping {

  private String id;
  private String javaFile;
  private String questionFile;
  private String type;
  private String category;

  public QuestionMapping(QuestionMappingBuilder builder) {

    this.id = builder.id;
    this.javaFile = builder.javaFile;
    this.questionFile = builder.questionFile;
    this.type = builder.type;
    this.category = builder.category;
  }

  public String getId() {

    return this.id;
  }

  public String getJavaFile() {

    return this.javaFile;
  }

  public String getQuestionFile() {

    return this.questionFile;
  }

  public String getType() {

    return this.type;
  }

  public String getCategory() {

    return this.category;
  }

  @Override
  public String toString() {

    return "{\"QuestionMapping\" : {\"id\" : "
        + id
        + ", \"javaFile\" : "
        + javaFile
        + ", \"type\" : "
        + type
        + ", \"questionFile\" : "
        + questionFile
        + ", \"category\" : "
        + category
        + "}}";
  }

  public static class QuestionMappingBuilder {

    private String id;
    private String javaFile;
    private String questionFile;
    private String type;
    private String category;

    public QuestionMappingBuilder setId(String id) {

      this.id = id;
      return this;
    }

    public QuestionMappingBuilder setJavaFile(String javaFile) {

      this.javaFile = javaFile;
      return this;
    }

    public QuestionMappingBuilder setQuestionFile(String questionFile) {

      this.questionFile = questionFile;
      return this;
    }

    public QuestionMappingBuilder setType(String type) {

      this.type = type;
      return this;
    }

    public QuestionMappingBuilder setCategory(String category) {

      this.category = category;
      return this;
    }

    public QuestionMapping build() {

      return new QuestionMapping(this);
    }
  }
}
