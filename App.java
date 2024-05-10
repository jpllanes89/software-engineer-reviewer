import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

  private static final String MESSAGE_PATH_INTRO = "./messages/intro-en.txt";
  private static final Scanner SCANNER = new Scanner(System.in);

  public static void main(String[] args) {

    showIntro();
  }

  private static void showIntro() {

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

      String input = SCANNER.nextLine();
      System.out.println(String.format("You have chosen: %s", input));
      input = SCANNER.nextLine();

    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }
}
