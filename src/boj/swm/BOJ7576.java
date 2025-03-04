package boj.swm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7576 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] dy = new int[]{-1, 0, 1, 0};
        int[] dx = new int[]{0, -1, 0, 1};

        int[][] board = new int[N][M];
        int[][] dist = new int[N][M];

        Queue<int[]> Q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) {
                    Q.add(new int[]{i, j});
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = -1;
                }
            }
        }

        while (!Q.isEmpty()) {
            int[] curr = Q.poll();
            int y = curr[0];
            int x = curr[1];

            for (int dir = 0; dir < 4; dir++) {
                int ny = y + dy[dir];
                int nx = x + dx[dir];
                if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
                if (dist[ny][nx] != -1 || board[ny][nx] == -1) continue;

                Q.add(new int[]{ny, nx});
                dist[ny][nx] = dist[y][x] + 1;
            }
        }

        int ret = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 0 && dist[i][j] == -1) {
                    System.out.println(-1);
                    return;
                } else {
                    ret = Math.max(ret, dist[i][j]);
                }
            }
        }

        System.out.println(ret);
    }
}
