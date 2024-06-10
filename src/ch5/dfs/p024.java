package ch5.dfs;

import java.util.Scanner;

// 신기한 소수(G5)
/**
 * 2, 3, 5, 7
 * 소수 판단 알고리즘 + 재귀 함수 DFS
 */
public class p024 {
    static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        DFS(2, 1);
        DFS(3, 1);
        DFS(5, 1);
        DFS(7, 1);
    }

    private static void DFS(int num, int jaritsu) {
        if (jaritsu == n) {
            if (isPrime(num)) System.out.println(num); // 소수면 출력
            return;
        }

        for (int i = 1; i <= 9; i = i + 2) {
            int target = num * 10 + i;
            if (isPrime(target)) {
                DFS(target, jaritsu + 1);
            }
        }
    }

    static boolean isPrime(int n) {
        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
