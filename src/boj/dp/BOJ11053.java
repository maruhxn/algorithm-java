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
        System.out.println(lis(-1) - 1);
    }

    static int lis(int start) {
        if (cache[start + 1] != -1) return cache[start + 1];

        int ret = 1;
        for (int next = start + 1; next < N; next++) {
            if (start == -1 || arr[start] < arr[next])
                ret = Math.max(ret, 1 + lis(next));
        }

        return cache[start + 1] = ret;
    }
}
