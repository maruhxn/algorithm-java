package ch3.two_pointer;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

// 먹을 것인가 먹힐 것인가(S3)

/**
 * 이분 탐색으로도 풀 수 있음. (B만 정렬)
 */
public class BOJ7795 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            Integer[] A = new Integer[n];
            Integer[] B = new Integer[m];

            for (int i = 0; i < n; i++) {
                A[i] = sc.nextInt();
            }

            for (int i = 0; i < m; i++) {
                B[i] = sc.nextInt();
            }

            Arrays.sort(A, Collections.reverseOrder());
            Arrays.sort(B, Collections.reverseOrder());

            int count = 0;
            int st_A = 0;
            int st_B = 0;
            while (st_A < n && st_B < m) {
                if (A[st_A] > B[st_B]) {
                    count += m - st_B;
                    st_A++;
                } else {
                    st_B++;
                }
            }
            System.out.println(count);
        }
    }
}
