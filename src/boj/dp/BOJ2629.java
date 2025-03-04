package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2629 {

    static int N, K;
    static int[] weights;
    static boolean[][] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()); // N <= 30
        weights = new int[N];
        result = new boolean[N + 1][40001];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            weights[i] = Integer.parseInt(st.nextToken()); // weights[i] <= 500
        }

        solve(0, 0);

        K = Integer.parseInt(br.readLine()); // K <= 7
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < K; i++) {
            int b = Integer.parseInt(st.nextToken()); // b <= 40000

            if (result[N][b]) sb.append("Y ");
            else sb.append("N ");
        }

        System.out.println(sb);
    }

    private static void solve(int idx, int weight) {
        if (result[idx][weight]) return;
        result[idx][weight] = true;
        if (idx == N) return;

        solve(idx + 1, weight); // 현재 무게는 상위 단계에서도 만들 수 있음
        solve(idx + 1, weight + weights[idx]); // 현재 무게에 새로운 추 추가(추 쪽)
        solve(idx + 1, Math.abs(weight - weights[idx])); // 반대편에 새로운 추 추가(구슬 쪽)
    }
}
