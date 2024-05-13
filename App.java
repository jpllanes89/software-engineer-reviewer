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
import java.util.List;
import java.util.Scanner;

public class App {

  private static final String FILENAME = "Question";
  private static final String FILE_TYPE = ".java";
  private static final String MESSAGE_PATH_INTRO = "./messages/intro-en.txt";
  private static final String LOCAL_DATE_TIME_FORMAT = "yyyyMMddHHmm";
  private static final Scanner SCANNER = new Scanner(System.in);

  public static void main(String[] args) {

    String option = showIntroAndOptions();
    System.out.println("option: " + option);

    generateQuestionnare();
  }

  private static String showIntroAndOptions() {

    String option = "";
    File introMessageFile = new File(MESSAGE_PATH_INTRO);
    List<String> availableOptions = new ArrayList<>();

    try (FileInputStream is = new FileInputStream(introMessageFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
      String line = "";
      while ((line = br.readLine()) != null) {
        System.out.println(line);
        String[] openBracketSplit = line.split("\\[");

        for (String s : openBracketSplit) {
          if (openBracketSplit.length > 1) {
            String[] closeBracketSplit = s.split("\\]");
            if (closeBracketSplit.length > 1) {
              availableOptions.add(closeBracketSplit[0]);
            }
          }
        }
      }

      boolean optionChosen = false;

      String input = "";
      while (!optionChosen) {
        input = SCANNER.nextLine();

        if (availableOptions.stream().anyMatch(input::equalsIgnoreCase)) {
          if ("x".equalsIgnoreCase(input)) {

            System.out.println(String.format("You have chosen %s to exit", input));
            System.exit(1);
          }

          option = input.toUpperCase();
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
    return option;
  }

  private static void generateQuestionnare() {

    String directoryName = generateDirectoryName();
    File directory = new File(directoryName);
    if (!directory.exists()) {

      directory.mkdir();
    }
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

        try (FileWriter fr = new FileWriter(filename, true);
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
}
