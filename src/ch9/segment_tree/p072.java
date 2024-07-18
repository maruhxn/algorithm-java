package ch9.segment_tree;

import java.util.Scanner;

// 최솟값 찾기 2(G1)
public class p072 {
    static long[] tree;
    static int leaf_start_index;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int t = 1;
        while (true) {
            if (Math.pow(2, t) >= n) break;
            else t++;
        }

        leaf_start_index = (int) Math.pow(2, t);
        tree = new long[(int) (leaf_start_index * 2)];
        for (int i = 0; i < n; i++) { // 리프노드 채우기
            tree[(int) leaf_start_index + i] = sc.nextLong();
        }
        for (int i = (int) (leaf_start_index - 1); i > 0; i--) {
            tree[i] = Math.min(tree[2 * i], tree[2 * i + 1]);
        }

        for (int i = 0; i < m; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            printMinValue(s, e);
        }
    }

    private static void printMinValue(int s, int e) {
        long start_index = s + leaf_start_index - 1;
        long end_index = e + leaf_start_index - 1;

        long min = Long.MAX_VALUE;
        while (end_index >= start_index) {
            if (start_index % 2 == 1) min = Math.min(tree[(int) start_index], min);
            if (end_index % 2 == 0) min = Math.min(tree[(int) end_index], min);
            start_index = (start_index + 1) / 2;
            end_index = (end_index - 1) / 2;
        }

        System.out.println(min);
    }
}
