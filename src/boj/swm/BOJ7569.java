package boj.swm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7569 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[] dy = new int[]{-1, 0, 1, 0, 0, 0};
        int[] dx = new int[]{0, -1, 0, 1, 0, 0};
        int[] dz = new int[]{0, 0, 0, 0, 1, -1};

        int[][][] board = new int[H][N][M];
        int[][][] dist = new int[H][N][M];

        Queue<int[]> Q = new LinkedList<>();

        for (int h = 0; h < H; h++) {
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    board[h][i][j] = Integer.parseInt(st.nextToken());
                    if (board[h][i][j] == 1) {
                        Q.add(new int[]{h, i, j});
                        dist[h][i][j] = 0;
                    } else {
                        dist[h][i][j] = -1;
                    }
                }
            }
        }

        while (!Q.isEmpty()) {
            int[] curr = Q.poll();
            int z = curr[0];
            int y = curr[1];
            int x = curr[2];

            for (int dir = 0; dir < 6; dir++) {
                int nz = z + dz[dir];
                int ny = y + dy[dir];
                int nx = x + dx[dir];
                if (nz < 0 || nx < 0 || ny < 0 || nx >= M || ny >= N || nz >= H) continue;
                if (dist[nz][ny][nx] != -1 || board[nz][ny][nx] == -1) continue;

                Q.add(new int[]{nz, ny, nx});
                dist[nz][ny][nx] = dist[z][y][x] + 1;
            }
        }

        int ret = -1;
        for (int h = 0; h < H; h++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (board[h][i][j] == 0 && dist[h][i][j] == -1) {
                        System.out.println(-1);
                        return;
                    } else {
                        ret = Math.max(ret, dist[h][i][j]);
                    }
                }
            }

        }
        System.out.println(ret);
    }
}
