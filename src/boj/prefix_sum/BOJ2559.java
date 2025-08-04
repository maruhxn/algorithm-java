package boj.prefix_sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2559 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        int[] pSum = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (i == 0) pSum[i] = arr[i];
            else pSum[i] = pSum[i - 1] + arr[i];
        }

        int ret = Integer.MIN_VALUE;
        for (int right = K - 1; right < N; right++) {
            int left = right - K + 1;
            ret = Math.max(ret, pSum[right] - pSum[left] + arr[left]);
        }

        System.out.println(ret);
    }
}
