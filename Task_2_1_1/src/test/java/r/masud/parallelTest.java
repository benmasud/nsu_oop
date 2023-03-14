package r.masud;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class parallelTest {
    @Test
    void testContainsNonPrime() {
        parallel parallel = new parallel();
        parallel.NonPrimeChecker checker = parallel.new NonPrimeChecker();

        int[] nums1 = {6, 8, 7, 13, 9, 4};
        boolean expected1 = true;
        boolean result1 = checker.containsNonPrime(nums1, 2);
        assertEquals(expected1, result1);

        int[] nums2 = {6997901, 6997927, 6997937, 6997967, 6998009, 6998029, 6998039, 6998051, 6998053};
        boolean expected2 = false;
        boolean result2 = checker.containsNonPrime(nums2, 3);
        assertEquals(expected2, result2);
    }
}
