package algospot.ch17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 실패.. 다음에 다시 보자
public class CHRISTMAS {
    static int N, K;
    static int[] boxes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(br.readLine());
        while (C-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            boxes = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                boxes[i] = Integer.parseInt(st.nextToken());
            }

            int[] pSum = new int[N];
            pSum[0] = boxes[0];
            for (int i = 1; i < N; i++) {
                pSum[i] = (boxes[i] % K + pSum[i - 1]) % K; // 중요
            }

            System.out.println(waysToBuy(pSum, K));
            System.out.println(maxBuys(pSum, K));
        }
    }

    public static int waysToBuy(int[] psum, int k) {
        int MOD = 20091101;

        int ret = 0;

        long[] count = new long[k];

        for (int i = 0; i < psum.length; i++) {
            count[psum[i]]++;
        }

        for (int i = 0; i < k; i++) {
            if (count[i] >= 2) ret = (int) (ret + ((count[i] * (count[i] - 1) / 2)) % MOD);
        }

        return ret;
    }

    public static int maxBuys(int[] psum, int k) {
        int[] ret = new int[psum.length];

        int[] prev = new int[k];
        Arrays.fill(prev, -1);

        for (int i = 0; i < psum.length; i++) {
            if (i > 0) ret[i] = ret[i - 1];
            else ret[i] = 0;

            int loc = prev[psum[i]];
            if (loc != -1) ret[i] = Math.max(ret[i], ret[loc] + 1);
            prev[psum[i]] = i;
        }

        return ret[ret.length - 1];
    }
}
