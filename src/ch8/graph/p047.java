package ch8.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 효율적으로 해킹하기(S1)
public class p047 {
    static ArrayList<Integer>[] A;
    static boolean visited[];
    static int result[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        A = new ArrayList[n + 1];
        result = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            A[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int S = sc.nextInt();
            int E = sc.nextInt();
            A[S].add(E);
        }

        for (int i = 1; i <= n; i++) {
            visited = new boolean[n + 1];
            BFS(i);
        }

        int max = 0;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, result[i]);
        }

        for (int i = 1; i <= n; i++) {
            if (result[i] == max) {
                System.out.print(i + " ");
            }
        }
    }

    private static void BFS(int now) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(now);
        visited[now] = true;
        while (!queue.isEmpty()) {
            Integer popped = queue.poll();
            A[popped].forEach(value -> {
                if (!visited[value]) {
                    queue.add(value);
                    result[value]++;
                    visited[value] = true;
                }
            });
        }
    }
}