package Exercises;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NetherRealms {

    static class Demon {
        String name;
        int health;
        double damage;

        Demon(String name, int health, double damage) {
            this.name = name;
            this.health = health;
            this.damage = damage;
        }

        String getName() {
            return name;
        }

        int getHealth() {
            return health;
        }

        double getDamage() {
            return damage;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Demon> demons = new ArrayList<>();

        String[] input = scanner.nextLine().split(",\\s*");
        for (String s : input) {
            String currentDemon = s.replaceAll(" ", "");
            String regex = "[^0-9+,\\-*/.]";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(currentDemon);
            int health = 0;
            while (matcher.find()) {
                health += matcher.group(0).charAt(0);
            }

            pattern = Pattern.compile("[+-]?([0-9]+(\\.[0-9]*)*)");

            matcher = pattern.matcher(currentDemon);
            double baseDamage = 0;
            while (matcher.find()) {
                baseDamage += Double.parseDouble(matcher.group(0));
            }

            pattern = Pattern.compile("[*/]");
            matcher = pattern.matcher(currentDemon);
            while (matcher.find()) {
                switch (matcher.group(0)) {
                    case "*":
                        baseDamage *= 2;
                        break;
                    case "/":
                        baseDamage /= 2;
                        break;
                }
            }

            demons.add(new Demon(currentDemon, health, baseDamage));
        }

        demons.sort(Comparator.comparing(Demon::getName));
        for (Demon demon : demons) {
            System.out.printf("%s - %d health, %.2f damage%n", demon.getName(), demon.getHealth(), demon.getDamage());
        }

    }
}
