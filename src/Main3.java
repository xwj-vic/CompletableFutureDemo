import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main3 {

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
        completableFuture1.thenAccept(result -> System.out.println("Task1 done, result:" + result));

        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Task2 doing ...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 2000;
        });
        completableFuture2.thenAccept(result -> System.out.println("Task2 done, result:" + result));

        CompletableFuture<Integer> completableFuture3 = completableFuture1.thenCombine(completableFuture2,
                (result1, result2) -> {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return result1 + result2;
                });
        System.out.println("result:" + completableFuture3.get());
    }
}
