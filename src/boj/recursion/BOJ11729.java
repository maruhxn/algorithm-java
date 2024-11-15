package boj.recursion;

import java.util.Scanner;

/**
 * 하노이탑
 * <p>
 * 점화식: f(n) = 2f(n - 1) + 1
 * 옮기는 횟수: 2^n - 1
 */
public class BOJ11729 {
    static int result = 0;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int k = Integer.parseInt(new Scanner(System.in).nextLine());
        sb.append((int) Math.pow(2, k) - 1);
        move(k, 1, 3);
        System.out.println(sb.toString());
    }

    // n개의 원판을 from에서 to로 옮기는 함수
    public static void move(int n, int from, int to) {
        if (n == 0) return;

        move(n - 1, from, 6 - from - to);
        sb.append("\n").append(from + " " + to);
        move(n - 1, 6 - from - to, to);
    }
}
