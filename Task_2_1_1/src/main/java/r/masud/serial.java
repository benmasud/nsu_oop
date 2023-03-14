package r.masud;

public class serial {
    // non prime number in serial solution
    public static boolean containsNonPrime(int[] arr) {
        for (int num : arr) {
            if (num <= 1) {
                continue; // 1 and below are not prime
            }
            boolean isPrime = true;
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (!isPrime) {
                return true; // found a non-prime number
            }
        }
        return false; // no non-prime number found
    }

}
