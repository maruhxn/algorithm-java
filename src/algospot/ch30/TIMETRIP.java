package algospot.ch30;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 실패.. 플로이드 워셜까지 알아야됨 슈발

public class TIMETRIP {

    static final int INF = 987654321;
    static ArrayList<Node>[] graph;
    static ArrayList<Node>[] reverseGraph;
    static int V, W;

    static boolean[][] reachable;

    static void calcReachable() {
        for (int i = 0; i < V; i++)
            reachable[i][i] = true;

        for (int k = 0; k < V; k++)
            for (int i = 0; i < V; i++)
                for (int j = 0; j < V; j++)
                    reachable[i][j] = reachable[i][j] || (reachable[i][k] && reachable[k][j]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int C = Integer.parseInt(br.readLine());
        while (C-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken()); // 은하계의 수
            W = Integer.parseInt(st.nextToken()); // 웜홀의 수

            graph = new ArrayList[V];
            reverseGraph = new ArrayList[V];
            reachable = new boolean[V][V];

            for (int i = 0; i < V; i++) {
                graph[i] = new ArrayList<>();
                reverseGraph[i] = new ArrayList<>();
            }

            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                graph[a].add(new Node(b, c));
                reverseGraph[a].add(new Node(b, -c));
                reachable[a][b] = true;
            }

            calcReachable();

            if (graph[0].size() == 1 && graph[0].get(0).x == 0) {
                bw.append("UNREACHABLE\n");
            } else {
                int r1 = bellmanFord(graph);
                int r2 = bellmanFord(reverseGraph);

                String minResult = r1 == -INF ? "INFINITY" : String.valueOf(r1);
                String maxResult = r2 == -INF ? "INFINITY" : String.valueOf(-r2);

                // 간선이 존재하지 않는지 확인하기 위해 INF에서 적당히 큰 값 빼주기 (upper[v]는 경로가 없어도 갱신 가능)
                if (!reachable[0][1]) {
                    bw.append("UNREACHABLE\n");
                } else {
                    bw.append(minResult + " " + maxResult + "\n");
                }
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static int bellmanFord(ArrayList<Node>[] graph) {
        int[] upper = new int[V];
        Arrays.fill(upper, INF);
        upper[0] = 0;

        for (int iter = 0; iter < V - 1; iter++) {
            for (int curr = 0; curr < V; curr++) {
                for (int i = 0; i < graph[curr].size(); i++) {
                    Node next = graph[curr].get(i);
                    upper[next.x] = Math.min(upper[next.x], upper[curr] + next.time);
                }
            }
        }

        for (int curr = 0; curr < V; curr++) {
            for (int i = 0; i < graph[curr].size(); i++) {
                Node next = graph[curr].get(i);
                if (upper[next.x] > upper[curr] + next.time) {
                    // 음수 사이클이 경로상에 존재하면 무한으로 발산
                    if (reachable[0][curr] && reachable[curr][1])
                        return -INF;
                }
            }
        }

        return upper[1];
    }

    static class Node {
        int x;
        int time;

        public Node(int x, int time) {
            this.x = x;
            this.time = time;
        }
    }
}
