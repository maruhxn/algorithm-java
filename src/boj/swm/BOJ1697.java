package boj.swm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1697 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int max = 100000;
        int[] dist = new int[max + 1];
        Arrays.fill(dist, -1);

        int[] dx = new int[]{-1, 1, 0};

        Queue<Integer> queue = new LinkedList<>();

        queue.add(N);
        dist[N] = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int dir = 0; dir < 3; dir++) {
                int nx = dir == 2 ? curr * 2 : curr + dx[dir];
                if (nx < 0 || nx > max) continue;
                if (dist[nx] != -1) continue;

                queue.add(nx);
                dist[nx] = dist[curr] + 1;
            }
        }

        System.out.println(dist[K]);
    }
}
