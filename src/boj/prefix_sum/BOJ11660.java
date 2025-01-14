package boj.prefix_sum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11660 {

    static int[][] board;
    static int[][] pSum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        pSum = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int ret = board[i][j];
                if (i > 0) ret += pSum[i - 1][j];
                if (j > 0) ret += pSum[i][j - 1];
                if (i > 0 && j > 0) ret -= pSum[i - 1][j - 1];
                pSum[i][j] = ret;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y1 = Integer.parseInt(st.nextToken());
            int x1 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());

            sb.append(gridSum(y1 - 1, x1 - 1, y2 - 1, x2 - 1)).append("\n");
        }


        System.out.println(sb);
    }

    static int gridSum(int y1, int x1, int y2, int x2) {
        int ret = pSum[y2][x2];
        if (y1 > 0) ret -= pSum[y1 - 1][x2];
        if (x1 > 0) ret -= pSum[y2][x1 - 1];
        if (y1 > 0 && x1 > 0) ret += pSum[y1 - 1][x1 - 1];
        return ret;
    }
}
