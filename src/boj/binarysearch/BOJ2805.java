package boj.binarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2805 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long M = Integer.parseInt(st.nextToken());
        long[] trees = new long[N];
        st = new StringTokenizer(br.readLine());

        long max = -1;
        for (int i = 0; i < N; i++) {
            trees[i] = Long.parseLong(st.nextToken());
            max = Math.max(max, trees[i]);
        }

        long left = 1;
        long right = max + 1;
        long mid;
        while (left < right) {
            mid = (left + right) / 2;

            long ret = 0;
            for (long tree : trees) {
                if (tree > mid) ret += (tree - mid);
            }

            if (ret < M) right = mid;
            else left = mid + 1;
        }

        System.out.println(left - 1);
    }
}
