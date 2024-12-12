package algospot.ch30;

import java.util.ArrayList;

public class Dijkstra {

    static final int INF = Integer.MAX_VALUE;

    static int V;
    static ArrayList<Node>[] graph;
    // 시작점에서 i 정점까지의 최단 거리
    static int[] dist;
    // i 정점 방문 여부
    static boolean[] visited;

    public Dijkstra(int V) {
        this.V = V;
        graph = new ArrayList[V];
        dist = new int[V];
        visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = INF;
        }
    }

    public static void dijkstra(int src) {
        dist[src] = 0;

        while (true) {
            int closest = INF;
            int here = -1;

            // 아직 방문하지 않은 정점 중 가장 가까운 정점을 찾는다
            for (int i = 0; i < V; i++) {
                if (dist[i] < closest && !visited[i]) {
                    here = i;
                    closest = dist[i];
                }
            }

            // 모두 방문했다면 반복문을 빠져나간다
            if (closest == INF) break;

            // 아직 방문하지 않은 인접한 정점을 방문한다
            visited[here] = true;
            for (int i = 0; i < graph[here].size(); i++) {
                Node next = graph[here].get(i);
                if (visited[next.x]) continue;
                int nextDist = next.weight + dist[here]; // 현재까지의 최단 거리 + 다음 정점으로의 가중치
                // dist[next.x] = 시작점에서 다음 정점까지의 최단 거리
                dist[next.x] = Math.min(dist[next.x], nextDist);
            }
        }
    }

    static class Node {

        int x;
        int weight;

        public Node(int x, int weight) {
            this.x = x;
            this.weight = weight;
        }
    }

    public void addEdge(int from, int to, int weight) {
        graph[from].add(new Node(to, weight));
    }

    public static void main(String[] args) {
        int V = 5; // 정점의 개수
        int src = 0; // 시작 정점
        Dijkstra dijkstra = new Dijkstra(V);

        // 그래프 추가
        dijkstra.addEdge(0, 1, 10);
        dijkstra.addEdge(0, 2, 3);
        dijkstra.addEdge(1, 2, 1);
        dijkstra.addEdge(1, 3, 2);
        dijkstra.addEdge(2, 1, 4);
        dijkstra.addEdge(2, 3, 8);
        dijkstra.addEdge(2, 4, 2);
        dijkstra.addEdge(3, 4, 7);
        dijkstra.addEdge(4, 3, 9);

        // 다익스트라 실행
        dijkstra.dijkstra(src);

        // 결과 출력
        System.out.println("Vertex\tDistance from Source");
        for (int i = 0; i < V; i++) {
            System.out.println(i + "\t\t" + dist[i]);
        }
    }
}
