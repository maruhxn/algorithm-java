package boj.swm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7562 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int[][] board;
        int[] dy = new int[]{1, 2, 2, 1, -1, -2, -2, -1};
        int[] dx = new int[]{-2, -1, 1, 2, 2, 1, -1, -2};

        while (T-- > 0) {
            int L = Integer.parseInt(br.readLine());
            board = new int[L][L];

            for (int i = 0; i < L; i++) {
                Arrays.fill(board[i], -1);
            }

            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            Queue<int[]> Q = new LinkedList<>();

            Q.add(new int[]{a, b});
            board[a][b] = 0;

            while (!Q.isEmpty()) {
                int[] curr = Q.poll();
                int y = curr[0];
                int x = curr[1];

                for (int dir = 0; dir < 8; dir++) {
                    int ny = y + dy[dir];
                    int nx = x + dx[dir];
                    if (nx < 0 || ny < 0 || nx >= L || ny >= L) continue;
                    if (board[ny][nx] != -1) continue;

                    Q.add(new int[]{ny, nx});
                    board[ny][nx] = board[y][x] + 1;
                }
            }

            sb.append(board[c][d]).append("\n");
        }

        System.out.println(sb);
    }

}
