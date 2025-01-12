package boj.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1149 {

    static int[][] house, cache;
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        house = new int[N][3];
        cache = new int[N][3];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                cache[i][j] = Integer.MAX_VALUE;
            }
        }

        StringTokenizer st;
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; ++j) {
                house[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ret = Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            ret = Math.min(ret, selectColor(0, i));
        }

        System.out.println(ret);
    }

    static int selectColor(int depth, int selected) {
        if (depth == N - 1) return house[depth][selected];

        int ret = cache[depth][selected];
        if (ret != Integer.MAX_VALUE) return ret;

        for (int color = 0; color < 3; ++color) {
            if (color != selected)
                ret = Math.min(ret, house[depth][selected] + selectColor(depth + 1, color));
        }

        return cache[depth][selected] = ret;
    }
}
