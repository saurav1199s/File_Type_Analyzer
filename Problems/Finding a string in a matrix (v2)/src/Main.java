import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static boolean ifEquals(char[][] pattern1, char[][] pattern2, int a, int b, int x, int y) {
        for (int i = x; i < y; ++i) {
            if (pattern1[i - x][a] != pattern2[i][b]) {
                return false;
            }
        }
        return true;
    }

    public static ArrayList<Integer> findPrefixFuntion(char[][] pattern) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        int i = 1;
        int len = 0;
        while (i < pattern[0].length) {
            if (ifEquals(pattern, pattern, i, len, 0, pattern.length)) {
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

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        char[][] pattern = new char[n][m];
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            pattern[i] = scanner.nextLine().toCharArray();
        }

        n = scanner.nextInt();
        m = scanner.nextInt();
        char[][] text = new char[n][m];
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            text[i] = scanner.nextLine().toCharArray();
        }

        List<Integer> prefixFunction = findPrefixFuntion(pattern);

        int ans = 0;
        int i = 0;
        int j = 0;
        int k;
        n = text.length - pattern.length + 1;
        while (i < n) {
            k = 0;
            j = 0;
            while (j < m) {
                if (ifEquals(pattern, text, k, j, i, i + pattern.length)) {
                    ++j;
                    ++k;
                    if (k == pattern[0].length) {
                        ++ans;
                        k = prefixFunction.get(k - 1);
                    }
                } else {
                    if (k == 0) {
                        ++j;
                    } else {
                        k = prefixFunction.get(k - 1);
                    }
                }
            }
            ++i;
        }
        System.out.println(ans);
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