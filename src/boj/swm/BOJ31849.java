package boj.swm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ31849 {

    static int N, M;
    static int[][] board, dist;
    static int[] dy = new int[]{-1, 0, 1, 0};
    static int[] dx = new int[]{0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken()); // 방 개수
        int C = Integer.parseInt(st.nextToken()); // 편의점 개수

        board = new int[N + 1][M + 1];
        dist = new int[N + 1][M + 1];

        // 거리 배열을 무한대로 초기화
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], -1);
        }

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken()); // 월세

            board[a][b] = p;
        }

        Queue<int[]> queue = new LinkedList<>();

        // 편의점 위치를 큐에 넣고, 시작 거리 0으로 설정
        for (int i = 0; i < C; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            queue.add(new int[]{c, d});
            dist[c][d] = 0;
        }

        // 다중 시작점 BFS
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int y = curr[0];
            int x = curr[1];

            for (int dir = 0; dir < 4; dir++) {
                int ny = y + dy[dir];
                int nx = x + dx[dir];
                if (ny <= 0 || nx <= 0 || ny > N || nx > M) continue;
                if (dist[ny][nx] != -1) continue;
                dist[ny][nx] = dist[y][x] + 1;
                queue.add(new int[]{ny, nx});
            }
        }

        // 편세권 점수 계산
        int ret = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (board[i][j] > 0) {  // 방이 존재하는 위치만
                    ret = Math.min(ret, board[i][j] * dist[i][j]);
                }
            }
        }

        System.out.println(ret);
    }
}
