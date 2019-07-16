import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SantasSecretHelper {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        String input = scanner.nextLine();
        while (!input.equals("end")) {


            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < input.length(); j++) {
                sb.append((char)(input.charAt(j) - n));
            }

            String regex = "[@](?<name>[A-Za-z]+)([\\S]+)(!(?<type>[G|N])!)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(sb.toString());
            while (matcher.find()){
                if(matcher.group("type").equals("G")){
                    System.out.println(matcher.group("name"));
                }
            }

            input = scanner.nextLine();
        }
    }
}
