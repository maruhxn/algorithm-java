package ch9.binary_tree;

import java.util.Scanner;

// 트리 순회하기(S1)
public class p070 {
    static int[][] tree;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        tree = new int[26][2]; // left, right
        for (int i = 0; i < n; i++) {
            String[] temp = sc.nextLine().split(" ");
            int node = temp[0].charAt(0) - 'A';
            char left = temp[1].charAt(0);
            char right = temp[2].charAt(0);
            if (left == '.') {
                tree[node][0] = -1;
            } else {
                tree[node][0] = left - 'A';
            }

            if (right == '.') {
                tree[node][1] = -1;
            } else {
                tree[node][1] = right - 'A';
            }
        }

        preOrder(0);
        System.out.println();
        inOrder(0);
        System.out.println();
        postOrder(0);
    }

    private static void preOrder(int i) {
        if (i == -1) return;
        System.out.print((char) (i + 'A'));
        preOrder(tree[i][0]);
        preOrder(tree[i][1]);
    }

    private static void inOrder(int i) {
        if (i == -1) return;
        inOrder(tree[i][0]);
        System.out.print((char) (i + 'A'));
        inOrder(tree[i][1]);
    }

    private static void postOrder(int i) {
        if (i == -1) return;
        postOrder(tree[i][0]);
        postOrder(tree[i][1]);
        System.out.print((char) (i + 'A'));
    }
}
