package algospot.ch6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BoogleMain2 {

    static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
    static char[][] board = new char[5][5];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            for (int i = 0; i < 5; i++) {
                board[i] = br.readLine().toCharArray();
            }

            int n = Integer.parseInt(br.readLine());

            while (n-- > 0) {
                String str = br.readLine();
                boolean canFind = false;

                for (int i = 0; i < 5 && !canFind; i++) {
                    for (int j = 0; j < 5 && !canFind; j++) {
                        if (hasWord(i, j, str)) {
                            canFind = true;
                        }
                    }
                }

                System.out.println(str + " " + (canFind ? "YES" : "NO"));
            }
        }
    }

    public static boolean hasWord(int y, int x, String words) {
        if (!inRange(y, x)) return false; // 시작 위치가 범위 밖이면 실패
        // 시작 위치의 범위와 첫 글자 일치 여부를 확인하고 있기 때문에 for 문 안에서 별도로 확인하지 않아도 된다

        if (board[y][x] != words.charAt(0)) return false; // 첫 글자가 일치하지 않으면 실패

        if (words.length() == 1) return true; // 단어의 길이가 1이면 성공

        for (int dir = 0; dir < 8; ++dir) {
            int ny = y + dy[dir], nx = x + dx[dir];

            if (hasWord(ny, nx, words.substring(1))) return true;
        }

        return false;
    }

    private static boolean inRange(int y, int x) {
        return y >= 0 && y < board.length && x >= 0 && x < board[0].length;
    }
}
