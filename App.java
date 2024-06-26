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
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class App {

    private static final String MESSAGE_PATH_INTRO = "./messages/intro-en.txt";
    private static final String QUESTIONS_MAPPING_PATH = "./mappings/questions-mapping.csv";
    private static final String TEMPLATE_MAPPING_PATH = "./mappings/template-mapping.csv";
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String QUESTION_PLACEHOLDER = "REPLACE_WITH_QUESTIONS";
    private static final String COMMENT_LINE_PREFIX = "        // ";

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
                            String.format(
                                    "You have chosen an invalid option. please choose again: %s",
                                    input));
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

        List<TemplateMapping> templateMappings = readingTemplateMappingFile();

        try (FileInputStream fis = new FileInputStream(file);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis))) {

            String line = "";
            // skip file header
            br.readLine();
            while ((line = br.readLine()) != null) {
                questionMappings.add(parseQuestionMappingLine(line, templateMappings));
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return questionMappings;
    }

    private static QuestionMapping parseQuestionMappingLine(
            String line, List<TemplateMapping> templateMappings) {

        String[] split = line.split(",");

        Map<String, List<TemplateMapping>> templateMappingsByGroup =
                templateMappings.stream().collect(Collectors.groupingBy(TemplateMapping::getGroup));

        List<TemplateMapping> availableTemplateMappings = templateMappingsByGroup.get(split[1]);

        Random random = new Random();
        int listSize = availableTemplateMappings.size();
        TemplateMapping selectedTemplateMapping =
                availableTemplateMappings.get(random.nextInt(listSize));

        QuestionMapping mapping =
                new QuestionMapping.QuestionMappingBuilder()
                        .setId(split[0])
                        .setTemplateGroup(split[1])
                        .setType(split[2])
                        .setCategory(split[3])
                        .setTemplate(selectedTemplateMapping.getTemplate())
                        .setQuestionFile(selectedTemplateMapping.getQuestionFile())
                        .build();

        return mapping;
    }

    private static List<TemplateMapping> readingTemplateMappingFile() {

        List<TemplateMapping> templateMappings = new ArrayList<>();

        File file = new File(TEMPLATE_MAPPING_PATH);
        if (!file.exists()) {

            System.err.println(TEMPLATE_MAPPING_PATH + "does not exist. Application will exit");
            System.exit(1);
        }

        try (FileInputStream fis = new FileInputStream(file);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis))) {
            String line = "";
            // skip header
            br.readLine();
            while ((line = br.readLine()) != null) {
                TemplateMapping templateMapping = parseTemplateMappingLine(line);
                templateMappings.add(templateMapping);
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return templateMappings;
    }

    private static TemplateMapping parseTemplateMappingLine(String line) {

        String[] split = line.split(",");
        TemplateMapping templateMapping =
                new TemplateMapping.TemplateMappingBuilder()
                        .setId(split[0])
                        .setTemplate(split[1])
                        .setGroup(split[2])
                        .setQuestionFile(split[3])
                        .build();
        return templateMapping;
    }

    private static List<ReviewerProperty> readReviewerPropertyFile(BufferedReader br)
            throws IOException {

        List<ReviewerProperty> reviewerProperties = new ArrayList<>();
        String line = "";
        while ((line = br.readLine()) != null) {

            ReviewerProperty prop = parseReviewerProperty(line);
            reviewerProperties.add(prop);
        }

        return reviewerProperties;
    }

    private static ReviewerProperty parseReviewerProperty(String line) {

        String[] split = line.split("=");
        String[] keySplit = split[0].split("-");

        ReviewerProperty mapping =
                new ReviewerProperty.ReviewerPropertyBuilder()
                        .setKey(split[0])
                        .setValue(split[1])
                        .setMainGroup(keySplit[0])
                        .setSubId(keySplit[1])
                        .build();

        return mapping;
    }

    private static void generateQuestionnare(
            String chosenId, List<QuestionMapping> questionMappings) {

        String directoryName = generateDirectory();
        System.out.println("created directory to contain the quetions: " + directoryName);

        Map<String, QuestionMapping> questionMappingsById =
                questionMappings.stream()
                        .collect(Collectors.toMap(QuestionMapping::getId, qm -> qm));

        QuestionMapping questionMapping = questionMappingsById.get(chosenId);

        if (questionMapping == null) {
            //  generate random questions
            return;
        }

        generateQuestionFile(questionMapping, directoryName);
    }

    private static void generateQuestionFile(
            QuestionMapping questionMapping, String directoryName) {

        String javaFilePath = questionMapping.getTemplate();
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

                    List<ReviewerProperty> reviewerProperties =
                            readReviewerPropertyFile(questionBr);
                    List<ReviewerProperty> finalList =
                            generateFinalListOfQuestions(reviewerProperties);

                    for (ReviewerProperty prop : finalList) {
                        bw.write(COMMENT_LINE_PREFIX);
                        bw.write(prop.getValue());
                        bw.newLine();
                        bw.newLine();
                    }
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

    private static List<ReviewerProperty> generateFinalListOfQuestions(
            List<ReviewerProperty> props) {

        List<ReviewerProperty> finalList = new ArrayList<>();

        Map<String, List<ReviewerProperty>> byMainGroup =
                props.stream().collect(Collectors.groupingBy(ReviewerProperty::getMainGroup));
        Random random = new Random();

        for (Map.Entry<String, List<ReviewerProperty>> e : byMainGroup.entrySet()) {

            List<ReviewerProperty> reviewerProperties = e.getValue();
            int listSize = reviewerProperties.size();
            finalList.add(reviewerProperties.get(random.nextInt(listSize)));
        }

        return finalList;
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
                        .format(
                                DateTimeFormatter.ofPattern("yyyyMMddHHmm")
                                        .withZone(ZoneOffset.of("+16:00")));
        return directoryName;
    }

    private static void clearScreen() {

        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

class QuestionMapping {

    private String id;
    private String templateGroup;
    private String questionFile;
    private String type;
    private String category;
    private String template;

    public QuestionMapping(QuestionMappingBuilder builder) {

        this.id = builder.id;
        this.templateGroup = builder.templateGroup;
        this.questionFile = builder.questionFile;
        this.type = builder.type;
        this.category = builder.category;
        this.template = builder.template;
    }

    public String getId() {

        return this.id;
    }

    public String getTemplateGroup() {

        return this.templateGroup;
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

    public String getTemplate() {

        return this.template;
    }

    @Override
    public String toString() {

        return "{\"QuestionMapping\" : {\"id\" : "
                + id
                + ", \"templateGroup\" : "
                + templateGroup
                + ", \"type\" : "
                + type
                + ", \"questionFile\" : "
                + questionFile
                + ", \"category\" : "
                + category
                + ", \"template\" : "
                + template
                + "}}";
    }

    public static class QuestionMappingBuilder {

        private String id;
        private String templateGroup;
        private String questionFile;
        private String type;
        private String category;
        private String template;

        public QuestionMappingBuilder setId(String id) {

            this.id = id;
            return this;
        }

        public QuestionMappingBuilder setTemplateGroup(String templateGroup) {

            this.templateGroup = templateGroup;
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

        public QuestionMappingBuilder setTemplate(String template) {

            this.template = template;
            return this;
        }

        public QuestionMapping build() {

            return new QuestionMapping(this);
        }
    }
}

class ReviewerProperty {

    private String key;
    private String value;
    private String mainGroup;
    private String subId;

    public ReviewerProperty(ReviewerPropertyBuilder builder) {

        this.key = builder.key;
        this.value = builder.value;
        this.mainGroup = builder.mainGroup;
        this.subId = builder.subId;
    }

    public String getKey() {

        return this.key;
    }

    public String getValue() {

        return this.value;
    }

    public String getMainGroup() {

        return this.mainGroup;
    }

    public String getSubId() {

        return this.subId;
    }

    @Override
    public String toString() {

        return "{\"ReviewerProperty\" : {\"key\" : "
                + key
                + ", \"value\" : "
                + value
                + ", \"mainGroup\" : "
                + mainGroup
                + ", \"subId\" : "
                + subId
                + "}}";
    }

    public static class ReviewerPropertyBuilder {

        private String key;
        private String value;
        private String mainGroup;
        private String subId;

        public ReviewerPropertyBuilder setKey(String key) {

            this.key = key;
            return this;
        }

        public ReviewerPropertyBuilder setValue(String value) {

            this.value = value;
            return this;
        }

        public ReviewerPropertyBuilder setMainGroup(String mainGroup) {

            this.mainGroup = mainGroup;
            return this;
        }

        public ReviewerPropertyBuilder setSubId(String subId) {

            this.subId = subId;
            return this;
        }

        public ReviewerProperty build() {

            return new ReviewerProperty(this);
        }
    }
}

class TemplateMapping {

    private String id;
    private String template;
    private String group;
    private String questionFile;

    public TemplateMapping(TemplateMappingBuilder builder) {

        this.id = builder.id;
        this.template = builder.template;
        this.group = builder.group;
        this.questionFile = builder.questionFile;
    }

    public String getId() {

        return this.id;
    }

    public String getTemplate() {

        return this.template;
    }

    public String getGroup() {

        return this.group;
    }

    public String getQuestionFile() {

        return this.questionFile;
    }

    @Override
    public String toString() {

        return "{TemplateMapping : { \"id\" : "
                + id
                + ", \"template\" : "
                + template
                + ", \"group\" : "
                + group
                + ", \"questionFile\" : "
                + questionFile
                + " }}";
    }

    public static class TemplateMappingBuilder {

        private String id;
        private String template;
        private String group;
        private String questionFile;

        public TemplateMappingBuilder setId(String id) {

            this.id = id;
            return this;
        }

        public TemplateMappingBuilder setTemplate(String template) {

            this.template = template;
            return this;
        }

        public TemplateMappingBuilder setGroup(String group) {

            this.group = group;
            return this;
        }

        public TemplateMappingBuilder setQuestionFile(String questionFile) {

            this.questionFile = questionFile;
            return this;
        }

        public TemplateMapping build() {

            return new TemplateMapping(this);
        }
    }
}
