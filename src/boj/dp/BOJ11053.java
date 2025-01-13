package boj.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11053 {

    static int N;
    static int[] arr, cache;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        cache = new int[N + 1];
        Arrays.fill(cache, -1);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

//        System.out.println(lis(-1) - 1);

//        int ret = 0;
//        for (int i = 0; i < N; i++) {
//            ret = Math.max(ret, lis2(i));
//        }
//
//        System.out.println(ret);
        System.out.println(lis2(N) - 1);
    }

    static int lis2(int end) {
        int ret = cache[end];
        if (ret != -1) return ret;

        ret = 1;
        for (int i = end - 1; i >= 0; --i) {
            if (end == N || arr[i] < arr[end])
                ret = Math.max(ret, 1 + lis2(i));
        }

        return cache[end] = ret;
    }

    static int lis(int start) {
        int ret = cache[start + 1];
        if (ret != -1) return ret;

        ret = 1;
        for (int next = start + 1; next < N; next++) {
            if (start == -1 || arr[start] < arr[next])
                ret = Math.max(ret, 1 + lis(next));
        }

        return cache[start + 1] = ret;
    }
}
