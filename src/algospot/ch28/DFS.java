package algospot.ch28;

import java.util.ArrayList;

public class DFS {

    // 그래프의 인접 리스트 표현
    ArrayList<Integer>[] graph;
    // 각 정점을 방문했는지 여부를 나타냄
    boolean[] visited;

    public void dfs(int node) {
        System.out.println("DFS visits " + node + "\n");
        visited[node] = true;

        // 모든 인접 정점을 순회하면서
        for (int i = 0; i < graph[node].size(); i++) {
            Integer next = graph[node].get(i);
            // 아직 방문한 적이 없다면 방문한다
            if (!visited[next])
                dfs(next);
        }
    }


    // 모든 정점을 방문한다
    public void dfsAll() {
        visited = new boolean[graph.length];

        // 모든 정점을 순회하면서 아직 방문하지 않은 정점을 방문한다
        for (int i = 1; i < graph.length; i++) {
            if (!visited[i]) dfs(i);
        }
    }
}
