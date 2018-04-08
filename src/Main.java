
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 示例
 * 适用于上一个任务完成后才开始下一个任务的场景
 */
public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completedFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("task1 doing ...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task1";
        });

        CompletableFuture<String> completableFuture2 = completedFuture1.thenCompose(result ->
                CompletableFuture.supplyAsync(() -> {
                    System.out.println(result + " isDone and task2 doing ...");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "task2";
                }));

        System.out.println(completableFuture2.get());
    }
}
