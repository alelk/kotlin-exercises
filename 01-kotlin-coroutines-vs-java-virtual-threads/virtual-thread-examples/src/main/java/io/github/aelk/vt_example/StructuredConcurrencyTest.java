package io.github.aelk.vt_example;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;

public class StructuredConcurrencyTest {

    public static void main(String[] args) {

        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            StructuredTaskScope.Subtask<Integer> f1 = scope.fork(() -> 1);
            StructuredTaskScope.Subtask<Integer> f2 = scope.fork(() -> 2);
            scope.join();
            scope.throwIfFailed();
            System.out.println("1 + 2 = " + (f1.get() + f2.get()));
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
