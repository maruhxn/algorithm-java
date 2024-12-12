package algospot.ch31;

import java.io.*;
import java.util.StringTokenizer;

// 밀집 행렬이므로 프림이 더 효율 좋을 듯?
// 이미 연결된 건물의 가중치를 0으로 두면 하나의 컴포넌트로 묶이는 효과
// 각 정점과 연결된 정점을 빠르게 조회하기 위해 인접 리스트가 아닌 인접 행렬 사용
public class LAN_Prim {

    static final int INF = 987654321;
    static int N, M;
    static double[][] graph;
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
            graph = new double[N][N];

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

            for (int u = 0; u < N; u++) {
                for (int v = 0; v < N; v++) {
                    double dist = calcDist(points[u], points[v]);
                    graph[u][v] = graph[v][u] = dist;
                }
            }

            for (int i = 0; i < M; i++) { // 케이블 연결 정보
                // 한 쌍의 건물을 연결하는 케이블이 두 개 이상 있을 수도 있음
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph[u][v] = graph[v][u] = 0;
            }

            double ret = prim();
            bw.append(String.format("%.7f\n", ret));
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static double prim() {
        // 해당 정점이 트리에 포함되어 있는지 여부를 담는 배열
        boolean[] added = new boolean[N];
        // 트리에 인접한 간선 중 해당 정점에 닿는 최소 간선의 정보를 담는 배열
        double[] minWeights = new double[N];
        // 각 정점이 트리와 연결되었는지 여부를 확인하기 위해, 사용하는 간선의 다른 한쪽 끝 정점을 담는 배열
        // ex) parent[0] = 1 -> 0번 정점이 (0, 1) 간선을 통해 트리에 연결됨
        int[] parent = new int[N];

        for (int i = 0; i < N; i++) {
            minWeights[i] = INF;
            parent[i] = -1;
        }

        // 가중치의 합
        double ret = 0;

        minWeights[0] = parent[0] = 0;
        for (int iter = 0; iter < N; iter++) {
            int u = -1;
            for (int v = 0; v < N; v++) {
                if (!added[v] && (u == -1 || minWeights[u] > minWeights[v])) {
                    u = v;
                }
            }

            ret += minWeights[u];
            added[u] = true;

            // u에 인접한 간선 (u, v)들을 검사
            for (int v = 0; v < graph[u].length; v++) {
                double weight = graph[u][v];
                // 인접한 정점이 아직 추가되지 않았고, 가중치가 지금까지 찾은 해당 정점까지의 최소 간선보다 작다면 정보 업데이트
                if (!added[v] && minWeights[v] > weight) {
                    parent[v] = u;
                    minWeights[v] = weight;
                }
            }
        }

        return ret;
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
