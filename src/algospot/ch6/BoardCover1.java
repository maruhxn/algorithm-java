package algospot.ch6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// {0, -1}, {-1, 0}
// {-1, 0}, {0, 1}
// {0, 1}, {1, 0}
// {1, 0}, {0, -1}
public class BoardCover1 {

    static int[][][] types = {
            {{0, -1}, {-1, 0}},
            {{-1, 0}, {0, 1}},
            {{0, 1}, {1, 0}},
            {{1, 0}, {0, -1}}
    };
    static int h, w;
    static char[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());
        while (c-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            board = new char[20][20]; // 보드 초기화
            for (int i = 0; i < h; i++) {
                board[i] = br.readLine().toCharArray();
            }

            System.out.println(paintRecursion(0, 0));
        }
    }

    public static int paintRecursion(int y, int x) {
        // 주어진 y 이상, x 초과 범위에서 채워지지 않은 점 선택하기
        int selectedX = -1;
        int selectedY = -1;

        for (int i = y; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (i == y && j < x) continue;
                if (board[i][j] == '.') {
                    selectedY = i;
                    selectedX = j;
                    break;
                }
            }
            if (selectedX != -1 && selectedY != -1) break;
        }

        // 선택된 점이 없는 경우
        if (selectedX == -1 && selectedY == -1) {
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (board[i][j] == '.') {
                        // 채워지지 않은 점이 있는 경우 -> 실패
                        return 0;
                    }
                }
            }
            // 채워지지 않은 점이 없는 경우, 1 반환
            return 1;
        }

        // 선택된 점이 있는 경우
        int ret = 0;

        // 4가지 방식으로 칠하기
        for (int i = 0; i < 4; i++) {
            int targetY1 = selectedY + types[i][0][0];
            int targetX1 = selectedX + types[i][0][1];

            int targetY2 = selectedY + types[i][1][0];
            int targetX2 = selectedX + types[i][1][1];

            if (targetX1 < 0 || targetY1 < 0 || targetX1 >= w || targetY1 >= h) continue;
            if (targetX2 < 0 || targetY2 < 0 || targetX2 >= w || targetY2 >= h) continue;
            if (board[targetY1][targetX1] == '#' || board[targetY2][targetX2] == '#') continue;

            board[selectedY][selectedX] = '#';
            board[targetY1][targetX1] = '#';
            board[targetY2][targetX2] = '#';

            ret += paintRecursion(selectedY, selectedX + 1);

            board[selectedY][selectedX] = '.';
            board[targetY1][targetX1] = '.';
            board[targetY2][targetX2] = '.';
        }

        ret += paintRecursion(selectedY, selectedX + 1);

        return ret;
    }
}
