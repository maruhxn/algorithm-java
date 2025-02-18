package boj.recursion;

import java.util.Scanner;

// 한 행씩 진행하여 중복으로 놓는 경우를 없앰
// 퀸을 놓은 경우, 이동 불가능한 모든 위치를 false로 업데이트하는 것보다는 해당 점이 퀸이 도달 가능한 지점인지를 찾는 것이 더 효율적
public class BOJ9663 {

    static int N, ret;
    static boolean[][] board;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        sc.close();

        board = new boolean[N][N];

        nQueen(0);

        System.out.println(ret);
    }

    // row 번째 행에 퀸 배치
    private static void nQueen(int row) {
        if (row == N) { // 모든 퀸 배치 완료
            ++ret;
            return;
        }

        // 모든 열(col)에 대해 안전한지 확인 후 배치
        for (int col = 0; col < N; col++) {
            if(isSafe(row, col)) {
                board[row][col] = true;
                nQueen(row + 1);
                board[row][col] = false;
            }
        }
    }

    // (y, x)에 퀸을 배치할 수 있는지 확인 (검사 중 퀸이 발견되면 false 반환)
    private static boolean isSafe(int y, int x) {
        // 같은 열 검사
        for (int i = 0; i < y; i++) {
            if (board[i][x]) return false;
        }

        // ↖ 왼쪽 위 대각선 검사
        for (int i = 1; y - i >= 0 && x - i >= 0; i++) {
            if (board[y - i][x - i]) return false;
        }

        // ↗ 오른쪽 위 대각선 검사
        for (int i = 1; y - i >= 0 && x + i < N; i++) {
            if (board[y - i][x + i]) return false;
        }

        return true;
    }
}