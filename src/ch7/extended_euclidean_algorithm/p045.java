package ch7.extended_euclidean_algorithm;

import java.util.Scanner;

// Ax + By = C(G1)
public class p045 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        long gcd = gcd(a, b);

        if (c % gcd != 0) {
            System.out.println(-1);

        } else {
            int k = (int) (c / gcd);
            long[] ret = execute(a, b);
            System.out.println(ret[0] * k + " " + ret[1] * k);
        }
    }

    private static long[] execute(int a, int b) {
        long[] ret = new long[2];
        if (b == 0) {
            ret[0] = 1;
            ret[1] = 0;
            return ret;
        }
        long q = a / b;
        long[] v = execute(b, a % b); // 재귀 형태로 확장 유클리드 호제법 수행
        ret[0] = v[1];
        ret[1] = v[0] - v[1] * q;
        return ret;
    }

    private static int gcd(int a, int b) {
        if (b == 0) return a;
        else return gcd(b, a % b);
    }
}