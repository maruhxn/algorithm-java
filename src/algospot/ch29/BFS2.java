package algospot.ch29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BFS2 {

    static ArrayList<Integer>[] graph;
    // distance[i] = start부터 i까지의 거리
    static int[] distance;
    // parent[i] = BFS 스패닝 트리에서 i의 부모의 번호. 루트인 경우 자신의 번호
    static int[] parent;
    static Queue<Integer> queue;

    public static void bfs2(int start) {
        distance[start] = 0;
        parent[start] = start;
        queue.add(start);
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            System.out.printf(curr + " ");
            for (int i = 0; i < graph[curr].size(); i++) {
                int next = graph[curr].get(i);
                if (distance[next] == -1) {
                    queue.add(next);
                    distance[next] = distance[curr] + 1;
                    parent[next] = curr;
                }
            }
        }
    }

    // v로부터 시작점까지의 최단 경로 계산
    public static List<Integer> shortestPath(int v) {
        List<Integer> path = new ArrayList<>();
        path.add(v);
        while (parent[v] != v) {
            v = parent[v];
            path.add(v);
        }

        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) throws IOException {
        graph = new ArrayList[7];
        distance = new int[7];
        parent = new int[7];
        queue = new LinkedList<>();

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
            distance[i] = -1;
            parent[i] = -1;
        }

        graph[1].add(2);
        graph[1].add(3);
        graph[2].add(6);
        graph[3].add(4);
        graph[3].add(5);

        bfs2(1);
        System.out.println();
        for (int i = 1; i < distance.length; i++) {
            System.out.printf(distance[i] + " ");
        }

        System.out.println();
        for (Integer node : shortestPath(5)) {
            System.out.printf(node + " ");
        }
    }
}
