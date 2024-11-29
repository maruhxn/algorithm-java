package boj.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 트리의 부모 찾기 by 인접 리스트
 */
public class BOJ11725 {

    static ArrayList<Integer>[] tree;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        tree = new ArrayList[N + 1];
        parent = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Integer s = Integer.parseInt(st.nextToken());
            Integer e = Integer.parseInt(st.nextToken());
            tree[s].add(e);
            tree[e].add(s);
        }

        checkParentOf(1);

        for (int i = 2; i <= N; i++) {
            System.out.println(parent[i]);
        }
    }

    private static void checkParentOf(int p) {
        ArrayList<Integer> children = tree[p];

        for (Integer child : children) {
            if(child == 1 || parent[child] != 0) continue;
            parent[child] = p;
            checkParentOf(child);
        }
    }
}
