package boj.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ9370 {

    static int INF = 987654321;
    static int n, s, g, h;
    static ArrayList<Node>[] graph;
    static int[] target;
    static PriorityQueue<PqForm> pq;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); // 교차로
            int m = Integer.parseInt(st.nextToken()); // 도로 수
            int t = Integer.parseInt(st.nextToken()); // 목적지 후보 수

            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken()) - 1; // 출발지
            g = Integer.parseInt(st.nextToken()) - 1;
            h = Integer.parseInt(st.nextToken()) - 1;

            graph = new ArrayList[n];
            target = new int[t];

            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                int d = Integer.parseInt(st.nextToken());
                graph[a].add(new Node(b, d));
                graph[b].add(new Node(a, d));
            }

            for (int i = 0; i < t; i++) {
                target[i] = Integer.parseInt(br.readLine()) - 1;
            }

            pq = new PriorityQueue<>((o1, o2) -> o1.dist - o2.dist);

            int[] fromS = dijkstra(s);
            int[] fromG = dijkstra(g);
            int[] fromH = dijkstra(h);

            Arrays.sort(target);
            for (int v : target) {
                if (fromS[v] == fromS[g] + fromG[h] + fromH[v]) sb.append(v + 1).append(" ");
                else if (fromS[v] == fromS[h] + fromH[g] + fromG[v]) sb.append(v + 1).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static int[] dijkstra(int st) {
        int[] dist = new int[n];
        Arrays.fill(dist, INF);

        dist[st] = 0;
        pq.add(new PqForm(st, 0));

        while (!pq.isEmpty()) {
            PqForm poll = pq.poll();
            int curr = poll.index;
            int cost = poll.dist;

            if (dist[curr] < cost) continue;

            for (int i = 0; i < graph[curr].size(); i++) {
                Node next = graph[curr].get(i);
                int nextDist = next.weight + cost;
                if (dist[next.x] <= nextDist) continue;
                pq.offer(new PqForm(next.x, nextDist));
                dist[next.x] = nextDist;
            }
        }

        return dist;
    }

    static class Node {
        int x;
        int weight;

        public Node(int x, int weight) {
            this.x = x;
            this.weight = weight;
        }
    }

    static class PqForm {
        int index, dist;

        public PqForm(int index, int dist) {
            this.index = index;
            this.dist = dist;
        }
    }
}
