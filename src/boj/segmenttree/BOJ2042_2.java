package boj.segmenttree;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ2042_2 {
    private static int N, M, K;
    private static long[] arr, tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new long[N + 1];
        tree = new long[N + 1];

        for (int i = 1; i < N + 1; i++) {
            long x = Long.parseLong(br.readLine());
            arr[i] = x;
            update(i, x);
        }

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                update(b, c - arr[b]);
                arr[b] = c;
            } else if (a == 2) bw.append(intervalSum(b, (int) c) + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static long prefixSum(int pos) {
        long ret = 0;
        while (pos > 0) {
            ret += tree[pos];
            pos -= (pos & -pos);
        }
        return ret;
    }

    public static void update(int pos, long val) {
        while (pos <= N) {
            tree[pos] += val;
            pos += (pos & -pos);
        }
    }

    public static long intervalSum(int start, int end) {
        return prefixSum(end) - prefixSum(start - 1);
    }
}
