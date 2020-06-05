package analyzer;


import java.io.*;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static final List<Item> patterns = new ArrayList<>();

    public static void main(String[] args) {
        List<Item> patterns = new ArrayList<>();
        try {
            FileReader fr = new FileReader(args[1]);
            BufferedReader br = new BufferedReader(fr);
            Scanner scanner = new Scanner(br);
            String[] str;
            while (scanner.hasNextLine()) {
                str = scanner.nextLine().split(";");
                Item item = new Item(Integer.parseInt(str[0]), str[1].substring(1, str[1].length() - 1), str[2].substring(1, str[2].length() - 1));
                patterns.add(item);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Collections.sort(patterns, (o1, o2) -> o2.priority - o1.priority);

        File dir = new File(args[0]);
        List<Callable<String>> threads = new ArrayList<>();
        for (File file : dir.listFiles()) {
            threads.add(new Worker(file, patterns));
        }

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<String>> futures = Collections.emptyList();
        try {
            futures = executorService.invokeAll(threads);
        } catch (InterruptedException e) {

        }

        executorService.shutdown();

        while (!executorService.isShutdown()) {
        }

        for (Future<String> f : futures) {
            try {
                System.out.println(f.get());
            } catch (Exception e) {
            }
        }
    }
}


