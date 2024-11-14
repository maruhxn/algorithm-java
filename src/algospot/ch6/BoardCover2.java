package algospot.ch6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BoardCover2 {

    static int[][][] coverType = {
            {{0, 0}, {1, 0}, {0, 1}},
            {{0, 0}, {0, 1}, {1, 1}},
            {{0, 0}, {1, 0}, {1, 1}},
            {{0, 0}, {1, 0}, {1, -1}},
    };
    static int h, w;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());
        while (c-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            board = new int[h][w]; // 보드 초기화
            for (int i = 0; i < h; i++) {
                char[] chars = br.readLine().toCharArray();
                for (int j = 0; j < w; j++) {
                    board[i][j] = chars[j] == '#' ? 1 : 0;
                }
            }

            System.out.println(cover(board));
        }
    }

    public static boolean set(int[][] board, int y, int x, int type, int delta) {
        boolean ok = true;

        for (int i = 0; i < 3; i++) {
            int ny = y + coverType[type][i][0];
            int nx = x + coverType[type][i][1];

            if (ny < 0 || ny >= board.length || nx < 0 || nx >= board[0].length) ok = false;
            else if ((board[ny][nx] += delta) > 1) {
                ok = false;
            }
        }

        return ok;
    }

    public static int cover(int[][] board) {
        // 아직 채우지 못한 칸 중 가장 윗줄 왼쪽에 있는 칸을 찾는다
        int y = -1, x = -1;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (board[i][j] == 0) {
                    y = i;
                    x = j;
                    break;
                }
            }
            if (y != -1) break;
        }

        // 기저 사례: 모든 칸을 채웠으면 1을 반환한다
        if (y == -1) return 1;

        int ret = 0;

        for (int type = 0; type < 4; type++) {
            if (set(board, y, x, type, 1)) ret += cover(board);
            set(board, y, x, type, -1);
        }
        return ret;
    }
}

/**
 * 1. 블럭을 채우는 모양도 중요했다. 해당 점은 무조건 채우고 넘어가야 한다. 채우지 않고 넘어간다면 분기가 굉장히 많아진다.. 무조건 채울 수 있도록 coverType을 만들어야 한다.
 * 2. 해당 위치에 블럭을 놓을 수 있는지 여부를 확인하고 놓을 수 없더라도 일단 마지막까지 실행하는 점이 중요했다.
 * 마지막까지 실행하지 않고 중간에 취소를 해버리면 나중에 놓았던 블록은 치울 때, 기존에 있던 블록마저 치우게 된다..
 * => board의 타입을 int[][]로 둔 후, 1보다 큰 값들을 겹쳐서 놓여있는 상황으로 생각하여 일단은 끝까지 진행한다. 당연히 겹친 부분은 올바르지 않은 상황이므로 다음 분기를 탈 수 없게 ok = false로 처리한다.
 */