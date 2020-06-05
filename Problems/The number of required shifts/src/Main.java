import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arr = new int[scanner.nextInt()];
        scanner.nextLine();
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = scanner.nextInt();
        }
        
        int ans = 0;
        for (int i = 1; i < arr.length; ++i) {
            int val = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] < val) {
                arr[j + 1] = arr[j];
                --j;
            }
            if (j != i - 1) {
                ++ans;
            }
            arr[j + 1] = val;
        }
        
        System.out.println(ans);
    }
}
