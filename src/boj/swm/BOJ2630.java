package boj.swm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2630 {
    static int N;
    static int[][] board;
    static int[] cnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        cnt = new int[2];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        count(0, 0, N);
        System.out.println(String.valueOf(cnt[0]) + '\n' + cnt[1]);
    }

    private static void count(int y, int x, int k) {
        boolean ok = true;
        for (int i = y; i < y + k; i++) {
            for (int j = x; j < x + k; j++) {
                if (board[y][x] != board[i][j]) {
                    ok = false;
                    break;
                }
            }
            if (!ok) break;
        }

        if (ok) {
            cnt[board[y][x]]++;
        } else {
            int nextK = k / 2;
            count(y, x, nextK);
            count(y, x + nextK, nextK);
            count(y + nextK, x, nextK);
            count(y + nextK, x + nextK, nextK);
        }
    }
}
