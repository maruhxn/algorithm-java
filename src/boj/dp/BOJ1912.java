package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1912 {

    static int[] arr, cache;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        cache = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            cache[i] = Integer.MIN_VALUE;
        }

        int ret = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            ret = Math.max(ret, solve(i));
        }

        System.out.println(ret);
    }

    // i번째 원소를 포함한 최대 연속 부분합을 반환
    private static int solve(int idx) {
        if (idx == 0) return arr[idx];
        if (cache[idx] != Integer.MIN_VALUE) return cache[idx];

        return cache[idx] = Math.max(arr[idx], arr[idx] + solve(idx - 1));
    }
}
