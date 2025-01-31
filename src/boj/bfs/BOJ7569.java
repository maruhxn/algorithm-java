package boj.bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7569 {

    static int[] dh = new int[]{1, -1, 0, 0, 0, 0};
    static int[] dy = new int[]{0, 0, 0, -1, 0, 1};
    static int[] dx = new int[]{0, 0, -1, 0, 1, 0};
    static int[][][] graph;
    static int[][][] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        graph = new int[H][N][M];
        dist = new int[H][N][M];

        Queue<int[]> queue = new LinkedList<>();

        for (int h = 0; h < H; h++) {
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    graph[h][i][j] = Integer.parseInt(st.nextToken());
                    dist[h][i][j] = -1;
                    if (graph[h][i][j] == 1) {
                        dist[h][i][j] = 0;
                        queue.add(new int[]{h, i, j});
                    }
                }
            }
        }


        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int h = cur[0];
            int y = cur[1];
            int x = cur[2];
            for (int dir = 0; dir < 6; dir++) {
                int nh = h + dh[dir];
                int ny = y + dy[dir];
                int nx = x + dx[dir];
                if (nh < 0 || ny < 0 || nx < 0 || nh >= H || ny >= N || nx >= M) continue;
                if (graph[nh][ny][nx] != 0) continue;
                if (dist[nh][ny][nx] != -1) continue;
                dist[nh][ny][nx] = dist[h][y][x] + 1;
                queue.add(new int[]{nh, ny, nx});
            }
        }

        int max = Integer.MIN_VALUE;
        for (int h = 0; h < H; h++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (graph[h][i][j] != -1 && dist[h][i][j] == -1) {
                        System.out.println(-1);
                        System.exit(0);
                    }

                    max = Math.max(max, dist[h][i][j]);
                }
            }
        }

        System.out.println(max);
    }
}
