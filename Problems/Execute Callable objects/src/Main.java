import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


class FutureUtils {

    public static int executeCallableObjects(List<Future<Callable<Integer>>> items) {
        int result = 0;
        for (int i = items.size() - 1; i >= 0; --i) {
            try {
                result += items.get(i).get().call();
            } catch (Exception e) {

            }
        }
        return result;
    }

}