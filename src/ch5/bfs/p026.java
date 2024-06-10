package ch5.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// DFS와 BFS 프로그램(S2)
public class p026 {
    static ArrayList<Integer>[] A;
    static boolean visited[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        A = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            A[i] = new ArrayList<>();
        }
        for (int i = 1; i <= m; i++) { // 인접 배열 초기화
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            A[s].add(e);
            A[e].add(s);
        }

        // 번호가 작은 것을 먼저 방문하기 위해 정렬
        for (int i = 1; i <= n; i++) {
            Collections.sort(A[i]);
        }

        DFS(start);
        System.out.println();
        visited = new boolean[n + 1];
        BFS(start);
    }

    private static void BFS(int now) {
        Queue<Integer> queue = new LinkedList<>();

        System.out.print(now + " ");
        queue.add(now);
        visited[now] = true;
        while (!queue.isEmpty()) {
            Integer popped = queue.poll();
            A[popped].forEach(value -> {
                if (!visited[value]) {
                    System.out.print(value + " ");
                    queue.add(value);
                    visited[value] = true;
                }
            });
        }
    }

    private static void DFS(int now) {
        if (visited[now]) return;

        System.out.print(now + " ");
        visited[now] = true;
        A[now].forEach(i -> {
            if (!visited[i]) {
                DFS(i);
            }
        });
    }
}
