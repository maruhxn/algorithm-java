package boj.prefix_sum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ25682 {

    static int[][] board;
    static int[][] bSum;
    static int[][] wSum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        board = new int[N][M]; // 1이면 색칠해야 함. 0이면 색칠하지 않아도 됨
        bSum = new int[N][M];
        wSum = new int[N][M];

        boolean color = false;
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                char colorStr = s.charAt(j);
                if (!color && colorStr == 'W') board[i][j] = 1; // 검은색으로 변경
                else if (color && colorStr == 'B') board[i][j] = 1; // 흰색으로 변경
                color = !color;
            }

            if (M % 2 == 0) color = !color;
        }

        // 누적합 구하기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int ret = board[i][j];
                if (i > 0) ret += bSum[i - 1][j];
                if (j > 0) ret += bSum[i][j - 1];
                if (i > 0 && j > 0) ret -= bSum[i - 1][j - 1];
                bSum[i][j] = ret;
            }
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        // logic
        for (int i = 0; i + K - 1 < N; i++) {
            for (int j = 0; j + K - 1 < M; j++) {
                int gridSum = gridSum(i, j, i + K - 1, j + K - 1);
                min = Math.min(min, gridSum);
                max = Math.max(max, gridSum);
                // 첫칸 검은색 기준, 칠해야 하는 정사각형 수의 최댓값 = 첫칸 흰색 기준, 칠해야 하는 정사각형 수의 최솟값
            }
        }

        System.out.println(Math.min(min, K * K - max));
    }

    static int gridSum(int y1, int x1, int y2, int x2) {
        int ret = bSum[y2][x2];
        if (y1 > 0) ret -= bSum[y1 - 1][x2];
        if (x1 > 0) ret -= bSum[y2][x1 - 1];
        if (y1 > 0 && x1 > 0) ret += bSum[y1 - 1][x1 - 1];
        return ret;
    }
}