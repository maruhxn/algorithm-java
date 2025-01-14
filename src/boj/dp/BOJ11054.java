package boj.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11054 {
    static int N;
    static int[] arr;
    static int[] cache;
    static int[] reverseCache;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        cache = new int[N + 1];
        reverseCache = new int[N + 1];

        Arrays.fill(cache, -1);
        Arrays.fill(reverseCache, -1);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ret = 0;
        for (int mid = 0; mid < N; mid++) {
            ret = Math.max(ret, lis(mid) + lds(mid) - 1);
        }

        System.out.println(ret);
    }

    static int lis(int end) {
        int ret = cache[end];
        if (ret != -1) return ret;

        ret = 1;
        for (int i = end - 1; i >= 0; --i) {
            if (arr[i] < arr[end])
                ret = Math.max(ret, 1 + lis(i));
        }

        return cache[end] = ret;
    }

    static int lds(int end) {
        int ret = reverseCache[end];
        if (ret != -1) return ret;

        ret = 1;
        for (int i = end + 1; i < N; ++i) {
            if (arr[i] < arr[end])
                ret = Math.max(ret, 1 + lis(i));
        }

        return reverseCache[end] = ret;
    }
}