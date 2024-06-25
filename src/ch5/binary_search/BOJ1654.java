package ch5.binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 랜선 자르기(S2)
public class BOJ1654 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        long[] arr = new long[k];
        long left = 1;
        long right = -1;
        for (int i = 0; i < k; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            right = Math.max(right, arr[i]);
        }

        while (left <= right) {
            long mid = (left + right) / 2;
            int cnt = 0;
            for (long lan : arr) {
                cnt += (int) (lan / mid);
            }

            if (cnt >= n) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(right);
    }
}
