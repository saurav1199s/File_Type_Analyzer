import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static ArrayList<Integer> findPrefixFunction(String str) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        for (int i = 1; i < str.length(); ++i) {
            int j = list.get(i - 1);
            while (j > 0 && str.charAt(i) != str.charAt(j)) {
                j = list.get(j - 1);
            }
            if (str.charAt(j) == str.charAt(i)) {
                list.add(j + 1);
            } else {
                list.add(0);
            }
        }
        
        return list;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int n = scanner.nextInt();
        scanner.nextLine();
        for (String arr : scanner.nextLine().split("\\s+")) {
            List<Integer> list = findPrefixFunction(arr + str);
            int val = Integer.MIN_VALUE;
            for (Integer i : list) {
                val = Math.max(i, val);
            }
            System.out.print(str.length() + 1 - val + " ");
        }
    }   
}
