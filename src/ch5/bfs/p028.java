package ch5.bfs;

import java.util.*;

// 트리의 지름 구하기(G2)
public class p028 {
    static int n;
    static ArrayList<Node>[] A;
    static boolean visited[];
    static int distance[];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        A = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            A[i] = new ArrayList<Node>();
        }

        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            while (true) {
                int e = sc.nextInt();
                if(e == -1) break;;
                int value = sc.nextInt();
                A[num].add(new Node(e, value));
            }
        }

        distance = new int[n + 1];
        visited = new boolean[n + 1];
        BFS(1);

        int max = 1;
        for (int i = 2; i <= n; i++) {
            if (distance[max] < distance[i]) {
                max = i;
            }
        }

        distance = new int[n + 1];
        visited = new boolean[n + 1];
        BFS(max);
        Arrays.sort(distance);
        System.out.println(distance[n]);
    }

    private static void BFS(int index) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(index);
        visited[index] = true;
        while (!queue.isEmpty()) {
            Integer now = queue.poll();
            for (Node i : A[now]) {
                if (!visited[i.e]) {
                    visited[i.e] = true;
                    distance[i.e] = distance[now] + i.value;
                    queue.add(i.e);
                }
            }
        }
    }

    static class Node {
        int e;
        int value;

        public Node(int e, int value) {
            this.e = e;
            this.value = value;
        }
    }
}
