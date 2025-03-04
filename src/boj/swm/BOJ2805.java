package boj.swm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2805 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] trees = new long[N];
        st = new StringTokenizer(br.readLine());
        long right = -1;
        for (int i = 0; i < N; i++) {
            trees[i] = Long.parseLong(st.nextToken());
            right = Math.max(right, trees[i]);
        }

        long left = 0;
        long mid;

        while (left < right) {
            mid = (left + right) / 2;

            long ret = 0;
            for (long tree : trees) {
                if (tree > mid) ret += (tree - mid);
            }

            if (ret <= M) right = mid;
            else left = mid + 1;
        }

        System.out.println(left - 1);
    }
}
