package boj.swm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2565 {

    static int N;
    static int[] cache;
    static int[][] line;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        line = new int[N][2];
        cache = new int[N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            line[i] = new int[]{a, b};
            cache[i] = -1;
        }

        Arrays.sort(line, (l1, l2) -> l1[0] - l2[0]);

        int ret = 0;
        for (int i = 0; i < N; i++) {
            ret = Math.max(ret, lis(i));
        }

        System.out.println(N - ret);
    }

    private static int lis(int start) {
        if (cache[start] != -1) return cache[start];

        int ret = 1;
        for (int i = start + 1; i < N; i++) {
            if (line[start][1] < line[i][1]) {
                ret = Math.max(ret, 1 + lis(i));
            }
        }

        return cache[start] = ret;
    }
}
