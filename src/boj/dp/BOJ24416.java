package boj.dp;

import java.util.Scanner;

public class BOJ24416 {
    static int a;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        fib(n);

        System.out.println(a + " " + (n - 2));
    }

    static int fib(int n) {
        if (n == 1 || n == 2) {
            a++;
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }
}
