package boj.number;

import java.util.Scanner;

public class BOJ13909 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int count = 0;
        for (int i = 1; i * i <= n; i++) count++;
        System.out.println(count);
    }
}

// 1, 4, 9, 16
