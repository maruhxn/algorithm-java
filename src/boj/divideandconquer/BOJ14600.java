package boj.divideandconquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 샤워실 바닥 깔기
public class BOJ14600 {

    static int[][] board;
    static int N;
    static int powed;
    static boolean ok = false;
    static int[][][] coverType = {
            {{0, 0}, {1, 0}, {0, 1}},
            {{0, 0}, {0, 1}, {1, 1}},
            {{0, 0}, {1, 0}, {1, 1}},
            {{0, 0}, {1, 0}, {1, -1}},
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        powed = (int) Math.pow(2, N);
        board = new int[powed][powed];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        board[powed - y][x - 1] = -1;

        solve(board, 1);

        if (!ok) System.out.println(-1);
    }

    private static boolean set(int[][] board, int y, int x, int type, int delta) {
        boolean ok = true;

        for (int i = 0; i < 3; i++) {
            int ny = y + coverType[type][i][0];
            int nx = x + coverType[type][i][1];

            if (ny < 0 || ny >= board.length || nx < 0 || nx >= board[0].length) {
                ok = false;
            } else {
                if (board[ny][nx] == -1 || board[ny][nx] > 1) ok = false;
                board[ny][nx] += delta;
            }
        }

        return ok;
    }

    private static void cancel(int[][] board, int y, int x, int type, int delta) {
        for (int i = 0; i < 3; i++) {
            int ny = y + coverType[type][i][0];
            int nx = x + coverType[type][i][1];

            if (ny < 0 || ny >= board.length || nx < 0 || nx >= board[0].length || board[ny][nx] == -1) continue;
            board[ny][nx] -= delta;
        }
    }

    private static void solve(int[][] board, int num) {
        if (ok) return;

        int y = -1, x = -1;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 0) {
                    y = i;
                    x = j;
                    break;
                }
            }
            if (y != -1) break;
        }

        // 기저 사례: 모든 칸을 채웠으면 1을 반환한다
        if (y == -1) {
            ok = true;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }
            return;
        }

        for (int type = 0; type < coverType.length; type++) {
            if (set(board, y, x, type, num)) solve(board, num + 1);
            cancel(board, y, x, type, num);
        }
    }
}
