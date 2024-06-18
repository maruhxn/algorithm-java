package ch3.prefix_sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 수들의 합 2(S4)
public class BOJ2003 {
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long m = Long.parseLong(st.nextToken());
        int[] A = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            A[i] = A[i - 1] + Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 1;
        while (right <= n) {
            if (A[right] - A[left] == m) {
                result++;
                left++;
                right++;
            } else if (A[right] - A[left] < m) {
                right++;
            } else {
                left++;
            }
        }
        System.out.println(result);
    }
}
