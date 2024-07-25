package ch12.CCW;

import java.util.Scanner;

// 선분 방향 구하기(G5)
public class p097 {
    static int[][] points;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        points = new int[3][2];
        for (int i = 0; i < 3; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();

            points[i] = new int[]{x, y};
        }

        int[] p1 = points[0];
        int[] p2 = points[1];
        int[] p3 = points[2];
        int CCW = (p1[0] * p2[1] + p2[0] * p3[1] + p3[0] * p1[1]) - (p2[0] * p1[1] + p3[0] * p2[1] + p1[0] * p3[1]);

        if(CCW < 0) System.out.println(-1);
        else if (CCW > 0) System.out.println(1);
        else System.out.println(0);

    }
}
