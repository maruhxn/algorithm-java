package boj.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14889_2 {

    static int N;
    static int min;
    static int[][] startLink;
    static boolean[] selected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        min = Integer.MAX_VALUE;
        startLink = new int[N][N];
        selected = new boolean[N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                startLink[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(0, 0);
        System.out.println(min);
    }

    private static void solve(int start, int cnt) {
        if (cnt == N / 2) {
            int gap = 0;

            for (int i = 0; i < N / 2; i++) {
                for (int j = 0; j < N / 2; j++) {
                    if (selected[i] && selected[j]) {
                        gap += startLink[i][j];
                    } else if (!selected[i] && !selected[j]) {
                        gap -= startLink[i][j];
                    }
                }
            }

            min = Math.min(min, Math.abs(gap));
        }

        for (int i = start; i < N; i++) {
            if (selected[i]) continue;
            selected[i] = true;
            solve(i + 1, cnt + 1);
            selected[i] = false;
        }
    }
}