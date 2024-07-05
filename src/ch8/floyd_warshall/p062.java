package ch8.floyd_warshall;

import java.util.Scanner;

// 경로 찾기(S1)
public class p062 {

    static boolean[][] D;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        D = new boolean[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                D[i][j] = sc.nextInt() != 0;
            }
        }

        for (int k = 0; k < n; k++) {
            for (int s = 0; s < n; s++) {
                for (int e = 0; e < n; e++) {
                    if (D[s][e]) continue;
                    if (D[s][k] && D[k][e]) D[s][e] = true;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(D[i][j] ? "1 " : "0 ");
            }
            System.out.println();
        }
    }
}
