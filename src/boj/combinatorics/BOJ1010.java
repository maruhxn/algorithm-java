package boj.combinatorics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1010 {

    static int[][] cache;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int C = Integer.parseInt(br.readLine());
        StringTokenizer st;

        while (C-- > 0) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            cache = new int[n + 1][r + 1];
            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= r; j++) {
                    cache[i][j] = -1;
                }
            }

            sb.append(combination(n, r)).append("\n");
        }

        System.out.println(sb);
    }

    static int combination(int n, int r) {
        if (n == r || r == 0) return 1;

        if (cache[n][r] != -1) return cache[n][r];

        return cache[n][r] = combination(n - 1, r) + combination(n - 1, r - 1);
    }
}