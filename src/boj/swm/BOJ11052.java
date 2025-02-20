package boj.swm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 가격이 높으면서 개수는 적게 사야 함

public class BOJ11052 {
    static int[] p;
    static int[] cache;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        p = new int[N];
        cache = new int[N + 1];

        Arrays.fill(cache, -1);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            p[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solve(N));
    }

    // 카드 n개를 사기 위해 지불해야 하는 금액의 최댓값
    private static int solve(int n) {
        if(n == 0) return 0;

        if(cache[n] != -1) return cache[n];
        int ret = 0;

        for (int i = 0; i < p.length; i++) {
            if(n - (i + 1) >= 0) ret = Math.max(ret, p[i] + solve(n - i - 1));
        }

        return cache[n] = ret;
    }
}
