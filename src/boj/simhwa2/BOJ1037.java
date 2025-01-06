package boj.simhwa2;

import java.util.Scanner;

public class BOJ1037 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int min = Integer.MAX_VALUE;
        int max = -1;
        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
             min = Math.min(min, num);
             max = Math.max(max, num);
        }

        System.out.println(min * max);
    }
}
