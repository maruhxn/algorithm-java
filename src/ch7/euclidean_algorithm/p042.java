package ch7.euclidean_algorithm;

import java.util.Scanner;

// 유클리드 호제법(B1)

/**
 * 최소공배수 = A * B / 최대 공약수
 */
public class p042 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        while (T-- > 0) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            System.out.println(a * b / gcd(a, b));
        }
    }

    private static int gcd(int a, int b) {
//        int c = Math.max(a, b);
//        int d = Math.min(a, b);
//        while (d != 0) {
//            int temp = d;
//            d = c % temp;
//            c = temp;
//        }
//
//        return c;

        if(b == 0) return a;
        else return gcd(b, a % b);
    }
}
