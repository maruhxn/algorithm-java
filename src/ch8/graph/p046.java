package ch8.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 특정 거리의 도시 찾기(S2)

/**
 * 방문 배열을 0이 아닌 -1로 초기화해야 한다!
 * 0으로 초기화해버리면 처음 시작한 노드에 대한 처리가 어려워진다.
 */
public class p046 {
    static ArrayList<Integer>[] A;
    static int[] visited;
    static List<Integer> result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        visited = new int[n + 1];
        result = new ArrayList<>();
        A = new ArrayList[n + 1];
        for (int i = 1; i < n + 1; i++) {
            A[i] = new ArrayList<Integer>();
            visited[i] = -1;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            A[start].add(end);
        }

        BFS(x);

        for (int i = 1; i < n + 1; i++) {
            if (visited[i] == k) result.add(i);
        }

        if (result.isEmpty()) {
            System.out.println(-1);
        } else {
            result.stream()
                    .sorted()
                    .forEach(System.out::println);
        }

    }

    private static void BFS(int now) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(now);
        visited[now]++;
        while (!queue.isEmpty()) {
            Integer popped = queue.poll();
            A[popped].forEach(value -> {
                if (visited[value] == -1) {
                    queue.add(value);
                    visited[value] = visited[popped] + 1;
                }
            });
        }
    }
}
