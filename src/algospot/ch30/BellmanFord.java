package algospot.ch30;

import java.util.ArrayList;
import java.util.Arrays;

public class BellmanFord {

    static ArrayList<Node>[] graph;
    static int V;

    public BellmanFord(int V) {
        this.V = V;
        graph = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    public static int[] bellmanFord(int src) {
        int[] upper = new int[V];
        Arrays.fill(upper, Integer.MAX_VALUE);
        upper[src] = 0;

        boolean updated = false;

        // V번 순회
        for (int iter = 0; iter < V; iter++) {
            updated = false;

            // 모든 정점에 대하여
            for (int curr = 0; curr < V; curr++) {
                // 인접한 정점들을 업데이트 할 수 있는지
                for (int i = 0; i < graph[curr].size(); i++) {
                    Node next = graph[curr].get(i);
                    // (curr, next) 완화 시도
                    if (upper[next.x] > upper[curr] + next.weight) {
                        // 성공
                        upper[next.x] = upper[curr] + next.weight;
                        updated = true;
                    }
                }
            }
            // 모든 간선에 대해 완화기 실패했을 경우, V-1번도 돌 필요 없이 곧장 종료
            // 이미 모든 최단 경로 길이를 찾음
            if (!updated) break;
        }

        // V번째 순회에서도 완화가 성공했다면 음수 사이클이 있음
        if (updated) upper = new int[V];
        return upper;
    }

    static class Node {
        int x;
        int weight;

        public Node(int x, int weight) {
            this.x = x;
            this.weight = weight;
        }
    }
}
