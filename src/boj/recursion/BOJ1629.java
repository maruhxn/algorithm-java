package boj.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1629 {
    // depth 매우 크다보니, 시간 초과 & 메모리 초과 발생할 것
//    public static void main(String[] args) throws IOException {
//        StringTokenizer st = new StringTokenizer(new BufferedReader(new InputStreamReader(System.in)).readLine());
//        long a = Integer.parseInt(st.nextToken());
//        long b = Integer.parseInt(st.nextToken());
//        long c = Integer.parseInt(st.nextToken());
//
//        System.out.println(solve(a, b, c));
//    }
//
//    private static long solve(long a, long b, long c) {
//        if (b == 1) return a % c;
//
//        return a * solve(a, b - 1, c) % c;
//    }

    // 분할 정복 (feat. 지수 법칙 & 모듈러 곱셈)
    /**
     * f(x) = a^x % c
     * x가 짝수 = f(x / 2)f(x / 2) % c
     * x가 홀수 = f(x - 1)f(1) % c = f((x - 1) / 2)f((x - 1) / 2) % c * f(1) % c
     */
    static long a, b, c;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(new BufferedReader(new InputStreamReader(System.in)).readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        System.out.println(fpow(b));
    }

    private static long fpow(long exponent) {
        if (exponent == 1) return a % c;

        long k = fpow(exponent / 2) % c;
        if (exponent % 2 == 0) return k * k % c;
        return ((k * k % c) * (a % c)) % c;
    }
}
