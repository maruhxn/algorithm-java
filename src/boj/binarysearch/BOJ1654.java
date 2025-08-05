package boj.binarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1654 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        long max = -1;
        long[] lines = new long[K];
        for (int i = 0; i < K; i++) {
            lines[i] = Long.parseLong(br.readLine());
            max = Math.max(max, lines[i]);
        }

        long left = 1;
        long right = max + 1;
        long mid;

        while (left < right) {
            mid = (left + right) / 2;

            long ret = 0;
            for (long line : lines) {
                ret += line / mid;
            }

            if (ret < N) right = mid;
            else left = mid + 1;
        }

        System.out.println(left - 1);
    }
}
