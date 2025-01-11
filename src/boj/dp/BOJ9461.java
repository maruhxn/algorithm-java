package boj.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ9461 {

    static long[] cache;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int C = Integer.parseInt(br.readLine());

        cache = new long[101];
        Arrays.fill(cache, -1);

        while (C-- > 0) {
            int N = Integer.parseInt(br.readLine());
            sb.append(padoban(N)).append("\n");
        }


        System.out.println(sb);
    }

    static long padoban(int N) {
        if (N <= 3) return 1;

        long ret = cache[N];
        if (ret != -1) return ret;

        return cache[N] = padoban(N - 2) + padoban(N - 3);
    }
}
