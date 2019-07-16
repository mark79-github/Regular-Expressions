package Exercises;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StarEnigma {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        ArrayList<String> attackedPlanets = new ArrayList<>();
        ArrayList<String> destroyedPlanets = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String encryptedMsg = scanner.nextLine();
            Pattern pattern = Pattern.compile("[star]", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(encryptedMsg);
            int matcherCount = 0;
            while (matcher.find()) {
                matcherCount++;
            }
            StringBuilder decryptedMsg = new StringBuilder();
            for (int j = 0; j < encryptedMsg.length(); j++) {
                decryptedMsg.append((char) (encryptedMsg.charAt(j) - matcherCount));
            }

            String regex = ".*?@(?<planet>[a-zA-Z]+)[^@\\-!>]*:(?<population>\\d+)[^@\\-!>]*!(?<action>[AD])![^@\\-!>]*->(?<soldiers>\\d+)";
//            String myregex = "^[^\\@\\-\\!\\:\\>]*\\@(?<planet>[A-Za-z]+)[^\\@\\-\\!\\:\\>]*\\:(?<population>\\d+)[^\\@\\-\\!\\:\\>]*\\!(?<action>[AD])\\!\\-\\>(?<soldiers>\\d+)[^\\@\\-\\!\\:\\>]*$";
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(decryptedMsg);

            while (matcher.find()) {
                switch (matcher.group("action").charAt(0)) {
                    case 'A':
                        attackedPlanets.add(matcher.group("planet"));
                        break;
                    case 'D':
                        destroyedPlanets.add(matcher.group("planet"));
                        break;
                }
            }

        }

        attackedPlanets.sort(String::compareTo);
        destroyedPlanets.sort(String::compareTo);
        System.out.printf("Attacked planets: %d%n", attackedPlanets.size());
        for (String attackedPlanet : attackedPlanets) {
            System.out.printf("-> %s%n", attackedPlanet);
        }
        System.out.printf("Destroyed planets: %d%n", destroyedPlanets.size());
        for (String destroyedPlanet : destroyedPlanets) {
            System.out.printf("-> %s%n", destroyedPlanet);
        }

    }
}
