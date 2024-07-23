package ch11.dynamic_programming;

import java.util.Scanner;

// 이친수(S3)
// 피보나치로도 이차연배열로도 풀 수 있음
public class p086 {
//    static long[] D;
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        D = new long[n + 1];
//
//        for (int i = 0; i <= n; i++) {
//            D[i] = -1;
//        }
//
//        D[0] = 0;
//        D[1] = 1;
//        fibo(n);
//        System.out.println(D[n]);
//    }
//
//    static long fibo(int n) {
//        if (D[n] != -1) return D[n]; // 기존에 계산한 적이 있는 부분의 문제는 재계산하지 않고 리턴
//        return D[n] = fibo(n - 2) + fibo(n - 1); // 바로 리턴하지 않고 DP 테이블에 저장한 후 리턴
//    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long D[][] = new long[n + 1][2];
        D[1][1] = 1;
        D[1][0] = 0;

        for (int i = 2; i <= n; i++) {
            D[i][0] = D[i - 1][0] + D[i - 1][1];
            D[i][1] = D[i - 1][0];
        }

        System.out.println(D[n][0] + D[n][1]);
    }
}
