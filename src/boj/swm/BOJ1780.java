package boj.swm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1780 {
    static int N;
    static int[][] board;
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        board = new int[N][N];
        result = new int[3];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cut(0, 0, N);

        StringBuilder sb = new StringBuilder();
        for (int ret : result) {
            sb.append(ret).append("\n");
        }

        System.out.println(sb);
    }

    private static void cut(int y, int x, int size) {
        if (checkSame(y, x, size)) {
            result[board[y][x] + 1]++;
        } else {
            int nextSize = size / 3;
            cut(y, x, nextSize);
            cut(y + nextSize, x, nextSize);
            cut(y + (2 * nextSize), x, nextSize);

            cut(y, x + nextSize, nextSize);
            cut(y + nextSize, x + nextSize, nextSize);
            cut(y + (2 * nextSize), x + nextSize, nextSize);

            cut(y, x + (2 * nextSize), nextSize);
            cut(y + nextSize, x + (2 * nextSize), nextSize);
            cut(y + (2 * nextSize), x + (2 * nextSize), nextSize);
        }
    }

    private static boolean checkSame(int y, int x, int size) {
        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                if (board[y][x] != board[i][j]) return false;
            }
        }

        return true;
    }
}
