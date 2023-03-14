package r.masud;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

public class SerialBenchmark {

    @State(Scope.Benchmark)
    public static class BenchmarkState {
        int size;

        int[] arr;

        @Setup(Level.Trial)
        public void setUp() {
            arr = new int[size];
            for (int i = 0; i < size; i++) {
                arr[i] = i + 1; // fill array with consecutive numbers
            }
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public boolean testContainsNonPrime(BenchmarkState state) {
        return serial.containsNonPrime(state.arr);
    }
}