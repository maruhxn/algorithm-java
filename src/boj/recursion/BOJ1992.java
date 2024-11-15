package boj.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1992 {

    static int N;
    static String[] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new String[N];

        for (int i = 0; i < N; i++) {
            board[i] = br.readLine();
        }

        System.out.println(solve(N, 0, 0));
    }

    public static String solve(int n, int y, int x) {
        if (checkIsAllSame(n, y, x)) {
            return String.valueOf(board[y].charAt(x));
        } else {
            return "(" + solve(n / 2, y, x)
                    + solve(n / 2, y, x + n / 2)
                    + solve(n / 2, y + n / 2, x)
                    + solve(n / 2, y + n / 2, x + n / 2) + ")";
        }
    }

    private static boolean checkIsAllSame(int n, int y, int x) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[y].charAt(x) != board[y + i].charAt(x + j)) {
                    return false;
                }
            }
        }
        return true;
    }
}
