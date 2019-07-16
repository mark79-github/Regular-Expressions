import java.util.*;

public class MultiplyBigNumber {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        char[] inputNum = s.nextLine().toCharArray();
        int multiplier = Integer.parseInt(s.nextLine());

        StringBuilder exitNum = new StringBuilder();

        int addNum = 0;
        for (int i = inputNum.length - 1; i >= 0; i--) {
            int currentNum = ((inputNum[i] - '0') * multiplier) + addNum;
            addNum = currentNum / 10;
            //currentNum %= 10;
            exitNum.append(currentNum % 10);
        }

//        if (addNum > 0) {
//            exitNum.append(addNum);
//        }
        exitNum.append(addNum);

        exitNum.reverse();
        while (exitNum.charAt(0) == '0' && exitNum.length() > 1) {
            exitNum.deleteCharAt(0);
        }

        System.out.println(exitNum);
    }
}