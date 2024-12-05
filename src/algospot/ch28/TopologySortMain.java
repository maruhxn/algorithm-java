package algospot.ch28;

import java.util.ArrayList;
import java.util.Collections;

public class TopologySortMain {
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) {

        graph = new ArrayList[7];

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        graph[1].add(2);
        graph[1].add(3);
        graph[2].add(6);
        graph[3].add(4);
        graph[3].add(5);

        TopologySortWithDFS topologySortWithDFS = new TopologySortWithDFS(graph);
        TopologySortWithQueue topologySortWithQueue = new TopologySortWithQueue(graph);

        topologySortWithDFS.dfsAll();
        System.out.println();
        topologySortWithQueue.dfsAll();
    }
}
