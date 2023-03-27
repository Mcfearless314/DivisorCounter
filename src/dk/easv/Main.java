package dk.easv;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.ToIntFunction;

public class Main {

    public static void main(String[] args) throws Exception {

        // Fetches the start time of the method.
        Instant start = Instant.now();

        int a = 100_000;
        int b = 0;
        int c = 0;
        int d = 0;
        if (a%4==0){
            b = a - 3 * (a/4);
            c = a - 2 * (a/4);
            d = a - 1 * (a/4);
        }

        // Invokes the divisor counter
        ExecutorService es = Executors.newFixedThreadPool(4);
        DivisorCounter task1 = new DivisorCounter(1, b);
        DivisorCounter task2 = new DivisorCounter(b, c);
        DivisorCounter task3 = new DivisorCounter(c, d);
        DivisorCounter task4 = new DivisorCounter(d, a);
        System.out.println("Looking for the best result...");
        Future e = es.submit((Callable<Result>) task1);
        Future f = es.submit((Callable<Result>) task2);
        Future g = es.submit((Callable<Result>) task3);
        Future h = es.submit((Callable<Result>) task4);


        // Find the highest result

        Result result1 = (Result) e.get();
        Result result2 = (Result) f.get();
        Result result3 = (Result) g.get();
        Result result4 = (Result) h.get();

        List<Result> highestResult = Arrays.asList(result1, result2, result3, result4);

        highestResult.subList(0,3).sort(Comparator.comparingInt());

        Collections.sort();



        // Fetches the end time of the method.
        Instant end = Instant.now();
        System.out.println( + " maxResult " + result.getDivisorCounter() + " divisors!");
        System.out.println("Duration: " + Duration.between(start, end).toMillis() + " ms");
        es.shutdown();
    }
}
