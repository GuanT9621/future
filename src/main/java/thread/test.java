package thread;

import java.util.List;
import java.util.concurrent.*;

public class test {
    public class sum implements Callable {
        List<Integer> nums;
        public sum(List<Integer> nums) {
            this.nums = nums;
        }
        @Override
        public Integer call() throws Exception {
            return nums.stream().mapToInt(Integer::intValue).sum();
        }
    }

    public Integer sum(List<Integer> numbers) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(4,4,10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1));
        int size = numbers.size()/4;
        List<Integer> n1 = numbers.subList(0, size);
        List<Integer> n2 = numbers.subList(size, size*2);
        List<Integer> n3 = numbers.subList(size*2, size*3);
        List<Integer> n4 = numbers.subList(size*3, size*4);
        Future<Integer> future1 = tpe.submit(new sum(n1));
        Future<Integer> future2 = tpe.submit(new sum(n2));
        Future<Integer> future3 = tpe.submit(new sum(n3));
        Future<Integer> future4 = tpe.submit(new sum(n4));
        return future1.get() + future2.get() + future3.get() + future4.get();
    }
}
