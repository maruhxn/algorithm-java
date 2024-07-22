package ch10.combination;

import java.util.Scanner;

// 부녀회장이 될테야(B1)
public class p078 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int k = sc.nextInt();
            int n = sc.nextInt();
            int[][] D = new int[k + 1][n + 1];

            for (int i = 0; i <= k; i++) {
                D[i][1] = 1;
            }

            for (int i = 1; i <= n; i++) {
                D[0][i] = i;
            }

            for (int i = 1; i <= k; i++) {
                for (int j = 1; j <= n; j++) {
                    D[i][j] = D[i - 1][j] + D[i][j - 1];
                }
            }

            System.out.println(D[k][n]);
        }
    }
}
