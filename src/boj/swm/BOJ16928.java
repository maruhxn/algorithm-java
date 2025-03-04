package boj.swm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16928 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] graph = new int[101];
        int[] dist = new int[101];

        Arrays.fill(dist, -1);

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a] = b;
        }

        Queue<Integer> Q = new LinkedList<>();

        Q.add(1);
        dist[1] = 0;

        while (!Q.isEmpty()) {
            int curr = Q.poll();

            for (int i = 1; i <= 6; i++) {
                int nx = curr + i;
                if (nx > 100) continue;
                if (graph[nx] != 0) nx = graph[nx];
                if (dist[nx] != -1) continue;
                Q.add(nx);
                dist[nx] = dist[curr] + 1;
            }
        }

        System.out.println(dist[100]);
    }
}
