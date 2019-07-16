package Exercises;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Furniture_v2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Pattern pattern = Pattern.compile("(?<item>[A-Za-z\\s]+)<<(?<price>\\d+(.\\d+)?)!(?<quantity>\\d+)");
        double totalPrice = 0;
        String input = scanner.nextLine();
        System.out.println("Bought furniture:");
        while (!input.equals("Purchase")) {

            Matcher matcher = pattern.matcher(input);

            while(matcher.find()){
                System.out.println(matcher.group("item"));
                totalPrice += Double.parseDouble(matcher.group("price")) * Double.parseDouble(matcher.group("quantity"));
            }

            input = scanner.nextLine();
        }

        System.out.printf("Total money spend: %.2f", totalPrice);
    }
}
