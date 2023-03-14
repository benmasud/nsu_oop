package r.masud;

// parallel
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class parallelBenchmark {

    @Param({"1000", "10000", "100000"})
    int arraySize;

    @Param({"1", "2", "4", "8"})
    int numThreads;

    int[] nums;

    @Setup
    public void setup() {
        // create an array of non-prime numbers
        nums = new int[arraySize];
        int k = 0;
        for (int i = 4; i < 4 * arraySize; i++) {
            if (!isPrime(i)) {
                nums[k++] = i;
            }
        }
    }

    @Benchmark
    public boolean parallelNonPrimeChecker() {
        return new parallel().new NonPrimeChecker().containsNonPrime(nums, numThreads);
    }

    // utility method to check if a number is prime
    private static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
