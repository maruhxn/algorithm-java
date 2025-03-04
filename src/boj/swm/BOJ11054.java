package boj.swm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11054 {
    static int N;
    static int[] arr, lCache, rCache;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        lCache = new int[N];
        rCache = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 배열을 -1로 초기화 (메모이제이션 용)
        Arrays.fill(lCache, -1);
        Arrays.fill(rCache, -1);

        int ret = 0;
        for (int i = 0; i < N; i++) {
            ret = Math.max(ret, lis(i) + lds(i) - 1);
        }

        System.out.println(ret);
    }

    // arr[end]를 포함하는 LIS 길이
    private static int lis(int end) {
        if (lCache[end] != -1) return lCache[end];

        int ret = 1;
        for (int prev = end - 1; prev >= 0; prev--) {
            if (arr[prev] < arr[end]) {
                ret = Math.max(ret, lis(prev) + 1);
            }
        }
        return lCache[end] = ret;
    }

    // arr[start]를 포함하는 LDS 길이
    private static int lds(int start) {
        if (rCache[start] != -1) return rCache[start];

        int ret = 1;
        for (int next = start + 1; next < N; next++) {
            if (arr[next] < arr[start]) {
                ret = Math.max(ret, lds(next) + 1);
            }
        }
        return rCache[start] = ret;
    }
}
