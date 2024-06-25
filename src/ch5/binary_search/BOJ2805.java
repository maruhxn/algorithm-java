package ch5.binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 나무 자르기(S2)

/**
 * 유형: 파라메트릭 서치
 */
public class BOJ2805 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] trees = new int[n];
        st = new StringTokenizer(br.readLine());
        int left = 0;
        int right = -1;
        for (int i = 0; i < n; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            right = Math.max(right, trees[i]);
        }

        while (left <= right) {
            int mid = (left + right) / 2;
            long total = 0;
            for (int tree : trees) {
                if (tree > mid) total += tree - mid;
            }

            if (total >= m) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(right);
    }
}
