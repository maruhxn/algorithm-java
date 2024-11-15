package boj.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ780 {

    static int N;
    static int[][] board;
    static int[] result = {0, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(N, 0, 0);

        System.out.println(result[0]);
        System.out.println(result[1]);
        System.out.println(result[2]);
    }

    public static void solve(int n, int y, int x) {
        // n == 1이면 종료
        if (n == 1) {
            result[board[y][x] + 1]++;
            return;
        }

        // 가득 찼다면 종료
        if (checkIsAllSame(n, y, x)) {
            result[board[y][x] + 1] += 1;
            return;
        }

        int nextN = n / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                solve(nextN, y + i * nextN, x + j * nextN);
            }
        }
    }

    private static boolean checkIsAllSame(int n, int y, int x) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[y][x] != board[y + i][x + j]) {
                    return false;
                }
            }
        }
        return true;
    }
}