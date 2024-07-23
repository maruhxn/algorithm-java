package ch11.dynamic_programming;

import java.util.Scanner;

// 2 * N 타일링(S3)
public class p087 {
//    static long[] D;
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        D = new long[1001];
//
//        for (int i = 0; i <= 1000; i++) {
//            D[i] = -1;
//        }
//
//        D[1] = 1;
//        D[2] = 2;
//        fibo(n);
//        System.out.println(D[n]);
//    }
//
//    static long fibo(int n) {
//        if (D[n] != -1) return D[n]; // 기존에 계산한 적이 있는 부분의 문제는 재계산하지 않고 리턴
//        return D[n] = (fibo(n - 2) + fibo(n - 1)) % 10007; // 바로 리턴하지 않고 DP 테이블에 저장한 후 리턴
//    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long D[] = new long[1001];
        D[1] = 1;
        D[2] = 2;
        for (int i = 3; i <= n; i++) {
            D[i] = (D[i - 1] + D[i - 2]) % 10007;
        }

        System.out.println(D[n]);
    }
}
