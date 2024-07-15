package ch9.tree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// 리프 노드의 개수 구하기(G5)

/**
 * 1. root노드가 0번이 아닐때 발생할수 있다
 * 2. 루트노드가 리프노드가 될 수 있다
 * 3. 루트 노드를 삭제하면 0이 나와야한다.
 */
public class p068 {
    static ArrayList<Integer>[] tree;
    static int[] parent;
    static boolean[] visited;
    static int deleteNode, result = 0;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        tree = new ArrayList[n];
        visited = new boolean[n];
        parent = new int[n];
        int root = 0;

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            int v = sc.nextInt();
            if (v == -1) {
                root = i;
                continue;
            }
            tree[i].add(v);
            tree[v].add(i);
        }
        deleteNode = sc.nextInt();


        if (deleteNode == root) {
            System.out.println(0);
        } else {

            DFS(root);

            System.out.println(result);
        }
    }

    private static void DFS(int i) {
        visited[i] = true;
        int cNode = 0; // 자식 노드의 개수

        for (int node : tree[i]) {
            if (!visited[node] && node != deleteNode) {
                cNode++;
                DFS(node);
            }
        }

        if (cNode == 0) {
            result++;
        }
    }
}
