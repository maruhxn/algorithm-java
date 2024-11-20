package algospot.ch8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 실패..
public class POLY {

    static int MOD = 10_000_000;
    static int[][] cache;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());
        while (c-- > 0) {
            int n = Integer.parseInt(br.readLine());
            cache = new int[101][101];

            for (int i = 0; i < 101; i++) {
                for (int j = 0; j < 101; j++) {
                    cache[i][j] = -1;
                }
            }

            int result = 0;

            for (int i = 1; i <= n; i++) {
                result += poly(i, n - i);
                if (i != 1) {
                    result %= MOD;
                }
            }

            System.out.println(result);
        }
    }

    public static int poly(int k, int n) {
        if (n == 0) return 1;
        if (n == 1) return k;
        if (n == 2) return 2 * k + 1;

        int ret = cache[k][n];
        if (ret != -1) return ret;

        ret = 0;

        for (int i = 1; i <= n; i++) {
            ret += (k + i - 1) * poly(i, n - i);
        }

        return cache[k][n] = ret % MOD;
    }
}
