package boj.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11054 {
    static int N;
    static int[] arr;
    static int[][] cache;
    static int[] reverseCache;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        cache = new int[N + 1][N + 1];
        reverseCache = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(cache[i], -1);
        }
        Arrays.fill(reverseCache, -1);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ret = 0;
        for (int mid = 0; mid < N; mid++) {
            ret = Math.max(ret, (lis(-1, mid) - 1) + reverseLis(mid));
        }

        System.out.println(ret);
    }

    static int lis(int st, int mid) {
        int ret = cache[st + 1][mid];
        if (ret != -1) return ret;

        ret = 1;
        for (int next = st + 1; next < mid; ++next) {
            if ((st == -1 || arr[st] < arr[next]) && arr[next] < arr[mid])
                ret = Math.max(ret, 1 + lis(next, mid));
        }

        return cache[st + 1][mid] = ret;
    }

    static int reverseLis(int st) {
        int ret = reverseCache[st];
        if (ret != -1) return ret;

        ret = 1;
        for (int next = st + 1; next < N; ++next) {
            if (arr[st] > arr[next])
                ret = Math.max(ret, 1 + reverseLis(next));
        }

        return reverseCache[st] = ret;
    }
}