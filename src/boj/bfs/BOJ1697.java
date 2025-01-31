package boj.bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1697 {

    static int[] dist = new int[200001];
    static int N, K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Arrays.fill(dist, -1);

        bfs(N);

        System.out.println(dist[K]);
    }

    static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        dist[start] = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            if (curr > 0 && dist[curr - 1] == -1) {
                queue.add(curr - 1);
                dist[curr - 1] = dist[curr] + 1;
            }

            if (curr < K && dist[curr + 1] == -1) {
                queue.add(curr + 1);
                dist[curr + 1] = dist[curr] + 1;
            }

            if (curr <= K / 2 + 1 && dist[curr * 2] == -1) {
                queue.add(curr * 2);
                dist[curr * 2] = dist[curr] + 1;
            }
        }
    }
}
