package boj.swm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1654 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        long[] lines = new long[K];
        long left = 0, right = -1;
        for (int i = 0; i < K; i++) {
            lines[i] = Integer.parseInt(br.readLine());
            right = Math.max(right, lines[i]);
        }

        right++;

        // upper bound 찾기
        while (left < right) {
            long mid = (left + right) / 2;

            int cnt = 0;
            for (long line : lines) {
                cnt += (line / mid);
            }

            if (cnt < N) right = mid;
            else {
                left = mid + 1;
            }
        }

        // UpperBound로 얻어진 값(min)에 -1이 최대 길이가 된다.
        System.out.println(left - 1);
    }
}
