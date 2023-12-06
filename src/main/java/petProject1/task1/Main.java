package petProject1.task1;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        Random rnd = new Random();

        List<Integer> nums = rnd.ints(1, 1000000)
                .distinct()
                .limit(1000)
                .boxed()
                .collect(Collectors.toList());
        System.out.println(nums);                                                               // - 1.

        System.out.println(nums.stream().

                max(Integer::compareTo).

                orElseThrow());                                                                 // - 1.1
        System.out.println(nums.stream().

                min(Integer::compareTo).

                orElseThrow());                                                                // - 1.1.1

        int sum = nums.stream()
                .filter(x -> x > 500000)
                .mapToInt(x -> x * 5 - 150)
                .sum();
        System.out.println(sum);                                                                // - 1.2

        nums.sort(Integer::compareTo);
        System.out.println(nums);

        long c = nums.stream()
                .map(x -> x ^ 2)
                .filter(x -> x < 100_000)
                .count();
        System.out.println(c);                                                                  // - 1.3
    }
}
