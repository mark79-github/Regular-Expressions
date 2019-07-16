import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PostOffice_v3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split("\\|");
        String firstPart = input[0];
        String secondPart = input[1];
        String thirdPart = input[2];

        List<String> words = Arrays.stream(thirdPart.split("\\s+")).collect(Collectors.toList());
        String capitalLetters = "";
        HashMap<Integer, Integer> ascciMap = new HashMap<>();

        String regex = "([#]|[\\$]|[%]|[\\*]|[&])[A-Z]+([#]|[\\$]|[%]|[\\*]|[&])";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(firstPart);

        if (matcher.find()) {
            capitalLetters = matcher.group(0);
            if (capitalLetters.charAt(0) == capitalLetters.charAt(capitalLetters.length() - 1)) {
                capitalLetters = capitalLetters.substring(1, capitalLetters.length() - 1);
            }
        }

        if (capitalLetters.length() > 0) {
            for (int i = 0; i < capitalLetters.length(); i++) {
                ascciMap.putIfAbsent((int) capitalLetters.charAt(i), 0);
            }
        }
        if (ascciMap.size() > 0) {
            for (int i = 0; i < capitalLetters.length(); i++) {
                regex = String.format("(?<char>%d):(?<count>[0-1][1-9]|20|10)", (int) capitalLetters.charAt(i));//, (int) capitalLetters.charAt(i));
                pattern = Pattern.compile(regex);
                matcher = pattern.matcher(secondPart);
                while (matcher.find()) {
                    int symbol = Integer.parseInt(matcher.group("char"));
                    int count = Integer.parseInt(matcher.group("count"));
                    if (ascciMap.containsKey(symbol)) {
                        ascciMap.put(symbol, count + 1);
                    }
                }
            }
        }

        if (capitalLetters.length() > 0 && ascciMap.size() > 0) {
            for (int i = 0; i < capitalLetters.length(); i++) {
                for (String word : words) {
                    if (capitalLetters.charAt(i) == word.charAt(0)) {
                        if (ascciMap.get((int) capitalLetters.charAt(i)) == word.length()) {
                            System.out.println(word);
                        }
                    }
                }
            }
        }
    }
}
