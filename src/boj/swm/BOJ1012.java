package boj.swm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1012 {

    static boolean[][] board;
    static boolean[][] visited;
    static int[] dy = new int[]{-1, 0, 1, 0};
    static int[] dx = new int[]{0, -1, 0, 1};
    static int M, N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            board = new boolean[M][N];
            visited = new boolean[M][N];

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                board[a][b] = true;
            }

            int ret = 0;
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j] && !visited[i][j]) {
                        ++ret;
                        dfs(i, j);
                    }
                }
            }

            sb.append(ret).append("\n");
        }

        System.out.println(sb);
    }

    static void dfs(int y, int x) {
        visited[y][x] = true;
        for (int dir = 0; dir < 4; dir++) {
            int ny = y + dy[dir];
            int nx = x + dx[dir];

            if (ny < 0 || nx < 0 || ny >= M || nx >= N) continue;
            if (visited[ny][nx] || !board[ny][nx]) continue;

            dfs(ny, nx);
        }
    }
}
