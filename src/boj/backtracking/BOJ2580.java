package boj.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2580 {
    static int[][] board = new int[9][9];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        sudoku();
    }

    public static void sudoku() {
        // (y, x) 이후에서부터 0인 점 찾기
        int targetY = -1;
        int targetX = -1;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    targetY = i;
                    targetX = j;
                    break;
                }
            }
            if (targetY != -1) break;
        }

        // 0인 점이 없다면 결과 출력하고 끝내기
        if (targetY == -1) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(board[i][j]).append(" ");
                }
                sb.append("\n");
            }
            System.out.println(sb);
            System.exit(0);
        }

        // 0인 점이 있다면 해당 점에 올 수 있는 숫자들 찾기
        boolean[] available = new boolean[10];
        Arrays.fill(available, true);

        for (int i = 0; i < 9; i++) { // 가로 체크
            available[board[targetY][i]] = false;
        }

        for (int i = 0; i < 9; i++) { // 세로 체크
            available[board[i][targetX]] = false;
        }

        int unitY = (targetY / 3) * 3;
        int unitX = (targetX / 3) * 3;

        for (int i = unitY; i < unitY + 3; i++) {
            for (int j = unitX; j < unitX + 3; j++) {
                available[board[i][j]] = false;
            }
        }

        // 만약 가능한 숫자가 있다면, 가능한 숫자들을 하나씩 넣어본 후, 다시 스도쿠 진행
        for (int i = 1; i < available.length; i++) {
            if (available[i]) {
                board[targetY][targetX] = i;
                sudoku();
                board[targetY][targetX] = 0;
            }
        }

    }
}
