package algospot.ch31;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

// 크루스칼 사용 -> 시간 초과..
public class LAN_Kruskal {

    static int N, M;
    static int[] parent, rank;
    static ArrayList<Node>[] graph;
    static Point[] points;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int C = Integer.parseInt(br.readLine());
        while (C-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 건물 수
            M = Integer.parseInt(st.nextToken()); // 케이블 수

            points = new Point[N];
            parent = new int[N];
            rank = new int[N];
            graph = new ArrayList[N];

            for (int i = 0; i < N; i++) {
                graph[i] = new ArrayList<>();
                parent[i] = i;
                rank[i] = 1;
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) { // 건물 x 좌표
                int x = Integer.parseInt(st.nextToken());
                points[i] = new Point(x);
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) { // 건물 y 좌표
                int y = Integer.parseInt(st.nextToken());
                points[i].setY(y);
            }

            for (int i = 0; i < M; i++) { // 케이블 연결 정보 (간선)
                // 한 쌍의 건물을 연결하는 케이블이 두 개 이상 있을 수도 있음
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                double dist = calcDist(points[u], points[v]);
                graph[u].add(new Node(v, dist));
                graph[v].add(new Node(u, dist));
            }

            double ret = kruskal();
            bw.append(String.format("%.7f\n", ret));
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static double kruskal() {
        double ret = 0;

        // (가중치, (정접1, 정점2))의 목록을 얻는다 : 간선의 목록 얻기
        List<Edge> edges = new ArrayList<>();
        for (int u = 0; u < N; u++) {
            for (int v = 0; v < N; v++) {
                edges.add(new Edge(calcDist(points[u], points[v]), u, v));
            }
        }

        // 가중치 기준 오름차순 정렬
        edges.sort(Comparator.comparingDouble(o -> o.weight));

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < graph[i].size(); j++) {
                union(i, graph[i].get(j).x);
            }
        }

        // 처음엔 모든 정점이 서로 분리
        for (int i = 0; i < edges.size(); i++) {
            // 간선 (u, v) 검사
            Edge currEdge = edges.get(i);

            // 이미 u와 v가 연결되어 있을 경우 -> 사이클이 생기므로 무시
            if (find(currEdge.u) == find(currEdge.v)) continue;

            // 아니라면 이 둘을 합친다
            union(currEdge.u, currEdge.v);
            ret += currEdge.weight;
        }

        return ret;
    }

    public static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        // 이미 같은 집합에 속해있는 경우 걸러내기
        if (x == y) return;

        // 항상 y의 높이가 더 크도록
        if (rank[x] > rank[y]) {
            int temp = x;
            x = y;
            y = temp;
        }

        parent[x] = y; // x를 y의 자식으로 넣기

        // 두 트리의 높이가 같은 경우 높이 1 증가
        if (rank[x] == rank[y]) ++rank[y];
    }

    static class Node {
        int x;
        double weight;

        public Node(int x, double weight) {
            this.x = x;
            this.weight = weight;
        }
    }

    static class Edge implements Comparable<Edge> {

        double weight;
        int u;
        int v;

        public Edge(double weight, int u, int v) {
            this.weight = weight;
            this.u = u;
            this.v = v;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(weight, o.weight);
        }
    }

    static class Point {
        int x, y;

        public Point(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }
    }

    private static double calcDist(Point a, Point b) {
        return Math.sqrt(Math.pow(b.x - a.x, 2) + Math.pow(b.y - a.y, 2));
    }
}
