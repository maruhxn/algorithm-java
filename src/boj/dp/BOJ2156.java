package boj.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2156 {
    static int N;
    static int[] podoju;
    static int[] cache;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        podoju = new int[N + 1];
        cache = new int[N + 1];
        Arrays.fill(cache, -1);

        for (int i = 1; i <= N; i++) {
            podoju[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(drink(N));
    }

    static int drink(int curr) {
        // 기저 사례
        if (curr <= 1) return podoju[curr];
        if (curr == 2) return podoju[1] + podoju[2];

        if (cache[curr] != -1) return cache[curr];

        int drinkCurr = podoju[curr] + Math.max(drink(curr - 2), drink(curr - 3) + podoju[curr - 1]);
        int noDrinkCurr = drink(curr - 1);

        return cache[curr] = Math.max(drinkCurr, noDrinkCurr);
    }
}
