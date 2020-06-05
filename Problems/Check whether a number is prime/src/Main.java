import java.util.Scanner;
import java.util.concurrent.*;

public class Main {
//    public static final int[] prime = new int[Integer.MAX_VALUE];
//
//    public static void findPrime() {
//
//    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExecutorService executor = Executors.newFixedThreadPool(4);

        while (scanner.hasNext()) {
            int number = scanner.nextInt();
            executor.submit(() -> {
               if (number == 2) {
                   System.out.println("2");
                   return;
               }
               if (number%2 == 0 || number == 1) {
                   return;
               }
               int val = (int) Math.sqrt(number);
               for (int i = 3; i <= val; i += 2) {
                   if (number % i == 0) {
                       return;
                   }
               }
               System.out.println(number);
            });
        }

        executor.shutdown();
    }
}

class PrintIfPrimeTask implements Runnable {
    private final int number;

    public PrintIfPrimeTask(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        // write code of task here
    }
}