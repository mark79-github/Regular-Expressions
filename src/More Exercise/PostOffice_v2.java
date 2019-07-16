import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostOffice_v2 {

    private static void finalRegex(String input, char capitalLetter, int count) {
        String regex = String.format("(?<=\\s|^)(?<word>[%c][\\S]{%d})(?=\\s|$)", capitalLetter, count);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            System.out.println(matcher.group("word"));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split("\\|");

        String regex = "([#]|[$]|[%]|[\\*]|[&])[A-Z]+([#]|[$]|[%]|[\\*]|[&])";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input[0]);

        if (matcher.find()) {
            if (matcher.group(0).charAt(0) == matcher.group(0).charAt(matcher.group(0).length() - 1)) {
                String upperCaseWord = matcher.group(0).substring(1, matcher.group(0).length() - 1);
                for (int i = 0; i < upperCaseWord.length(); i++) {
                    int ascciSymbol = upperCaseWord.charAt(i);
                    String secondRegex = String.format("(?<char>[%d][%d]):(?<count>[\\d][\\d])", ascciSymbol / 10, ascciSymbol % 10);
                    Pattern secondPattern = Pattern.compile(secondRegex);
                    Matcher secondMatcher = secondPattern.matcher(input[1]);
                    if (secondMatcher.find()){
                        finalRegex(input[2], (char) ascciSymbol, Integer.parseInt(secondMatcher.group("count")));
                    }
                }

            }
        }


    }
}
