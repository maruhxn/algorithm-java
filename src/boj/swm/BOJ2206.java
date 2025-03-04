package boj.swm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2206 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] dy = new int[]{-1, 0, 1, 0};
        int[] dx = new int[]{0, 1, 0, -1};

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] board = new char[N][M];
        int[][][] dist = new int[N][M][2];

        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                dist[i][j][0] = -1;
                dist[i][j][1] = -1;
            }
        }

        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{0, 0, 0});
        dist[0][0][0] = 1;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int y = curr[0];
            int x = curr[1];
            int flag = curr[2];

            if (y == N - 1 && x == M - 1) {
                System.out.println(dist[y][x][flag]);
                return;
            }

            for (int dir = 0; dir < 4; dir++) {
                int ny = y + dy[dir];
                int nx = x + dx[dir];
                if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
                if (board[ny][nx] == '1' && flag == 0 && dist[ny][nx][1] == -1) {
                    queue.add(new int[]{ny, nx, 1});
                    dist[ny][nx][1] = dist[y][x][flag] + 1;
                }
                if (board[ny][nx] == '0' && dist[ny][nx][flag] == -1) {
                    queue.add(new int[]{ny, nx, flag});
                    dist[ny][nx][flag] = dist[y][x][flag] + 1;
                }
            }
        }


        if (dist[N - 1][M - 1][0] != -1 && dist[N - 1][M - 1][1] != -1) {
            System.out.println(Math.min(dist[N - 1][M - 1][0], dist[N - 1][M - 1][1]));
        } else {
            System.out.println(Math.max(dist[N - 1][M - 1][0], dist[N - 1][M - 1][1]));
        }
    }
}
