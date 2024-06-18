package ch3.prefix_sum;

import java.util.Arrays;
import java.util.Scanner;

// 수열(S3)
public class BOJ2559 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int maxResult = Integer.MIN_VALUE;
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = sc.nextInt();
        }

        if (k == 1) {
            int max = Arrays.stream(A).max().getAsInt();
            System.out.println(max);
            return;
        }

        int left = 0;
        int right = k - 1;

        while (right < n) {
            int sum = 0;
            for (int i = left; i <= right; i++) {
                sum += A[i];
            }
            if (maxResult < sum) {
                maxResult = sum;
            }
            left++;
            right++;
        }

        System.out.println(maxResult);
    }
}
