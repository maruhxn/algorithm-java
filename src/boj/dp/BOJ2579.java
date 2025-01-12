package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2579 {

    static int N;
    static int[] stairs;
    static int[][] cache;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        stairs = new int[N + 1];
        cache = new int[N + 1][3];
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < 3; j++) {
                cache[i][j] = -1;
            }
        }

        for (int i = 1; i <= N; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }

        int ret = game(0, 0);

        System.out.println(ret);
    }

    static int game(int curr, int cnt) {
        if (curr == N) return stairs[N];

        int ret = cache[curr][cnt];
        if (ret != -1) return ret;

        if (cnt < 2) ret = Math.max(ret, stairs[curr] + game(curr + 1, cnt + 1));
        if (N - curr > 1) ret = Math.max(ret, stairs[curr] + game(curr + 2, 1));

        return cache[curr][cnt] = ret;
    }
}