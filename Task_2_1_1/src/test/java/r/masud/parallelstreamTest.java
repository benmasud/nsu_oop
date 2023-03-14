package r.masud;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class parallelstreamTest {
    @Test
    public void testContainsNonPrime() {
        int[] nums1 = {6, 8, 7, 13, 9, 4};
        int[] nums2 = {6997901, 6997927, 6997937, 6997967, 6998009, 6998029, 6998039, 6998051, 6998053};

        boolean expected1 = true;
        boolean expected2 = false;

        boolean actual1 = parallelstream.containsNonPrime(nums1);
        boolean actual2 = parallelstream.containsNonPrime(nums2);

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }

}