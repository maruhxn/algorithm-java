package boj.tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1967 {

    static class Node {
        int x;
        int weight;

        public Node(int x, int weight) {
            this.x = x;
            this.weight = weight;
        }
    }
    static ArrayList<Node>[] tree;
    static boolean[] visited;

//    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int N = Integer.parseInt(br.readLine());
//
//        tree = new ArrayList[N];
//
//        for (int i = 0; i < N; i++) {
//            tree[i] = new ArrayList<>();
//        }
//
//        StringTokenizer st;
//        for (int i = 0; i < N - 1; i++) {
//            st = new StringTokenizer(br.readLine());
//            int node = Integer.parseInt(st.nextToken()) - 1;
//            int child = Integer.parseInt(st.nextToken()) - 1;
//            int weight = Integer.parseInt(st.nextToken());
//            tree[node].add(new Node(child, weight));
//            tree[child].add(new Node(node, weight));
//        }
//
//        int ret = 0;
//        for (int i = 0; i < N; i++) {
//            visited = new boolean[N];
//            if (tree[i].size() == 1) {
//                ret = Math.max(ret, getHeight(i));
//            }
//        }
//        System.out.println(ret);
//
//    }
//
//    private static int getHeight(int node) {
//        if (tree[node].isEmpty()) return 0;
//
//        visited[node] = true;
//        int height = 0;
//
//        for (Node child : tree[node]) {
//            if (visited[child.x]) continue;
//            height = Math.max(height, child.weight + getHeight(child.x));
//        }
//
//        return height;
//    }

    static int maxDistance = 0;
    static int farthestNode = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        tree = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            tree[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken()) - 1;
            int child = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            tree[node].add(new Node(child, weight));
            tree[child].add(new Node(node, weight));
        }

        // 1. 루트(0번 노드)에서 가장 먼 노드 찾기
        visited = new boolean[N];
        dfs(0, 0);

        // 2. 찾은 노드에서 가장 먼 노드까지의 거리 구하기
        visited = new boolean[N];
        maxDistance = 0;
        dfs(farthestNode, 0);

        System.out.println(maxDistance);
    }

    static void dfs(int node, int distance) {
        visited[node] = true;

        if (distance > maxDistance) {
            maxDistance = distance;
            farthestNode = node;
        }

        for (Node neighbor : tree[node]) {
            if (!visited[neighbor.x]) {
                dfs(neighbor.x, distance + neighbor.weight);
            }
        }
    }

}
