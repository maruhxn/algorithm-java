package boj.swm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11066 {

    static int K;
    static int[] files, pSum;
    static int[][] cache;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            K = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            files = new int[K];
            pSum = new int[K];
            cache = new int[K][K];

            for (int i = 0; i < K; i++) {
                files[i] = Integer.parseInt(st.nextToken());
                Arrays.fill(cache[i], -1);
            }

            pSum[0] = files[0];
            for (int i = 1; i < K; i++) {
                pSum[i] = files[i] + pSum[i - 1];
            }

            sb.append(merge(0, K - 1)).append("\n");
        }

        System.out.println(sb);
    }

    private static int merge(int st, int ed) {
        if (st == ed) return 0;

        if(cache[st][ed] != -1) return cache[st][ed];

        int ret = Integer.MAX_VALUE;
        for (int i = st; i < ed; i++) {
            ret = Math.min(ret, merge(st, i) + merge(i + 1, ed));
        }

        return cache[st][ed] = ret + (st == 0 ? pSum[ed] : pSum[ed] - pSum[st - 1]);
    }
}