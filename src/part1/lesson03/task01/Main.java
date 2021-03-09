package part1.lesson03.task01;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws CustomException {
        Scanner scanner = new Scanner(System.in);
        int exceptionChoice;

        System.out.println("Choose exception:");
        System.out.println("1.NullPointerException");
        System.out.println("2.IndexOutOfBoundException");
        System.out.println("3.CustomException");
        exceptionChoice = scanner.nextInt();

        switch (exceptionChoice) {
            case 1:
                throwNullPointerException();
                break;
            case 2:
                throwArrayIndexOutOfBoundsException();
                break;
            default:
                throwCustomException();
        }

        System.out.println("Hello World!");
    }

    /**
     * This method simulates NullPointerException
     */
    private static void throwNullPointerException() {
        Object object = null;
        object.toString();
    }

    /**
     * This method simulates ArrayIndexOutOfBoundsException
     */
    private static void throwArrayIndexOutOfBoundsException() {
        Object[] objects = {0};
        System.out.println(objects[1]);
    }


    /**
     * @throws CustomException which is author's custom exception type
     * This method simulates CustomException
     */
    private static void throwCustomException() throws CustomException {
        throw new CustomException();
    }
}
