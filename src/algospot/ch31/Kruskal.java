package algospot.ch31;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Kruskal {

    static int V;

    static int[] parent, rank; // 유니온 파인드
    static ArrayList<Node>[] graph;

    public static int kruskal(List<SelectedEdge> selected) {
        int ret = 0;

        // (가중치, (정접1, 정점2))의 목록을 얻는다 : 간선의 목록 얻기
        List<Edge> edges = new ArrayList<>();
        for (int u = 0; u < V; u++) {
            for (int i = 0; i < graph[u].size(); i++) {
                int v = graph[u].get(i).x;
                int cost = graph[u].get(i).weight;

                edges.add(new Edge(cost, u, v));
            }
        }

        // 가중치 기준 오름차순 정렬
        edges.sort(Comparator.comparingInt(o -> o.weight));

        // 처음엔 모든 정점이 서로 분리
        for (int i = 0; i < edges.size(); i++) {
            // 간선 (u, v) 검사
            Edge currEdge = edges.get(i);

            // 이미 u와 v가 연결되어 있을 경우 -> 사이클이 생기므로 무시
            if (find(currEdge.u) == find(currEdge.v)) continue;

            // 아니라면 이 둘을 합친다
            union(currEdge.u, currEdge.v);
            selected.add(new SelectedEdge(currEdge.u, currEdge.v));
            ret += currEdge.weight;
        }

        return ret;
    }

    public static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        // 이미 같은 집합에 속해있는 경우 걸러내기
        if (x == y) return;

        // 항상 y의 높이가 더 크도록
        if (rank[x] > rank[y]) {
            int temp = x;
            x = y;
            y = temp;
        }

        parent[x] = y; // x를 y의 자식으로 넣기

        // 두 트리의 높이가 같은 경우 높이 1 증가
        if (rank[x] == rank[y]) ++rank[y];
    }

    static class Node {
        int x;
        int weight;

        public Node(int x, int weight) {
            this.x = x;
            this.weight = weight;
        }
    }

    static class Edge implements Comparable<Edge> {

        int weight;
        int u;
        int v;

        public Edge(int weight, int u, int v) {
            this.weight = weight;
            this.u = u;
            this.v = v;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(weight, o.weight);
        }
    }

    static class SelectedEdge {
        int u;
        int v;

        public SelectedEdge(int u, int v) {
            this.u = u;
            this.v = v;
        }
    }

    public static void main(String[] args) {
        V = 7;

        parent = new int[V];
        rank = new int[V];
        graph = new ArrayList[V];

        for (int i = 0; i < V; i++) {
            parent[i] = i;
            rank[i] = 1;
            graph[i] = new ArrayList<>();
        }

        addEdge(0, 2, 1);
        addEdge(0, 1, 5);
        addEdge(1, 3, 1);
        addEdge(1, 5, 3);
        addEdge(1, 6, 3);
        addEdge(2, 3, 4);
        addEdge(3, 4, 5);
        addEdge(3, 5, 3);
        addEdge(5, 6, 2);

        List<SelectedEdge> selected = new ArrayList<>();
        int cost = kruskal(selected);

        System.out.println(cost);

        for (SelectedEdge selectedEdge : selected) {
            System.out.println(selectedEdge.u + " " + selectedEdge.v);
        }
    }

    private static void addEdge(int u, int v, int weight) {
        graph[u].add(new Node(v, weight));
        graph[v].add(new Node(u, weight));
    }
}
