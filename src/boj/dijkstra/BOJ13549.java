package boj.dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ13549 {

    static int[] dist = new int[200001];
    static int N, K;

//    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        N = Integer.parseInt(st.nextToken());
//        K = Integer.parseInt(st.nextToken());
//        Arrays.fill(dist, -1);
//
//        Queue<Integer> queue = new LinkedList<>();
//        queue.add(N);
//        dist[N] = 0;
//
//        while (!queue.isEmpty()) {
//            int curr = queue.poll();
//            if (curr > 0 && (dist[curr - 1] == -1 || dist[curr - 1] > dist[curr] + 1)) {
//                queue.add(curr - 1);
//                dist[curr - 1] = dist[curr] + 1;
//            }
//
//            if (curr < K && (dist[curr + 1] == -1 || dist[curr + 1] > dist[curr] + 1)) {
//                queue.add(curr + 1);
//                dist[curr + 1] = dist[curr] + 1;
//            }
//
//            if (curr <= K / 2 + 1 && (dist[curr * 2] == -1 || dist[curr * 2] > dist[curr])) {
//                queue.add(curr * 2);
//                dist[curr * 2] = dist[curr];
//            }
//        }
//
//        System.out.println(dist[K]);
//    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.time - o2.time);
        pq.add(new Node(N, 0));
        dist[N] = 0;

        while (!pq.isEmpty()) {
            Node poll = pq.poll();
            int curr = poll.index;
            int cost = poll.time;

            if (dist[curr] < cost) continue;

            if (curr > 0 && dist[curr - 1] > dist[curr] + 1) {
                pq.add(new Node(curr - 1, dist[curr] + 1));
                dist[curr - 1] = dist[curr] + 1;
            }

            if (curr < K && dist[curr + 1] > dist[curr] + 1) {
                pq.add(new Node(curr + 1, dist[curr] + 1));
                dist[curr + 1] = dist[curr] + 1;
            }

            if (curr <= K / 2 + 1 && dist[curr * 2] > dist[curr]) {
                pq.add(new Node(curr * 2, dist[curr]));
                dist[curr * 2] = dist[curr];
            }
        }

        System.out.println(dist[K]);
    }

    static class Node {
        int index;
        int time;

        public Node(int index, int time) {
            this.index = index;
            this.time = time;
        }
    }
}
