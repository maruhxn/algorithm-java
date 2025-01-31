package boj.bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ16928 {

    static int[] dist = new int[101];
    static Map<Integer, Integer> map = new HashMap<>();


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            map.put(u, v);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            map.put(u, v);
        }

        Arrays.fill(dist, -1);

        Queue<Integer> queue = new LinkedList<>();
        dist[1] = 0;
        queue.add(1);

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int i = 1; i <= 6; i++) {
                int next = map.getOrDefault(curr + i, curr + i);
                if (next > 100) continue;
                if (dist[next] >= 0) continue;
                dist[next] = dist[curr] + 1;
                queue.add(next);
            }
        }

        System.out.println(dist[100]);
    }
}
