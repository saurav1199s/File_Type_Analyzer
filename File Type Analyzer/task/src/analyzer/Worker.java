package analyzer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Worker implements Callable<String> {
    public static ArrayList<Integer> clacPrefixList(String pattern) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        int i = 1;
        int len = 0;
        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                ++len;
                ++i;
                list.add(len);
            } else {
                if (len == 0) {
                    list.add(0);
                    ++i;
                } else {
                    len = list.get(len - 1);
                }
            }
        }
        return list;
    }

    public static boolean KMP(String pattern, String text) {
        List<Integer> prefixList = clacPrefixList(pattern);

        int i = 0;
        int j = 0;
        while (i < text.length()) {
            if (pattern.charAt(j) == text.charAt(i)) {
                ++j;
                ++i;
                if (j == pattern.length()) {
                    return true;
                }
            } else {
                if (j == 0) {
                    ++i;
                } else {
                    j = prefixList.get(j - 1);
                }
            }
        }
        return false;
    }

    public static boolean RabinKarp(String pattern, String text, Pairs<Long, Long> matchValue) {
        if (text.length() < pattern.length()) {
            return false;
        }

        long val = text.charAt(0) + 1;
        Pairs<Long, Long> pair = new Pairs<>(val, val);
        long p = 1;
        long q = 1;

        for (int i = 1; i < pattern.length(); ++i) {
            p = (p * Item.P) % Item.MOD;
            q = (q * Item.Q) % Item.MOD;
            val = text.charAt(i) + 1;
            pair.a = (pair.a + val * p) % Item.MOD;
            pair.b = (pair.b + val * q) % Item.MOD;
        }

        if ((long)matchValue.a == pair.a && (long)matchValue.b == pair.b) {
            System.out.println(true);
            return true;
        }

        for (int i = pattern.length(); i < text.length(); ++i) {

            val = text.charAt(i - pattern.length()) + 1;
            pair.a = (((pair.a + Item.MOD - val) % Item.MOD) * Item.PModInv) % Item.MOD;
            pair.b = (((pair.b + Item.MOD - val) % Item.MOD) * Item.QModInv) % Item.MOD;

            val = text.charAt(i) + 1;
            pair.a = (pair.a + val * p) % Item.MOD;
            pair.b = (pair.b + val * q) % Item.MOD;

            if ((long)matchValue.a == pair.a && (long)matchValue.b == pair.b) {
                System.out.println(true);
                return true;
            }
        }


        return false;
    }

    File file;
    List<Item> patterns;

    Worker (File file, List<Item> patterns) {
        this.file = file;
        this.patterns = new ArrayList<>(patterns);
    }

    @Override
    public String call() {
        try {
            FileInputStream fis = new FileInputStream(this.file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            String text = new String(bis.readAllBytes());

            for (int i = 0; i < patterns.size(); ++i) {
                Item elem = patterns.get(i);
                if (RabinKarp(elem.pattern, text, elem.hash)) {
                    return String.format("%s: %s", this.file.getName(), elem.tag);
                }
            }
            return String.format("%s: Unknown file type", file.getName());
        } catch (Exception e) {

        }
        return "";
    }
}
