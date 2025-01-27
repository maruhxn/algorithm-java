package boj.bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2178 {

    static int N, M;
    static int[] dy = new int[]{0, -1, 0, 1};
    static int[] dx = new int[]{-1, 0, 1, 0};
    static char[][] graph;
    static int[][] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new char[N][M];
        dist = new int[N][M];
        for (int i = 0; i < N; i++) {
            graph[i] = br.readLine().toCharArray();
            Arrays.fill(dist[i], -1);
        }

        bfs(0, 0);
        System.out.println(dist[N - 1][M - 1] + 1);
    }

    private static void bfs(int startY, int startX) {
        Queue<int[]> queue = new LinkedList<>();
        dist[startY][startX] = 0;
        queue.add(new int[]{startY, startX});

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int y = curr[0];
            int x = curr[1];
            for (int dir = 0; dir < 4; dir++) {
                int ny = y + dy[dir];
                int nx = x + dx[dir];
                if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
                if (dist[ny][nx] >= 0 || graph[ny][nx] == '0') continue;
                queue.add(new int[]{ny, nx});
                dist[ny][nx] = dist[y][x] + 1;
            }
        }
    }
}
