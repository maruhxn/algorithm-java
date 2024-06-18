package ch3.sliding_window;

import java.util.Scanner;

// 회전 초밥(S1)
public class BOJ2531 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 접시 수
        int d = sc.nextInt(); // 초밥 가짓 수
        int k = sc.nextInt(); // 연속해서 먹는 접시 수
        int c = sc.nextInt(); // 쿠폰 번호

        Integer[] A = new Integer[n];
        for (int i = 0; i < n; i++) {
            A[i] = sc.nextInt();
        }

        // 7 9 7 30 2 7 9 25
        // 2 7 9 25 7 9 7 30
        // 2, 7, 9, 25
        // 9, 7, 30, 2
    }
}
