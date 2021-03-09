package part1.lesson03.task02;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    private static final int n = 2;

    public static void main(String[] args) throws NegativeNumberException {
        List<Integer> randomNumbers = getRandomNumbers();
        printAllSquareRootable(randomNumbers);
    }

    private static List<Integer> getRandomNumbers() throws NegativeNumberException {
        List<Integer> nRandomNumbers = new ArrayList<>(n);
        Random rand = new Random();
        int randomInteger;
        for (int i = 0; i < n; i++) {
            randomInteger = rand.nextInt();
            if (randomInteger < 0) {
                throw new NegativeNumberException();
            } else {
                nRandomNumbers.add(randomInteger);
            }
        }
        return nRandomNumbers;
    }

    private static void printAllSquareRootable(List<Integer> randomNumbers) {
        for (int k : randomNumbers) {
            double q = Math.sqrt(k);
            if (Math.pow((int) q, 2) == k) {
                System.out.println(k);
            }
        }
    }
}
