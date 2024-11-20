package algospot.ch8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ASYMTILING {
    static int MOD = 1000000007;
    static int[] cache;
    static int[] synCache;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());
        while (c-- > 0) {
            int N = Integer.parseInt(br.readLine());
            cache = new int[101];
            synCache = new int[101];

            for (int i = 0; i < 101; i++) {
                cache[i] = -1;
            }

            for (int i = 0; i < 101; i++) {
                synCache[i] = -1;
            }

            synCache[1] = 1;
            synCache[2] = 2;
            synCache[3] = 1;
            synCache[4] = 3;


            int result = (tiling(N) - synTiling(N) + MOD) % MOD;

            System.out.println(result);
        }
    }

    // 2 * n 크기의 사각형을 타일로 덮는 방법의 수 반환
    public static int tiling(int n) {
        // 기저 사례: n이 1 이하일 때
        if (n <= 1) return 1;

        int ret = cache[n];
        if (ret != -1) return ret;

        ret = (tiling(n - 1) + tiling(n - 2)) % MOD;

        return cache[n] = ret;
    }

    public static int synTiling(int n) {
        int ret = synCache[n];
        if (ret != -1) return ret;

        ret = (synTiling(n - 2) + synTiling(n - 4)) % MOD;

        return synCache[n] = ret;
    }
}
