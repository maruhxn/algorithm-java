package algospot.ch30;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class DijkstraWithPQ {

    static final int INF = Integer.MAX_VALUE;

    static ArrayList<Node>[] graph;
    static int[] dist;

    public DijkstraWithPQ(int V) {
        graph = new ArrayList[V];
        dist = new int[V];

        for (int i = 0; i < V; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = INF;
        }
    }

    public static void dijkstra(int src) {
        dist[src] = 0;

        PriorityQueue<PqForm> pq = new PriorityQueue<>();
        pq.add(new PqForm(src, 0));

        while (!pq.isEmpty()) {
            PqForm curr = pq.poll(); // (방문)
            int here = curr.index; // 현재 정점
            int cost = curr.dist; // 현재 까지의 최단 거리

            // 이미 방문한 정점 처리
            // 만약 지금 꺼낸 것보다 더 짧은 경로를 알고 있다면 지금 꺼낸 것을 무시한다
            if (dist[here] < cost) continue;

            for (int i = 0; i < graph[here].size(); i++) {
                Node next = graph[here].get(i);
                int nextDist = next.weight + cost; // 현재까지의 최단 거리에 방문할 정점의 가중치를 더해 방문점까지의 경로 길이를 구함
                // 더 짧은 경로를 발견하면, dist[]를 갱신하고 우선순위 큐에 넣는다 (발견)
                if (dist[next.x] > nextDist) {
                    dist[next.x] = nextDist;
                    pq.add(new PqForm(next.x, nextDist));
                }
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

    static class PqForm implements Comparable<PqForm> {
        int index, dist;

        PqForm(int index, int dist) {
            this.index = index;
            this.dist = dist;
        }

        @Override
        public int compareTo(PqForm o) {
            // dist 기준 오름차순 정렬
            return this.dist - o.dist;
        }
    }

    public void addEdge(int from, int to, int weight) {
        graph[from].add(new Node(to, weight));
    }

    public static void main(String[] args) {
        int V = 5; // 정점의 개수
        int src = 0; // 시작 정점
        DijkstraWithPQ dijkstra = new DijkstraWithPQ(V);

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
