package r.masud;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class serialTest {
    @Test
    void testContainsNonPrimeTrue() {
        int[] arr = {6, 8, 7, 13, 9, 4};
        assertTrue(serial.containsNonPrime(arr));

    }

    @Test
    void testContainsNonPrimeFalse() {
        int[] arr = {6997901, 6997927, 6997937, 6997967, 6998009, 6998029, 6998039, 6998051, 6998053};
        assertFalse(serial.containsNonPrime(arr));
    }
}