package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2293 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] money = new int[N];
        for (int i = 0; i < N; i++) {
            money[i] = Integer.parseInt(br.readLine());
        }

        // dp[k]: k원을 만드는 방법의 수
        int[] dp = new int[K + 1];
        dp[0] = 1;


        for (int i = 0; i < N; i++) {
            for (int k = money[i]; k <= K; k++) {
                // dp[k]의 기존 값 -> 이전까지 고려한 동전들로 k원을 만드는 방법의 수
                // dp[k - money[i]] -> 현재 동전 money[i]를 추가했을 때 k원을 만드는 방법의 수
                dp[k] += dp[k - money[i]];
            }
        }

        System.out.println(dp[K]);
    }
}
