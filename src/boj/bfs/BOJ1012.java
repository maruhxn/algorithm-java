package boj.bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1012 {

    static int N, M;
    static int[] dy = new int[]{0, -1, 0, 1};
    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[][] graph;
    static boolean[][] discovered;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            graph = new int[N][M];
            discovered = new boolean[N][M];

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                graph[y][x] = 1;
            }


            int ret = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (graph[i][j] == 1 && !discovered[i][j]) {
                        bfs(i, j);
                        ret++;
                    }
                }
            }

            sb.append(ret).append("\n");
        }

        System.out.println(sb);
    }

    private static void bfs(int y, int x) {
        Queue<int[]> queue = new LinkedList<>();
        discovered[y][x] = true;
        queue.add(new int[]{y, x});

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int currY = curr[0];
            int currX = curr[1];
            for (int dir = 0; dir < 4; dir++) {
                int ny = currY + dy[dir];
                int nx = currX + dx[dir];
                if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
                if (graph[ny][nx] == 0 || discovered[ny][nx]) continue;
                queue.add(new int[]{ny, nx});
                discovered[ny][nx] = true;
            }
        }
    }
}
