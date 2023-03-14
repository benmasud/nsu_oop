package r.masud;
import java.util.Arrays;

public class parallelstream {
    public static boolean containsNonPrime(int[] nums) {
        return Arrays.stream(nums)
                .parallel()
                .anyMatch(n -> isNonPrime(n));
    }

    private static boolean isNonPrime(int num) {
        if (num <= 1) {
            return true;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return true;
            }
        }
        return false;
    }
}
