package ch8.floyd_warshall;

import java.util.Scanner;

// 가장 빠른 버스 노선 구하기(G4)

/**
 * Integer.MAX_VALUE로 설정한다면,
 * D[s][k] + D[k][e] 계산 시 오버플로우가 발생해, 큰 값인데도 음수가 되어버림..
 */
public class p061 {
    static int[][] D;
    static int MAX_VALUE = 10000001; // 충분히 큰 수

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        // 최단 거리 배열 초기화
        D = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                D[i][j] = MAX_VALUE;
            }
        }

        // 최단 거리 배열에 그래프 데이터 저장 -> 인접 행렬 표현
        for (int i = 0; i < m; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            int w = sc.nextInt();
            if (D[s][e] > w) D[s][e] = w;
        }

        // 플로이드-워셜 알고리즘 적용
        for (int k = 1; k <= n; k++) {
            for (int s = 1; s <= n; s++) {
                for (int e = 1; e <= n; e++) {
                    D[s][e] = Math.min(D[s][e], D[s][k] + D[k][e]);
                }
            }
        }

        // 출력
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (D[i][j] == MAX_VALUE) System.out.print("0 ");
                else System.out.print(D[i][j] + " ");
            }
            System.out.println();
        }
    }
}
