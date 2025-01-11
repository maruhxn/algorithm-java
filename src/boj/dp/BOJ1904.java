package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1904 {

    static int[] cache;
    static final int mod = 15746;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        cache = new int[N + 1];
        Arrays.fill(cache, -1);

        System.out.println(tile(N));
    }

    static int tile(int n) {
        if (n == 0 || n == 1) return 1;

        if (cache[n] != -1) return cache[n];

        return cache[n] = ((tile(n - 1) % mod) + (tile(n - 2) % mod)) % mod;
    }
}