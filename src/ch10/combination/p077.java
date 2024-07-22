package ch10.combination;

import java.util.Scanner;

// 이항계수 2(S2)

/**
 * 모듈러 연산: (A mod N + B mod N)mod N = (A + B) mod N
 */
public class p077 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int r = sc.nextInt();
        int[][] D = new int[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            D[i][1] = i;
            D[i][0] = 1;
            D[i][i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                D[i][j] = D[i - 1][j] + D[i - 1][j - 1];
                D[i][j] %= 10007;
            }
        }

        System.out.println(D[n][r]);
    }
}
