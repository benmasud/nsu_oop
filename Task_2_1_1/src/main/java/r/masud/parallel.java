package r.masud;

public class parallel {
    // non - prime number in parallel solution using the Thread class
    class NonPrimeChecker {
        public boolean containsNonPrime(int[] nums, int numThreads) {
            Thread[] threads = new Thread[numThreads];
            int chunkSize = (int) Math.ceil((double) nums.length / numThreads);

            NonPrimeThread[] nonPrimeThreads = new NonPrimeThread[numThreads];
            for (int i = 0; i < numThreads; i++) {
                int start = i * chunkSize;
                int end = Math.min(start + chunkSize, nums.length);
                nonPrimeThreads[i] = new NonPrimeThread(nums, start, end);
                threads[i] = new Thread(nonPrimeThreads[i]);
                threads[i].start();
            }

            for (int i = 0; i < numThreads; i++) {
                try {
                    threads[i].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (NonPrimeThread nonPrimeThread : nonPrimeThreads) {
                if (nonPrimeThread.foundNonPrime) {
                    return true;
                }
            }

            return false;
        }
    }

    class NonPrimeThread implements Runnable {
        private int[] nums;
        private int start;
        private int end;
        public boolean foundNonPrime = false;

        public NonPrimeThread(int[] nums, int start, int end) {
            this.nums = nums;
            this.start = start;
            this.end = end;
        }

        public void run() {
            for (int i = start; i < end; i++) {
                if (!isPrime(nums[i])) {
                    foundNonPrime = true;
                    break;
                }
            }
        }

        private boolean isPrime(int n) {
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
}
