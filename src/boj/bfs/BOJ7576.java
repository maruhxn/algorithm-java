package boj.bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7576 {

    static int[] dy = new int[]{0, -1, 0, 1};
    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[][] graph;
    static int[][] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        graph = new int[N][M];
        dist = new int[N][M];

        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                dist[i][j] = -1;
                if (graph[i][j] == 1) {
                    dist[i][j] = 0;
                    queue.add(new int[]{i, j});
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int y = cur[0];
            int x = cur[1];
            for (int dir = 0; dir < 4; dir++) {
                int ny = y + dy[dir];
                int nx = x + dx[dir];
                if (ny < 0 | nx < 0 || ny >= N || nx >= M) continue;
                if (graph[ny][nx] != 0) continue;
                if (dist[ny][nx] != -1) continue;
                dist[ny][nx] = dist[y][x] + 1;
                queue.add(new int[]{ny, nx});
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (graph[i][j] != -1 && dist[i][j] == -1) {
                    System.out.println(-1);
                    System.exit(0);
                }

                max = Math.max(max, dist[i][j]);
            }
        }

        System.out.println(max);
    }
}
