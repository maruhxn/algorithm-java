package algospot.ch30;

import java.util.Arrays;
import java.util.List;

public class Floyd {

    static final int INF = 987654321;
    // 정점의 개수
    static int V;

    // 그래프의 인접 행렬 표현
    // graph[u][v] = u에서 v로 가는 간선의 가중치. 간선이 없으면 아주 큰 값 넣기
    static int[][] graph;

    // via[u][v] = u에서 v로 가는 최단 경로가 경유하는 점 중 가장 번호가 큰 점
    // -1로 초기화
    static int[][] via;

    public Floyd() {
        graph = new int[V][V];
        via = new int[V][V];

        for (int i = 0; i < V; i++) {
            Arrays.fill(graph[i], INF);
            Arrays.fill(via[i], -1);
        }
    }

    void floyd() {
        for (int i = 0; i < V; i++) graph[i][i] = 0;

        for (int k = 0; k < V; k++) { // 경유점
            for (int i = 0; i < V; i++) { // 시점
                for (int j = 0; j < V; j++) { // 종점
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }
    }

    void floyd2() {
        for (int i = 0; i < V; i++) graph[i][i] = 0;

        for (int k = 0; k < V; k++) { // 경유점
            for (int i = 0; i < V; i++) { // 시점
                for (int j = 0; j < V; j++) { // 종점
                    if (graph[i][j] > graph[i][k] + graph[k][j]) {
                        via[i][j] = k; // 마지막으로 갱신한 k값 저장
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }
    }

    // u에서 v로 가는 최단 경로를 계산해 path에 저장
    void reconstruct(int u, int v, List<Integer> path) {
        if (via[u][v] == -1) { // u에서 v로 가는 최단 경로에 경유점이 없는 경우
            path.add(u);
            if (u != v) path.add(v);
        } else {
            int w = via[u][v];
            reconstruct(u, w, path);
            path.remove(path.size() - 1); // reconstruct를 두 번 호출하여 w가 중복으로 들어가므로 하나 지움
            reconstruct(v, w, path);
        }
    }
}
