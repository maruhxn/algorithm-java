package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ7579 {

    static int N, M;
    static int[] m, c;
    static int[][] cache;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        m = new int[N];
        for (int i = 0; i < N; i++) {
            m[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        c = new int[N];
        int cSum = 0;
        for (int i = 0; i < N; i++) {
            c[i] = Integer.parseInt(st.nextToken());
            cSum += c[i];
        }

        cache = new int[N][cSum + 1];
        for (int i = 0; i < N; i++) {
            Arrays.fill(cache[i], -1);
        }

        // 최소 비용 찾기
        for (int costLimit = 0; costLimit <= cSum; costLimit++) {
            if (knapsack(N - 1, costLimit) >= M) {
                System.out.println(costLimit);
                break;
            }
        }
    }

    // knapsack(idx, costLimit) = idx번째 앱까지 고려했을 때, 비용 costLimit으로 확보할 수 있는 최대 메모리
    static int knapsack(int idx, int costLimit) {
        if (idx < 0) return 0;
        if (cache[idx][costLimit] != -1) return cache[idx][costLimit];

        int ret = knapsack(idx - 1, costLimit);
        if (costLimit >= c[idx])
            ret = Math.max(ret, m[idx] + knapsack(idx - 1, costLimit - c[idx]));

        return cache[idx][costLimit] = ret;
    }
}
