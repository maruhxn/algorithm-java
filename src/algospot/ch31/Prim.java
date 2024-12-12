package algospot.ch31;

import java.util.ArrayList;
import java.util.List;

public class Prim {
    static int V;
    static final int INF = 987654321;
    static ArrayList<Node>[] graph;

    static int prim(List<SelectedEdge> selected) {
        // 해당 정점이 트리에 포함되어 있는지 여부를 담는 배열
        boolean[] added = new boolean[V];
        // 트리에 인접한 간선 중 해당 정점에 닿는 최소 간선의 정보를 담는 배열
        int[] minWeights = new int[V];
        // 각 정점이 트리와 연결되었는지 여부를 확인하기 위해, 사용하는 간선의 다른 한쪽 끝 정점을 담는 배열
        // ex) parent[0] = 1 -> 0번 정점이 (0, 1) 간선을 통해 트리에 연결됨
        int[] parent = new int[V];

        for (int i = 0; i < V; i++) {
            minWeights[i] = INF;
            parent[i] = -1;
        }

        // 가중치의 합
        int ret = 0;

        // 0번 정점을 시작점으로 항상 트리에 가장 먼저 추가
        minWeights[0] = parent[0] = 0;
        for (int iter = 0; iter < V; iter++) {
            // 다음에 트리에 추가할 정점을 찾음
            // 알고있는 최소 간선에서 가장 작은 값 선택
            int u = -1;
            for (int v = 0; v < V; v++) {
                if (!added[v] && (u == -1 || minWeights[u] > minWeights[v])) {
                    u = v;
                }
            }

            // (parent[u], u)를 트리에 추가
            if (parent[u] != u) selected.add(new SelectedEdge(parent[u], u));

            ret += minWeights[u];
            added[u] = true;

            // u에 인접한 간선 (u, v)들을 검사
            for (int i = 0; i < graph[u].size(); i++) {
                Node nextNode = graph[u].get(i);
                int v = nextNode.x;
                int weight = nextNode.weight;
                // 인접한 정점이 아직 추가되지 않았고, 가중치가 지금까지 찾은 해당 정점까지의 최소 간선보다 작다면 정보 업데이트
                if (!added[v] && minWeights[v] > weight) {
                    parent[v] = u;
                    minWeights[v] = weight;
                }
            }
        }

        return ret;
    }


    static class Node {
        int x;
        int weight;

        public Node(int x, int weight) {
            this.x = x;
            this.weight = weight;
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

        graph = new ArrayList[V];

        for (int i = 0; i < V; i++) {
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
        int cost = prim(selected);

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
