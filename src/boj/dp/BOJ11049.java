package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 행렬 곱셈 순서
public class BOJ11049 {

    static int INF = 987654321;
    static int[][] matrix;
    static int[][] cache;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        matrix = new int[N][2];
        cache = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cache[i][j] = -1;
            }
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            matrix[i][0] = r;
            matrix[i][1] = c;
        }

        System.out.println(solve(0, N - 1));
    }

    private static int solve(int left, int right) {
        if (left == right) return 0;
        if (right - left == 1) return matrix[left][0] * matrix[left][1] * matrix[right][1];

        int ret = cache[left][right];
        if (ret != -1) return ret;


        ret = INF;
        for (int i = left; i < right; i++) {
            int sum = matrix[left][0] * matrix[i][1] * matrix[right][1];
            ret = Math.min(ret, solve(left, i) + solve(i + 1, right) + sum);
        }

        return cache[left][right] = ret;
    }
}
