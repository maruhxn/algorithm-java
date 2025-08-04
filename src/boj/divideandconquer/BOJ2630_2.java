package boj.divideandconquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2630_2 {

    static int N;
    static int[][] graph;
    static int[] cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        cnt = new int[2];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(0, 0, N);
        System.out.println(String.valueOf(cnt[0]) + '\n' + cnt[1]);
    }

    private static void solve(int y, int x, int size) {
        boolean isSame = true;
        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                if (graph[y][x] != graph[i][j]) {
                    isSame = false;
                    break;
                }
            }
            if (!isSame) break;
        }

        if (isSame) {
            cnt[graph[y][x]]++;
        } else {
            int nextSize = size / 2;
            solve(y, x, nextSize);
            solve(y, x + nextSize, nextSize);
            solve(y + nextSize, x, nextSize);
            solve(y + nextSize, x + nextSize, nextSize);
        }
    }
}
