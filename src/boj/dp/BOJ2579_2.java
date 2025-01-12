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

        int ret = game(N);

        System.out.println(ret);
    }

    static int game(int curr) {
        if (curr <= 1) return stairs[curr];
        if (curr == 2) return stairs[1] + stairs[2];

        int ret = cache[curr];
        if (ret != -1) return ret;

        int case1 = game(curr - 2);
        int case2 = stairs[curr - 1] + game(curr - 3);

        return cache[curr] = stairs[curr] + Math.max(case1, case2);
    }
}