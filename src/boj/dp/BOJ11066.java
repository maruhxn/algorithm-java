package boj.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11066 {

    static int[] pSum;
    static int[] files;
    static long[][] cache;
    static int K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            K = Integer.parseInt(br.readLine());
            files = new int[K];
            pSum = new int[K];
            cache = new long[K][K];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) {
                files[i] = (Integer.parseInt(st.nextToken()));
            }

            pSum[0] = files[0];
            for (int i = 1; i < K; i++) {
                pSum[i] = pSum[i - 1] + files[i];
            }

            sb.append(solve(0, K - 1)).append("\n");
        }
        System.out.println(sb);
    }

    private static long solve(int st, int ed) {
        if (st == ed) return cache[st][ed];
        if (ed - st == 1) return cache[st][ed] = files[st] + files[ed];

        long ret = cache[st][ed];
        if (ret != 0) return ret;

        ret = Integer.MAX_VALUE;
        for (int mid = st; mid < ed; mid++) {
            ret = Math.min(ret, solve(st, mid) + solve(mid + 1, ed));
        }

        return cache[st][ed] = ret + rangeSum(st, ed);
    }

    private static long rangeSum(int st, int ed) {
        return st == 0 ? pSum[ed] : pSum[ed] - pSum[st - 1];
    }
}
