package boj.swm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2178 {

    static int[] dy = new int[]{-1, 0, 1, 0};
    static int[] dx = new int[]{0, -1, 0, 1};
    static char[][] graph;
    static int[][] dist;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new char[N][M];
        dist = new int[N][M];
        for (int i = 0; i < N; i++) {
            graph[i] = br.readLine().toCharArray();
        }

        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{0, 0});
        dist[0][0] = 1;

        while(!queue.isEmpty()) {
            int[] curr = queue.poll();
            int y = curr[0];
            int x = curr[1];
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (nx < 0 || nx >= M || ny < 0 || ny >= N) continue;
                if(dist[ny][nx] != 0 || graph[ny][nx] == '0') continue;

                queue.add(new int[]{ny, nx});
                dist[ny][nx] = 1 + dist[y][x];
            }
        }

        System.out.println(dist[N - 1][M - 1]);
    }
}
