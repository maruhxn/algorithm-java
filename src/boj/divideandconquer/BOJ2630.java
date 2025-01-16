package boj.divideandconquer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2630 {
    static int[] color = new int[2];
    static int[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        board = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(0, 0, N);

        sb.append(color[0]).append("\n").append(color[1]);
        System.out.println(sb);
    }

    static void solve(int y, int x, int size) {
        if (size == 1) {
            ++color[board[y][x]];
            return;
        }

        if (check(y, x, size)) {
            ++color[board[y][x]];
            return;
        }

        int nextSize = size / 2;
        solve(y, x, nextSize);
        solve(y, x + nextSize, nextSize);
        solve(y + nextSize, x, nextSize);
        solve(y + nextSize, x + nextSize, nextSize);
    }

    private static boolean check(int y, int x, int size) {
        for (int i = y; i < y + size; ++i) {
            for (int j = x; j < x + size; ++j) {
                if (board[y][x] != board[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}