package com.example.others.hash;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.common.primitives.Longs;
import org.apache.commons.lang.StringUtils;


import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * hash字符串，使用64进制存储
 */
public class X64UnsignedHashFunction {

    static HashFunction hashFunction = Hashing.murmur3_128();

    private static final char[] RADIX_64 = {'0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
            'x', 'y', 'z', '-', '_'};

    private static int[] RADIX_INDEX = new int[130];

    static {
        for (int i = 0; i < RADIX_64.length; i++) {
            RADIX_INDEX[RADIX_64[i]] = i;
        }
    }

    public static String x64(long l) {
        return toString(l, RADIX_64.length);
    }

    public static long asLong(String key) {

        long spid_1 = 0L;

        for (int i = 0; i < key.length(); i++) {

            char c = key.charAt(i);

            spid_1 = spid_1 * 64 + RADIX_INDEX[c];
        }

        return spid_1;
    }

    @SuppressWarnings("deprecation")
    public static String hash(String input) {
        long hash = hashFunction.hashString(input).asLong();
        return StringUtils.reverse(x64(hash));
    }

    public static String randomChar() {
        int index = (int) (Math.random() * 64);
        String s = String.valueOf(RADIX_64[index]);
        return s;
    }

    private static String toString(long x, int radix) {
        checkArgument(
                radix >= Character.MIN_RADIX && radix <= RADIX_64.length,
                "radix (%s) must be between Character.MIN_RADIX and Character.62",
                radix);
        if (x == 0) {
            // Simply return "0"
            return "0";
        } else {
            char[] buf = new char[64];
            int i = buf.length;
            if (x < 0) {
                // Separate off the last digit using unsigned division. That
                // will leave
                // a number that is nonnegative as a signed integer.
                long quotient = divide(x, radix);
                long rem = x - quotient * radix;
                buf[--i] = forDigit((int) rem, radix);
                x = quotient;
            }
            // Simple modulo/division approach
            while (x > 0) {
                buf[--i] = forDigit((int) (x % radix), radix);
                x /= radix;
            }
            // Generate string
            return new String(buf, i, buf.length - i);
        }
    }

    private static char forDigit(int digit, int radix) {
        if ((digit >= radix) || (digit < 0)) {
            return '\0';
        }
        if ((radix < Character.MIN_RADIX) || (radix > RADIX_64.length)) {
            return '\0';
        }

        return RADIX_64[digit];
    }

    private static long divide(long dividend, long divisor) {
        if (divisor < 0) { // i.e., divisor >= 2^63:
            if (compare(dividend, divisor) < 0) {
                return 0; // dividend < divisor
            } else {
                return 1; // dividend >= divisor
            }
        }

        if (dividend >= 0) {
            return dividend / divisor;
        }
        long quotient = ((dividend >>> 1) / divisor) << 1;
        long rem = dividend - quotient * divisor;
        return quotient + (compare(rem, divisor) >= 0 ? 1 : 0);
    }

    private static long flip(long a) {
        return a ^ Long.MIN_VALUE;
    }

    private static int compare(long a, long b) {
        return Longs.compare(flip(a), flip(b));
    }

    public static List<String> getAllChars() {

        List<String> res = new ArrayList<>();

        for (char c : RADIX_64) {
            res.add(String.valueOf(c));
        }

        return res;
    }


    public static void main(String[] args) {
        List<String> allChars = X64UnsignedHashFunction.getAllChars();
        System.out.println("allChars = " + allChars);
        System.out.println("allChars = " + allChars.get(44));
    }

}