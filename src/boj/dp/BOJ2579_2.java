package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2579_2 {

    static int N;
    static int[] stairs;
    static int[] cache;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        stairs = new int[N + 1];
        cache = new int[N + 1];
        Arrays.fill(cache, -1);

        for (int i = 1; i <= N; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(solve(N));
    }

    private static int solve(int N) {
        if (N <= 1) return stairs[N];
        if (N == 2) return stairs[1] + stairs[2];
        if (cache[N] != -1) return cache[N];

        return cache[N] = stairs[N] + Math.max(solve(N - 2), stairs[N - 1] + solve(N - 3));
    }
}