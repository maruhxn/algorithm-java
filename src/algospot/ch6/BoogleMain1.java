package algospot.ch6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BoogleMain1 {

    static char[][] board = new char[5][5];
    static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};

    static boolean canFind;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            for (int i = 0; i < 5; i++) {
                char[] chars = br.readLine().toCharArray();
                for (int j = 0; j < chars.length; j++) {
                    board[i][j] = chars[j];
                }
            }

            int n = Integer.parseInt(br.readLine());
            while (n-- > 0) {
                String str = br.readLine();
                canFind = false;

                List<int[]> points = new ArrayList<>();

                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (board[i][j] == str.charAt(0)) {
                            points.add(new int[]{i, j});
                        }
                    }
                }

                for (int[] point : points) {
                    check(str, 0, point);
                }

                System.out.println(str + " " + (canFind ? "YES" : "NO"));
            }
        }
    }

    private static void check(String str, int index, int[] currPoint) {
        if (index == str.length() - 1) {
            canFind = true;
            return;
        }

        for (int i = 0; i < currPoint.length; i++) {
            for (int dir = 0; dir < 8; dir++) {
                int nx = currPoint[0] + dx[dir];
                int ny = currPoint[1] + dy[dir];

                if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;

                if (board[nx][ny] == str.charAt(index + 1)) {
                    check(str, index + 1, new int[]{nx, ny});
                }
            }
        }
    }
}
