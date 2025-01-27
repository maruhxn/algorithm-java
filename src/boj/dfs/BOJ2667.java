package boj.dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ2667 {

    static int N, cnt;
    static int[] dy = new int[]{0, -1, 0, 1};
    static int[] dx = new int[]{-1, 0, 1, 0};
    static char[][] graph;
    static boolean[][] visited;
    static ArrayList<Integer> result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new char[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            graph[i] = br.readLine().toCharArray();
        }

        result = new ArrayList<>();
        int ret = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && graph[i][j] == '1') {
                    cnt = 0;
                    dfs(i, j);
                    result.add(cnt);
                    ret++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(ret).append("\n");
        result.sort((o1, o2) -> o1 - o2);
        for (int i = 0; i < result.size(); i++) {
            sb.append(result.get(i)).append("\n");
        }

        System.out.println(sb);
    }

    private static void dfs(int y, int x) {
        visited[y][x] = true;
        cnt++;

        for (int dir = 0; dir < 4; dir++) {
            int ny = y + dy[dir];
            int nx = x + dx[dir];
            if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
            if (graph[ny][nx] == '0' || visited[ny][nx]) continue;
            dfs(ny, nx);
        }
    }
}
