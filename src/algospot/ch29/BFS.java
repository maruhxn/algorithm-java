package algospot.ch29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    static ArrayList<Integer>[] graph;
    static boolean[] discovored;
    static Queue<Integer> queue;

    public static void bfs(int start) {
        discovored[start] = true;
        queue.add(start);
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            System.out.printf(curr + " ");
            for (int i = 0; i < graph[curr].size(); i++) {
                int next = graph[curr].get(i);
                if (!discovored[next]) {
                    queue.add(next);
                    discovored[next] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        graph = new ArrayList[7];
        discovored = new boolean[7];
        queue = new LinkedList<>();

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        graph[1].add(2);
        graph[1].add(3);
        graph[2].add(6);
        graph[3].add(4);
        graph[3].add(5);

        bfs(1);
    }
}
