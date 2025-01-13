package boj.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BOJ2565 {

    static int N;
    static int[] lines;
    static int[] cache;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 전깃줄 개수

        cache = new int[501];
        Arrays.fill(cache, -1);
        TreeMap<Integer, Integer> map = new TreeMap<>();

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map.put(a - 1, b);
        }

        lines = map.values().stream().mapToInt(v -> v).toArray();

        System.out.println(N - (lis(-1) - 1));
    }

    static int lis(int start) {
        int ret = cache[start + 1];
        if (ret != -1) return ret;

        ret = 1;
        for (int next = start + 1; next < N; next++) {
            if (start == -1 || lines[start] < lines[next])
                ret = Math.max(ret, 1 + lis(next));
        }

        return cache[start + 1] = ret;
    }
}