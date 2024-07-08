package ch8.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 숨바꼭질3(G5)
public class BOJ13549 {
    static boolean[] visited;
    static int a, b, min;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        a = sc.nextInt();
        b = sc.nextInt();
        visited = new boolean[100001];
        min = Integer.MAX_VALUE;
        BFS();

        System.out.println(min);
    }

    private static void BFS() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(a, 0));
        visited[a] = true;
        while (!queue.isEmpty()) {
            Node now = queue.poll();
            visited[now.x] = true;
            if (now.x == b) min = Math.min(min, now.time);
            if (now.x * 2 <= 100000 && !visited[now.x * 2])
                queue.add(new Node(now.x * 2, now.time));
            if (now.x - 1 >= 0 && !visited[now.x - 1])
                queue.add(new Node(now.x - 1, now.time + 1));
            if (now.x + 1 <= 100000 && !visited[now.x + 1])
                queue.add(new Node(now.x + 1, now.time + 1));
        }
    }

    static class Node {
        int x, time;

        public Node(int x, int time) {
            this.x = x;
            this.time = time;
        }
    }
}
