package ch10.combination;

import java.util.Scanner;

// 다리 놓기(S5)
public class p079 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            int[][] D = new int[m + 1][m + 1];

            for (int i = 0; i <= m; i++) {
                D[i][1] = i;
                D[i][0] = 1;
                D[i][i] = 1;
            }

            for (int i = 2; i <= m; i++) {
                for (int j = 1; j < i; j++) {
                    D[i][j] = D[i - 1][j] + D[i - 1][j - 1];
                }
            }

            System.out.println(D[m][n]);
        }
    }
}
