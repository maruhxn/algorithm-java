package ch9.segment_tree;

import java.util.Scanner;

// 구간 합 구하기 3
public class p071 {
    static int t = 1;
    static int leaf_start_index;
    static long[] tree;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 수의 개수
        int m = sc.nextInt(); // 변경 횟수
        int k = sc.nextInt(); // 구간 합 구하는 횟수

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
            tree[i] = tree[2 * i] + tree[2 * i + 1];
        }

        for (int i = 0; i < m + k; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            long c = sc.nextLong();

            if (a == 1) {
                changeValue(b, c);
            } else {
                printResult(b, c);
            }
        }
    }

    private static void printResult(int s, long e) {
        long start_index = getTreeIndex(s);
        long end_index = getTreeIndex(e);

        long result = 0;
        while (end_index >= start_index) {
            if (start_index % 2 == 1) result += tree[(int) start_index];
            if (end_index % 2 == 0) result += tree[(int) end_index];
            start_index = (start_index + 1) / 2;
            end_index = (end_index - 1) / 2;
        }

        System.out.println(result);
    }

    private static void changeValue(int index, long value) {
        long i = getTreeIndex(index);
        long diff = value - tree[(int) i];
        while (i > 0) {
            tree[(int) i] += diff;
            i = i / 2;
        }
    }

    static long getTreeIndex(long i) {
        return i + leaf_start_index - 1;
    }
}
