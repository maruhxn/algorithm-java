package boj.dp;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ10844 {
    static long[][] cache;
    static int N;
    static long mod = 1000000000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        cache = new long[N + 1][10];

        for (int i = 1; i <= N; i++) {
            Arrays.fill(cache[i], -1);
        }

        long ret = 0;
        for (int i = 1; i <= 9; i++) {
            ret = (ret + stairNum(1, i)) % mod;
        }

        System.out.println(ret);
    }

    private static long stairNum(int depth, int curr) {
        if (curr < 0 || curr > 9) return 0;
        if (depth == N) return 1;
        if (cache[depth][curr] != -1) return cache[depth][curr];

        return cache[depth][curr] = (stairNum(depth + 1, curr - 1) + stairNum(depth + 1, curr + 1)) % mod;
    }
}
