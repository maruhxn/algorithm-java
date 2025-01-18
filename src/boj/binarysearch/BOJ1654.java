package boj.binarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1654 {

    static int N, K;
    static long[] lines;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken()); // K <= N

        long max = -1;
        lines = new long[K];
        for (int i = 0; i < K; i++) {
            lines[i] = Long.parseLong(br.readLine());
            max = Math.max(max, lines[i]);
        }

        System.out.println(solve(1, max));
    }

    private static long solve(long st, long ed) {
        if (st > ed) return st - 1;

        long mid = (st + ed) / 2;

        long ret = 0;
        for (int i = 0; i < K; i++) {
            ret += lines[i] / mid;
        }

        if (ret < N) return solve(st, mid - 1);
        else return solve(mid + 1, ed);
    }
}
