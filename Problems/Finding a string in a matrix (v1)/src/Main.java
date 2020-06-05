import java.util.*;

public class Main {

    public static ArrayList<Integer> findPrefixFuntion(String pattern) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        int i = 1;
        int len = 0;
        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                ++len;
                list.add(len);
                ++i;
            } else {
                if (len != 0) {
                    len = list.get(len - 1);
                } else {
                    list.add(0);
                    ++i;
                }
            }
        }

        return list;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String pattern = scanner.nextLine();
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.nextLine();

        StringBuilder str = new StringBuilder("");
        while (n-- > 0) {
            str.append(scanner.nextLine());
        }

        List<Integer> prefixFunction = findPrefixFuntion(pattern);

        List<Pairs<Integer, Integer>> list = new ArrayList<>();

        int i = 0;
        int j = 0;
        while (i < str.length()) {
            if (pattern.charAt(j) == str.charAt(i)) {
                ++j;
                ++i;
                if (j == pattern.length()) {
                    list.add(new Pairs<>((i - j) / m, (i - j) % m));
                    j = prefixFunction.get(j - 1);
                }
            } else {
                if (j == 0) {
                    ++i;
                } else {
                    j = prefixFunction.get(j - 1);
                }
            }
        }

        System.out.println(list.size());

        for (Pairs p : list) {
            System.out.println(p.toString());
        }
    }
}

class Pairs<T, R> {
    T a;
    R b;
    Pairs(T a, R b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return a + " " + b;
    }
}