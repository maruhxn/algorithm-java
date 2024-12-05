package algospot.ch28;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TopologySortWithDFS {

    ArrayList<Integer>[] graph;
    boolean[] visited;
    List<Integer> answer;

    public TopologySortWithDFS(ArrayList<Integer>[] graph) {
        this.graph = graph;
    }

    public void dfs(int node) {
        visited[node] = true;

        for (int i = 0; i < graph[node].size(); i++) {
            Integer next = graph[node].get(i);
            if (!visited[next]) dfs(next);
        }

        answer.add(node);
    }


    public void dfsAll() {
        visited = new boolean[graph.length];
        answer = new ArrayList<>();

        for (int i = 1; i < graph.length; i++) {
            if (!visited[i]) dfs(i);
        }

        Collections.reverse(answer);

        for (int i = 0; i < answer.size(); i++) {
            System.out.printf(answer.get(i) + " ");
        }
    }
}
