package boj.swm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1991 {
    static int[][] tree;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        tree = new int[26][2]; // left, right

        for (int i = 0; i < n; i++) {
            String[] temp = br.readLine().split(" ");
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
        sb.append("\n");
        inOrder(0);
        sb.append("\n");
        postOrder(0);
        System.out.println(sb);
    }

    private static void preOrder(int i) {
        if (i == -1) return;
        sb.append((char) (i + 'A'));
        preOrder(tree[i][0]);
        preOrder(tree[i][1]);
    }

    private static void inOrder(int i) {
        if (i == -1) return;
        inOrder(tree[i][0]);
        sb.append((char) (i + 'A'));
        inOrder(tree[i][1]);
    }

    private static void postOrder(int i) {
        if (i == -1) return;
        postOrder(tree[i][0]);
        postOrder(tree[i][1]);
        sb.append((char) (i + 'A'));
    }
}
