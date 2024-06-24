package ch5.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// 알파벳(G4)

/**
 * dist라는 배열을 둘 필요 없이, DFS 함수 내에서 count라는 매개변수를 받도록 하면 됨.
 * DFS(int i, int j, int count) <<<<<< DFS(nx, ny, count + 1);
 * <p>
 * 그리고, visited 배열은 필요없음. 어차피 진행 가능여부는 Set에 해당 알파벳이 있는지 없는지가 중요.
 */
public class BOJ1987 {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static char[][] board;
    static Set<Character> hasAlpha;
    static int r, c, result;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        board = new char[r][c];
        dist = new int[r][c];
        result = 0;

        hasAlpha = new HashSet<>();
        for (int i = 0; i < r; i++) {
            board[i] = br.readLine().toCharArray();
        }

        dist[0][0] = 1;
        DFS(0, 0);
        System.out.println(result);
    }

    private static void DFS(int i, int j) {
        hasAlpha.add(board[i][j]);

        for (int dir = 0; dir < 4; dir++) {
            int nx = i + dx[dir];
            int ny = j + dy[dir];
            if (nx < 0 | nx >= r | ny < 0 | ny >= c) continue;
            if (hasAlpha.contains(board[nx][ny])) continue;
            dist[nx][ny] = dist[i][j] + 1;
            DFS(nx, ny);
        }

        hasAlpha.remove(board[i][j]);
        if (result < dist[i][j]) result = dist[i][j];
    }
}
