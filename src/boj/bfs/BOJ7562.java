package boj.bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7562 {

    static int[] dy = new int[]{-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] dx = new int[]{-2, -1, 1, 2, 2, 1, -1, -2};
    static int[][] dist;
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());

            dist = new int[N][N];

            for (int i = 0; i < N; i++) {
                Arrays.fill(dist[i], -1);
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            int curY = Integer.parseInt(st.nextToken());
            int curX = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int targetY = Integer.parseInt(st.nextToken());
            int targetX = Integer.parseInt(st.nextToken());

            bfs(curY, curX);

            sb.append(dist[targetY][targetX]).append("\n");
        }

        System.out.println(sb);
    }

    private static void bfs(int startY, int startX) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startY, startX});
        dist[startY][startX] = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int y = cur[0];
            int x = cur[1];
            for (int dir = 0; dir < 8; dir++) {
                int ny = y + dy[dir];
                int nx = x + dx[dir];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (dist[ny][nx] != -1) continue;
                queue.add(new int[]{ny, nx});
                dist[ny][nx] = dist[y][x] + 1;
            }
        }
    }
}
