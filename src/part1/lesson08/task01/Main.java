package part1.lesson08.task01;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    final static private Random rand = new Random();
    final static private int RANDOM_NUMBER_BOUNDARY = 10;
    final static private int SIZE_OF_ARRAY = 5;
    final static private int THREADS_NUMBER = 3;
    final static private long WAIT_TIME = 500L;

    public static void main(String[] args) {

        int sizeOfArray = rand.nextInt(SIZE_OF_ARRAY);
        int[] randomNumbers = getRandomNumbers(sizeOfArray);
        List<Future<BigInteger>> listOfFutures = new ArrayList<>(sizeOfArray);
        System.out.println(Arrays.toString(randomNumbers));

        final ExecutorService es = Executors.newFixedThreadPool(THREADS_NUMBER);
        for (int i = 0; i < sizeOfArray; i++) {

            FactorialCallable factorialCallable = new FactorialCallable(randomNumbers[i]);
            Future<BigInteger> tmpFuture = es.submit(factorialCallable);
            listOfFutures.add(tmpFuture);

        }

        for (Future<BigInteger> futureResult : listOfFutures) {
            try {
                waitAndPrintTheResult(futureResult);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        es.shutdown();
    }

    private static void waitAndPrintTheResult(Future<BigInteger> factorialFuture) throws InterruptedException, ExecutionException {
        while (!factorialFuture.isDone()) {
            System.out.println("Waiting for the tasks to be completed");
            Thread.sleep(WAIT_TIME);
        }

        System.out.println(factorialFuture.get());
    }

    private static int[] getRandomNumbers(int sizeOfArray) {
        int[] randomNumbers = new int[sizeOfArray];

        for (int i = 0; i < sizeOfArray; i++) {
            randomNumbers[i] = rand.nextInt(RANDOM_NUMBER_BOUNDARY);
        }
        return randomNumbers;
    }
}
