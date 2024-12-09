package algospot.ch30;

import java.io.*;
import java.util.*;

public class FIRETRUCKS {

    static ArrayList<Node>[] graph;
    static int[] dist;
    static Map<Integer, Integer> results;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int C = Integer.parseInt(br.readLine());
        while (C-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken()); // 장소의 수
            int E = Integer.parseInt(st.nextToken()); // 도로의 수
            int N = Integer.parseInt(st.nextToken()); // 화재 장소의 수
            int M = Integer.parseInt(st.nextToken()); // 소방서의 수

            graph = new ArrayList[V + 1];
            dist = new int[V + 1];
            for (int i = 1; i <= V; i++) {
                graph[i] = new ArrayList<>();
                dist[i] = Integer.MAX_VALUE;
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                graph[u].add(new Node(v, w));
                graph[v].add(new Node(u, w));
            }

            results = new HashMap<Integer, Integer>();

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int firePlace = Integer.parseInt(st.nextToken());
                results.put(firePlace, Integer.MAX_VALUE);
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int firetrucks = Integer.parseInt(st.nextToken());
                dijkstra(firetrucks);
                for (int firePlace : results.keySet()) {
                    if (results.get(firePlace) > dist[firePlace]) {
                        results.put(firePlace, dist[firePlace]);
                    }
                }
            }

            int result = 0;
            for (Integer value : results.values()) {
                result += value;
            }
            bw.append(result + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static void dijkstra(int src) {
        dist[src] = 0;

        PriorityQueue<PqForm> pq = new PriorityQueue<>();
        pq.add(new PqForm(src, 0));

        while (!pq.isEmpty()) {
            PqForm curr = pq.poll(); // (방문)
            int here = curr.index; // 현재 정점
            int cost = curr.dist; // 현재 까지의 최단 거리

            if (dist[here] < cost) continue;

            for (int i = 0; i < graph[here].size(); i++) {
                Node next = graph[here].get(i);
                int nextDist = next.weight + cost;
                if (dist[next.x] > nextDist) {
                    dist[next.x] = nextDist;
                    pq.add(new PqForm(next.x, nextDist));
                }
            }

        }
    }

    static class Node {
        int x;
        int weight;

        public Node(int x, int weight) {
            this.x = x;
            this.weight = weight;
        }
    }

    static class PqForm implements Comparable<PqForm> {
        int index;
        int dist;

        public PqForm(int index, int dist) {
            this.index = index;
            this.dist = dist;
        }

        @Override
        public int compareTo(PqForm o) {
            return Double.compare(this.dist, o.dist);
        }
    }
}
