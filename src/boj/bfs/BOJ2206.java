package boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2206 {

    static int N, M;
    static int[] dy = new int[]{-1, 0, 1, 0};
    static int[] dx = new int[]{0, 1, 0, -1};
    static char[][] graph;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new char[N][M];

        for (int i = 0; i < N; i++) {
            graph[i] = br.readLine().toCharArray();
        }

        int ret = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (graph[i][j] == '1') {
                    graph[i][j] = '0';

                    dist = new int[N][M];
                    for (int k = 0; k < N; k++) {
                        Arrays.fill(dist[k], -1);
                    }

                    bfs(0, 0);

                    if (dist[N - 1][M - 1] != -1) {
                        ret = Math.min(ret, dist[N - 1][M - 1] + 1);
                    }

                    graph[i][j] = '1';
                }
            }
        }

        System.out.println(ret == Integer.MAX_VALUE ? -1 : ret);
    }

    static void bfs(int startY, int startX) {
        Queue<int[]> queue = new LinkedList<>();
        dist[startY][startX] = 0;
        queue.add(new int[]{startY, startX});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int y = cur[0];
            int x = cur[1];

            for (int dir = 0; dir < 4; dir++) {
                int ny = y + dy[dir];
                int nx = x + dx[dir];
                if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
                if (graph[ny][nx] == '1' || dist[ny][nx] != -1) continue;
                dist[ny][nx] = dist[y][x] + 1;
                queue.add(new int[]{ny, nx});
            }
        }
    }
}
