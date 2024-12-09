package algospot.ch30;

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class ROUTING {

    static int N, M;
    static ArrayList<Node>[] graph;
    static double[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int C = Integer.parseInt(br.readLine());
        while (C-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            graph = new ArrayList[N];
            dist = new double[N];
            for (int i = 0; i < N; i++) {
                graph[i] = new ArrayList<>();
                dist[i] = Double.MAX_VALUE;
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                double w = Double.parseDouble(st.nextToken());
                graph[u].add(new Node(v, w));
                graph[v].add(new Node(u, w));
            }

            dijkstra(0);

            bw.write(String.format("%.10f\n", dist[N - 1]));
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void dijkstra(int start) {
        dist[start] = 1;
        PriorityQueue<PqForm> pq = new PriorityQueue<>();
        pq.add(new PqForm(start, 1));

        while (!pq.isEmpty()) {
            PqForm curr = pq.poll();
            int here = curr.index;
            double cost = curr.dist;

            if (dist[here] < cost) continue;

            for (int i = 0; i < graph[here].size(); i++) {
                Node next = graph[here].get(i);
                double nextDist = cost * next.weight;
                if (dist[next.x] > nextDist) {
                    dist[next.x] = nextDist;
                    pq.add(new PqForm(next.x, nextDist));
                }
            }
        }
    }

    static class Node {
        int x;
        double weight;

        public Node(int x, double weight) {
            this.x = x;
            this.weight = weight;
        }
    }

    static class PqForm implements Comparable<PqForm> {
        int index;
        double dist;

        public PqForm(int index, double dist) {
            this.index = index;
            this.dist = dist;
        }


        @Override
        public int compareTo(PqForm o) {
            return Double.compare(this.dist, o.dist);
        }
    }
}
