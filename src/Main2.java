import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 示例
 * 适用于将需要获取不同任务的执行结果的整合，而不必等待上一个任务执行完成才开始执行下一个任务
 */
public class Main2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Task1 doing ...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        });

        CompletableFuture<Integer> completableFuture2 = completableFuture1.thenCombine(
                CompletableFuture.supplyAsync(() -> {
                    System.out.println("Task2 doing ...");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return 2000;
                }),
                (result1, result2) -> result1 + result2);

        System.out.println(completableFuture2.get());
    }
}
