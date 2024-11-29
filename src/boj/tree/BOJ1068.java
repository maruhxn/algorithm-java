package boj.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1068 {
    static ArrayList<Integer>[] tree;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        tree = new ArrayList[N];
        parent = new int[N];

        for (int i = 0; i < N; i++) {
            tree[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int s = Integer.parseInt(st.nextToken());
            parent[i] = s;
            if (s != -1) {
                tree[s].add(i);
                tree[i].add(s);
            }
        }

        int target = Integer.parseInt(br.readLine());

        int result = 0;

        if (parent[target] != -1) {
            solve(target);
            tree[parent[target]].remove((Integer) target);

            for (int i = 0; i < N; i++) {
                if ((parent[i] != -1 && tree[i].size() == 1)
                        || (parent[i] == -1 && tree[i].isEmpty())) ++result;
            }
        }
        System.out.println(result);
    }

    private static void solve(int p) {
        ArrayList<Integer> children = tree[p];

        if (children.size() == 1) {
            children.add(-2);
            return;
        }

        for (Integer child : children) {
            if (child != parent[p]) solve(child);
        }
    }
}
