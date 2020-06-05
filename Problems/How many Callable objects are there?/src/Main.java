import java.util.concurrent.*;


class FutureUtils {

    public static int determineCallableDepth(Callable callable) throws Exception {
        Object temp;
        int count = 1;

        temp = callable.call();

        while (true) {
            if (temp instanceof Callable) {
                ++count;
                temp = ((Callable) temp).call();
            } else {
                return count;
            }
        }
    }

}