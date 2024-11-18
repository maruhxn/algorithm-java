package boj.divideandconquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 222-풀링
public class BOJ17829 {

    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solve(n, 0, 0));
    }

    public static int solve(int n, int y, int x) {
        if (n == 1) return board[y][x];

        int[] pollingResult = new int[4];

        pollingResult[0] = solve(n / 2, y, x);
        pollingResult[1] = solve(n / 2, y, x + n / 2);
        pollingResult[2] = solve(n / 2, y + n / 2, x);
        pollingResult[3] = solve(n / 2, y + n / 2, x + n / 2);

        Arrays.sort(pollingResult);

        return pollingResult[2];
    }
}
