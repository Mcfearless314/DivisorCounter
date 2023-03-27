package dk.easv;


import java.util.Vector;
import java.util.concurrent.Callable;

public class DivisorCounter implements Runnable,Callable<Result> {

    private final static Vector<Result> results = new Vector<>();
    private final int minimum;
    private final int maximum;

    public DivisorCounter(int minimum, int maximum)
    {
        this.minimum = minimum;
        this.maximum = maximum;
    }


    public static Result getBestResult() {
        Result result = new Result(0, 0);
        for(Result r : results) {
            if (r.getDivisorCounter() > result.getDivisorCounter()) {
                result = r;
            }
        }
        return result;
    }

    @Override
    public Result call() {
        Result result = new Result(0, 0);
        for(int i = minimum; i <= maximum; i++) {
            int counter = 0;
            for(int j = 1; j<=i; j++) {
                if(i % j == 0) {
                    counter++;
                }
            }
            if(counter > result.getDivisorCounter()) {
                result = new Result(i, counter);
            }
        }

        results.add(result);
        return result;
    }

    @Override
    public void run() {
        call();
    }
}
