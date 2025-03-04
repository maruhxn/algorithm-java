package boj.swm;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ1904 {

    static int[] cache;
    static final int mod = 15746;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        cache = new int[N + 1];
        Arrays.fill(cache, -1);

        System.out.println(tile(N));
    }

    static int tile(int n) {
        if (n <= 1) return 1;

        if (cache[n] != -1) return cache[n];

        return cache[n] = ((tile(n - 1) % mod) + (tile(n - 2) % mod)) % mod;
    }
}
