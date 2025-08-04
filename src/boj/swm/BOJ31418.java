package boj.swm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ31418 {

    static int MOD = 998_244_353;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        long ret = 1;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            long maxX, minX, maxY, minY;
            maxX = Math.min(x + T, W);
            maxY = Math.min(y + T, H);
            minX = Math.max(x - T, 1);
            minY = Math.max(y - T, 1);

            long width = (maxX - minX + 1) % MOD;
            long height = (maxY - minY + 1) % MOD;

            ret = (ret * width % MOD * height % MOD) % MOD;
        }

        System.out.println(ret);
    }
}
