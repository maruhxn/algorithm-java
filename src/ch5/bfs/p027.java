package ch5.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 미로탐색(S1)
public class p027 {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static boolean[][] visited;
    static int[][] A;
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        A = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) { // A 초기화
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for (int j = 0; j < m; j++) {
                A[i][j] = Integer.parseInt(line.substring(j, j + 1));
            }
        }

        BFS(0, 0);
        System.out.println(A[n-1][m-1]);
    }

    private static void BFS(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        visited[i][j] = true;
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            for (int dir = 0; dir < 4; dir++) {
                int nx = now[0] + dx[dir];
                int ny = now[1] + dy[dir];
                if(nx < 0 | nx >= n | ny < 0 | ny >= m) continue;
                if (A[nx][ny] != 0 && !visited[nx][ny]) { // 0이고 방문한적 없어야함.
                    queue.add(new int[]{nx, ny});
                    A[nx][ny] = A[now[0]][now[1]] + 1;
                    visited[nx][ny] = true;
                }
            }
        }
    }
}
