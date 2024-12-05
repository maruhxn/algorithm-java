package algospot.ch28;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TopologySortWithQueue {

    ArrayList<Integer>[] graph;
    int[] indegree;
    List<Integer> answer;

    public TopologySortWithQueue(ArrayList<Integer>[] graph) {
        this.graph = graph;
        this.indegree = new int[graph.length];
        this.answer = new ArrayList<>();

        for (ArrayList<Integer> nodes : graph) {
            for (Integer node : nodes) {
                ++indegree[node];
            }
        }
    }

    public void dfsAll() {
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i < indegree.length; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();
            answer.add(node);
            for (Integer next : graph[node]) {
                --indegree[next];
                if (indegree[next] == 0) queue.offer(next);
            }
        }

        for (int i = 0; i < answer.size(); i++) {
            System.out.printf(answer.get(i) + " ");
        }
    }
}
