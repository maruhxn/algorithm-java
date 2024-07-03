package ch8.topology_sort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 줄 세우기(G3)
public class p053 {
    static int n, m;
    static ArrayList<Integer>[] A;
    static int[] D;
    static ArrayList<Integer> result;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        A = new ArrayList[n + 1];
        D = new int[n + 1];
        result = new ArrayList<Integer>();
        for (int i = 1; i <= n; i++) {
            A[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int v = sc.nextInt();
            int e = sc.nextInt();
            A[v].add(e);
            D[e]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (D[i] == 0) queue.offer(i);
        }

        while (!queue.isEmpty()) {
            Integer polled = queue.poll();
            result.add(polled);
            for (int next : A[polled]) {
                D[next]--;
                if (D[next] == 0) {
                    queue.add(next);
                }
            }
        }

        for (int i : result) {
            System.out.print(i + " ");
        }
    }
}