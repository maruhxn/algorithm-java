package algospot.ch8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TILING2 {
    static int MOD = 1000000007;
    static int[] cache;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());
        while (c-- > 0) {
            int N = Integer.parseInt(br.readLine());
            cache = new int[101];

            for (int i = 0; i < 101; i++) {
                cache[i] = -1;
            }

            System.out.println(tiling(N));
        }
    }

    public static int tiling(int n) {
        // 기저 사례: n이 1 이하일 때
        if (n <= 1) return 1;

        int ret = cache[n];
        if (ret != -1) return ret;

        ret = (tiling(n - 1) + tiling(n - 2)) % MOD;

        return cache[n] = ret;
    }
}
