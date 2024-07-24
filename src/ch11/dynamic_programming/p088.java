package ch11.dynamic_programming;

import java.util.Scanner;

// 쉬운 계단 수(S1)
//public class p088 {
//    static long[][] D;
//    static long result;
//    static long mod = 1000000000;
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        D = new long[n + 1][11];
//        for (int i = 1; i <= 9; i++) {
//            D[1][i] = 1;
//        }
//
//        for (int i = 2; i <= n; i++) {
//            D[i][0] = D[i - 1][1];
//            D[i][9] = D[i - 1][8];
//            for (int j = 1; j <= 8; j++) {
//                D[i][j] = (D[i - 1][j + 1] + D[i - 1][j - 1]) % mod;
//            }
//        }
//
//        for (int i = 0; i < 10; i++) {
//            result = (result + D[n][i]) % mod;
//        }
//
//        System.out.println(result);
//    }
//}

public class p088 {
    static long[][] D;
    static long mod = 1000000000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        D = new long[n + 1][11];

        // Initialize the memoization array with -1 indicating uncomputed states
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= 9; j++) {
                D[i][j] = -1;
            }
        }

        long result = 0;

        // Sum up all possible stair numbers of length n ending with digits 0 to 9
        for (int i = 1; i <= 9; i++) {
            result = (result + stairCount(n, i)) % mod;
        }

        System.out.println(result);
    }

    static long stairCount(int length, int lastDigit) {
        if (length == 1) {
            return 1;
        }
        if (D[length][lastDigit] != -1) {
            return D[length][lastDigit];
        }

        long count = 0;
        if (lastDigit > 0) {
            count = (count + stairCount(length - 1, lastDigit - 1)) % mod;
        }
        if (lastDigit < 9) {
            count = (count + stairCount(length - 1, lastDigit + 1)) % mod;
        }

        D[length][lastDigit] = count;
        return count;
    }
}