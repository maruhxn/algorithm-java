package boj.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1932 {

    static int N;
    static int[][] triangle, cache;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        triangle = new int[N][500];
        cache = new int[N][500];


        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < i + 1; j++) {
                triangle[i][j] = Integer.parseInt(st.nextToken());
                cache[i][j] = -1;
            }
        }

        System.out.println(solve(0, 0));
    }

    static int solve(int depth, int pos) {
        if (depth == N - 1) return triangle[depth][pos];

        int ret = cache[depth][pos];
        if (ret != -1) return ret;

        return cache[depth][pos] = triangle[depth][pos] + Math.max(solve(depth + 1, pos), solve(depth + 1, pos + 1));
    }
}
