package ch6.greedy;

import java.util.Scanner;

// 동전 개수의 최솟값 구하기(S4)
public class p032 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = sc.nextInt();
        }

        int count = 0;
        for (int i = n - 1; i >= 0; i--) {
//            while (A[i] <= k) {           // 반복적인 뺄셈 연산 = 나눗셈
//                k -= A[i];
//                count++;
//            }
            if (A[i] <= k) {
                count += (k / A[i]);
                k = k % A[i];
            }
        }
        System.out.println(count);
    }
}
