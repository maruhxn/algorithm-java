package ch9.tree;

import java.util.ArrayList;
import java.util.Scanner;

// 상근이의 여행(S4)
public class BOJ9372 {

    static ArrayList<Integer>[] A;
    static boolean visited[];
    static int result;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            result = 0;
            int n = sc.nextInt(); // 2 <= n <= 1000
            int m = sc.nextInt(); // 1 <= m <= 10000

            A = new ArrayList[n + 1];
            visited = new boolean[n + 1];
            for (int i = 1; i <= n; i++) {
                A[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                int s = sc.nextInt();
                int e = sc.nextInt();
                A[s].add(e);
                A[e].add(s);
            }

            DFS(1);

            System.out.println(result);
        }

    }

    private static void DFS(int now) {
        if (visited[now]) return;
        visited[now] = true;
        A[now].forEach(i -> {
            if (!visited[i]) {
                DFS(i);
                result++;
            }
        });
    }
}
