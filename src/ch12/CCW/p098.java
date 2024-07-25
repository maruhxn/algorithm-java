package ch12.CCW;

import java.util.Scanner;

// 선분의 교차 여부 구하기(G2)z
public class p098 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long x1 = sc.nextLong();
        long y1 = sc.nextLong();

        long x2 = sc.nextLong();
        long y2 = sc.nextLong();

        long x3 = sc.nextLong();
        long y3 = sc.nextLong();

        long x4 = sc.nextLong();
        long y4 = sc.nextLong();

        long abc = CCW(x1, y1, x2, y2, x3, y3);
        long abd = CCW(x1, y1, x2, y2, x4, y4);
        long cda = CCW(x3, y3, x4, y4, x1, y1);
        long cdb = CCW(x3, y3, x4, y4, x2, y2);

        long result1 = abc * abd;
        long result2 = cda * cdb;

        if (result1 == 0 && result2 == 0) {
            // 무한히 연장 했을 때 일직선 위에 있음.
            if (Math.min(x1, x2) <= Math.max(x3, x4) && Math.min(x3, x4) <= Math.max(x1, x2)
                    && Math.min(y1, y2) <= Math.max(y3, y4) && Math.min(y3, y4) <= Math.max(y1, y2)
            ) {
                // 겹침
                System.out.println(1);
            } else {
                // 안 겹침
                System.out.println(0);
            }
        } else if (result1 <= 0 && result2 <= 0) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }


    }

    static long CCW(long x1, long y1, long x2, long y2, long x3, long y3) {
        long temp = (x1 * y2 + x2 * y3 + x3 * y1) - (x2 * y1 + x3 * y2 + x1 * y3);
        if(temp > 0) return 1;
        else if(temp < 0) return -1;
        else return 0;
    }
}
