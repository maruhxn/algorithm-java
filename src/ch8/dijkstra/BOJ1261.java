package ch8.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 알고스팟(G4)
public class BOJ1261 {
    static int N, M;
    static int[][] board;
    static int[][] dist;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        board = new int[N + 1][M + 1];
        dist = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 1; j <= M; j++) {
                dist[i][j] = Integer.MAX_VALUE;
                board[i][j] = chars[j - 1] - '0';
            }
        }

        dijkstra();

        System.out.println(dist[N][M]);
    }

    static void dijkstra() {
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(1, 1, 0));
        dist[1][1] = 0;

        while (!q.isEmpty()) {
            Node now = q.poll();
            int x = now.x;
            int y = now.y;
            int w = now.w;
            if(dist[x][y] < w) continue;
            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if (nx <= 0 | nx > N | ny <= 0 | ny > M) continue;
                if (board[nx][ny] == 0) { // 빈 방인 경우
                    if (dist[nx][ny] > dist[x][y]) {
                        // 벽을 부시진 않고, 기존 값과 새로운 값 중 더 작은 값으로 갱신
                        dist[nx][ny] = dist[x][y];
                        q.add(new Node(nx, ny, dist[nx][ny]));
                    }
                } else { // 벽의 경우
                    if (dist[nx][ny] > dist[x][y] + 1) {
                        // [기존의 부신 벽의 횟수(dist[nx][ny])]과 [현재까지 이동하면서 부신 벽의 횟수 + 1] 중 더 작은 값으로 갱신
                        dist[nx][ny] = dist[x][y] + 1;
                        q.add(new Node(nx, ny, dist[nx][ny]));
                    }
                }

            }
        }
    }

    private static class Node implements Comparable<Node> {
        int x, y, w;

        public Node(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }
}
