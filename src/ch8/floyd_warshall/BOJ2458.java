package ch8.floyd_warshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 키 순서(G4)
/**
 * D[s][e] 또는 D[e][s]가 true면 s와 e 사이의 관계를 알 수 있다는 것!
 */
public class BOJ2458 {
    static int N, M;
    static boolean[][] D;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        D = new boolean[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            D[i][i] = true;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            D[s][e] = true;
        }

        for (int k = 1; k <= N; k++) {
            for (int s = 1; s <= N; s++) {
                for (int e = 1; e <= N; e++) {
                    if (D[s][e]) continue;
                    if (D[s][k] && D[k][e]) {
                        D[s][e] = true;
                    }
                }
            }
        }

        int result = 0;

        for (int i = 1; i <= N; i++) {
            boolean allTrue = true;
            for (int j = 1; j <= N; j++) {
                if (!D[i][j] && !D[j][i]) {
                    allTrue = false;
                    break;
                }
            }
            if(allTrue) result++;
        }

        System.out.println(result);
    }
}
