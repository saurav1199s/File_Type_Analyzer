package analyzer;

public class Item {
    public static final long P = 31;
    public static final long Q = 53;
    public static final long MOD = 1_000_000_007;
    public static final long PModInv;
    public static final long QModInv;

    public static long powMod(long base, long exp) {
        long res = 1;
        while (exp > 0) {
            if (exp % 2 != 0) {
                res = (res * base) % MOD;
            }
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return res;
    }

    static {
        PModInv = powMod(P, MOD - 2);
        QModInv = powMod(Q, MOD - 2);
    }

    int priority;
    String pattern;
    String tag;
    Pairs<Long, Long> hash;

    Item(int priority, String pattern, String tag) {
        this.priority = priority;
        this.pattern = pattern;
        this.tag = tag;
        calcHash();
    }

    private void calcHash() {
        long p = 1;
        long q = 1;
        long val = (long)pattern.charAt(0) + 1;
        hash = new Pairs<>(val, val);
        for (int i = 1; i < pattern.length(); ++i) {
            p = (p * P) % MOD;
            q = (q * Q) % MOD;
            val = pattern.charAt(i) + 1;
            hash.a = (hash.a + p * val) % MOD;
            hash.b = (hash.b + q * val) % MOD;
        }
    }
}
