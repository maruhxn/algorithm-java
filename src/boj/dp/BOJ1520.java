package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1520 {

    static int M, N;
    static int[][] board, cache;
    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        board = new int[M][N];
        cache = new int[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                cache[i][j] = -1;
            }
        }

        System.out.println(getRouteCnt(0, 0));
    }

    private static int getRouteCnt(int y, int x) {
        if (y == M - 1 && x == N - 1) return 1;

        if (cache[y][x] != -1) return cache[y][x];

        int ret = 0;
        for (int dir = 0; dir < 4; dir++) {
            int ny = y + dy[dir];
            int nx = x + dx[dir];

            if (ny < 0 || ny >= M || nx < 0 || nx >= N) continue;
            if (board[y][x] <= board[ny][nx]) continue;

            ret += getRouteCnt(ny, nx);
        }

        return cache[y][x] = ret;
    }
}
