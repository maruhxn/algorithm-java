package boj.swm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ2667 {

    static int[] dy = new int[]{-1, 0, 1, 0};
    static int[] dx = new int[]{0, -1, 0, 1};
    static char[][] graph;
    static boolean[][] visited;
    static int N, ret;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        graph = new char[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            graph[i] = br.readLine().toCharArray();
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (graph[i][j] == '1' && !visited[i][j]) {
                    ret = 0;
                    dfs(i, j);
                    result.add(ret);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(result.size()).append("\n");
        result.stream().sorted().forEach(r -> sb.append(r).append("\n"));

        System.out.println(sb);
    }

    static void dfs(int y, int x) {
        visited[y][x] = true;
        ++ret;

        for (int dir = 0; dir < 4; dir++) {
            int ny = y + dy[dir];
            int nx = x + dx[dir];

            if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
            if (visited[ny][nx] || graph[ny][nx] == '0') continue;

            dfs(ny, nx);
        }
    }
}
