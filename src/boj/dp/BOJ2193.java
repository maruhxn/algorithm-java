package boj.dp;

import java.util.Scanner;

// 이친수
public class BOJ2193 {
    static long[] cache;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        cache = new long[91];
        for (int i = 0; i < 91; i++) {
            cache[i] = -1;
        }

        System.out.println(pinary(n));
    }

    private static long pinary(int n) {
        if (n == 1 || n == 2) return 1;

        long ret = cache[n];
        if (ret != -1) return ret;

        ret = pinary(n - 1) + pinary(n - 2);

        return cache[n] = ret;
    }
}

/**
 * f(n) = f(n - 1) + f(n - 2)
 * <p>
 * f(n - 1): 길이가 n이고 마지막 자리가 0인 이친수 개수
 * f(n - 2): 길이가 n이고 마지막 자리가 1인 이친수 개수
 * <p>
 * 기저 사례: n == 1이거나 n==2인 경우에는 1 반환
 * <p>
 * 자료형 범위 주의!
 */