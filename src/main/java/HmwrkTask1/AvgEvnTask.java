package HmwrkTask1;

import java.util.*;

/*
    Напишите программу, которая использует Stream API для обработки списка чисел.
    Программа должна вывести на экран среднее значение всех четных чисел в списке.
 */
public class AvgEvnTask {
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        OptionalDouble avg = numbers.stream()
                .filter(n -> n % 2 == 0)
                .mapToDouble(n -> n)
                .average();

        if (avg.isPresent()) {
            System.out.println("Среднее значение четных чисел: " + avg.getAsDouble());
        } else {
            System.out.println("В списке нет четных чисел.");
        }

    }
}
