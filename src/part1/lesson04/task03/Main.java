package part1.lesson04.task03;


import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        MathBox<Double> mathBox = new MathBox<>(new Double[]{1d, 2d, 3d, 4d});
        MathBox<Integer> mathBox1 = new MathBox<>(new Integer[]{1, 2, 3, 4});
        MathBox<Float> mathBox2 = new MathBox<>(new Float[]{1f, 2f, 3f, 4f});
        System.out.println(mathBox.summator());
        mathBox.splitter(2);
        Map<String, MathBox> mathBoxMap = new HashMap<>();
        mathBoxMap.put("firstmap", mathBox);
        mathBoxMap.put("secondmap", mathBox1);
        mathBoxMap.put("thirdmap", mathBox2);

        System.out.println(mathBoxMap.get("thirdmap"));

    }

}
