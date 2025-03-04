package boj.swm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1992 {
    static int N;
    static char[][] board;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new char[N][N];
        sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        quadTree(0, 0, N);

        System.out.println(sb);
    }

    private static void quadTree(int y, int x, int size) {
        if (checkSame(y, x, size)) {
            sb.append(board[y][x]);
        } else {
            sb.append('(');
            int nextSize = size / 2;
            quadTree(y, x, nextSize);
            quadTree(y, x + nextSize, nextSize);
            quadTree(y + nextSize, x, nextSize);
            quadTree(y + nextSize, x + nextSize, nextSize);
            sb.append(')');
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
