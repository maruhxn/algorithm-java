package ch11.dynamic_programming;

import java.util.Scanner;

// 퇴사(S3)
// D[i]: i일 ~ 퇴사일까지 벌 수 있는 최대수입
// 더 큰 수입을 벌 수 있다면 굳이 매일 일하지 않아도 된다. 즉, 수입을 위해 쉬는 날이 있을 수 있다.
public class p085 {
    static Item[] table;
    static int[] D;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        table = new Item[n + 1];
        D = new int[n + 2];

        for (int i = 1; i <= n; i++) {
            int t = sc.nextInt();
            int p = sc.nextInt();
            table[i] = new Item(t, p);
        }

        // DP
        for (int i = n; i >= 1; i--) {
            int t = table[i].t;
            int p = table[i].p;
            if (t + i <= n + 1) D[i] = Math.max(p + D[t + i], D[i + 1]);
            else D[i] = D[i + 1];
        }

        System.out.println(D[1]);
    }

    static class Item {
        int t, p;

        public Item(int t, int p) {
            this.t = t;
            this.p = p;
        }
    }
}
