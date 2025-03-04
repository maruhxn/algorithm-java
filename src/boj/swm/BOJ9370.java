package boj.swm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ9370 {

    static ArrayList<Edge>[] graph;
    static int[] dist;
    static int INF = 87654321;
    static int[] targets;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 교차로 수
            int m = Integer.parseInt(st.nextToken()); // 도로 수
            int t = Integer.parseInt(st.nextToken()); // 목적지 후보 수

            graph = new ArrayList[n + 1];
            dist = new int[n + 1];
            targets = new int[t];

            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()); // 예술가들 출발지
            int g = Integer.parseInt(st.nextToken()); // 경유 도로 출발점
            int h = Integer.parseInt(st.nextToken()); // 경유 도로 도착점

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                graph[a].add(new Edge(b, c));
                graph[b].add(new Edge(a, c));
            }

            // 목적지 후보
            for (int i = 0; i < t; i++) {
                targets[i] = Integer.parseInt(br.readLine());
            }

            dijkstra(s);
            int stoG = dist[g]; // s -> g
            int stoH = dist[h]; // s -> h
            int[] stoTarget = new int[targets.length];
            for (int i = 0; i < targets.length; i++) {
                stoTarget[i] = dist[targets[i]]; // s -> target
            }

            dijkstra(g);
            int gToH = dist[h];
            int[] gtoTarget = new int[targets.length];
            for (int i = 0; i < targets.length; i++) {
                gtoTarget[i] = dist[targets[i]]; // s -> target
            }

            dijkstra(h);
            int[] htoTarget = new int[targets.length];
            for (int i = 0; i < targets.length; i++) {
                htoTarget[i] = dist[targets[i]]; // s -> target
            }

            List<Integer> result = new ArrayList<>();
            for (int i = 0; i < targets.length; i++) {
                int sght = stoG + gToH + htoTarget[i];
                int shgt = stoH + gToH + gtoTarget[i];
                if (stoTarget[i] == sght || stoTarget[i] == shgt) {
                    result.add(targets[i]);
                }
            }

            result.stream().sorted(((o1, o2) -> o1 - o2)).forEach(a -> sb.append(a + " "));
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void dijkstra(int src) {
        Arrays.fill(dist, INF);
        PriorityQueue<PqForm> pq = new PriorityQueue<>((p1, p2) -> p1.dist - p2.dist);
        dist[src] = 0;
        pq.add(new PqForm(src, 0));

        while (!pq.isEmpty()) {
            PqForm curr = pq.poll();
            int here = curr.v;
            int currDist = curr.dist;

            if (dist[here] < currDist) continue;

            for (Edge next : graph[here]) {
                int nextDist = next.weight + currDist;
                if (dist[next.v] > nextDist) {
                    dist[next.v] = nextDist;
                    pq.add(new PqForm(next.v, nextDist));
                }
            }
        }
    }

    static class PqForm {
        int v;
        int dist;

        PqForm(int v, int dist) {
            this.v = v;
            this.dist = dist;
        }
    }

    static class Edge {
        int v;
        int weight;

        Edge(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }
    }
}

// for(목적지 후보 : 목적지 후보들) {
//  if((s -> 목적지 후보) < (s -> g -> h -> 목적지 후보) || (s -> 목적지 후보) < (s -> 목적지 후보) < (s -> h -> g -> 목적지 후보)) {
//      말 안됨
//  }
// }
//
//