import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class TestClass {
    public static void main(String[] args) {
        List<Integer> l = Arrays.asList(new Integer[]{2, 1, 6, 9, 7});
        Queue<Integer> queue = new PriorityQueue<>();

        queue.addAll(l);
        System.out.println("Size:" + queue.size());
        for (int i = 0; i < l.size(); i++) {
            System.out.println(queue.poll());
        }
    }
}
