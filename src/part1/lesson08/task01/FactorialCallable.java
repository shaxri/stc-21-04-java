package part1.lesson08.task01;

import java.math.BigInteger;
import java.util.concurrent.Callable;

public class FactorialCallable implements Callable<BigInteger> {

    private final int numberForFactorial;

    public FactorialCallable(int numberForFactorial) {
        this.numberForFactorial = numberForFactorial;
    }

    @Override
    public BigInteger call() throws Exception {
        BigInteger result = BigInteger.ONE;
        for(int i = 1; i <= numberForFactorial; i++){
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}
