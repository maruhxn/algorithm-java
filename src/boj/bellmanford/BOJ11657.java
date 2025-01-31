package boj.bellmanford;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ11657 {

    static int INF = 987654321;
    static ArrayList<Node>[] graph;
    static long[] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N];
        dist = new long[N];

        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = INF;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());

            graph[u].add(new Node(v, w));
        }

        dist[0] = 0;

        boolean updated = false;
        for (int iter = 0; iter < N; iter++) {
            updated = false;

            for (int curr = 0; curr < N; curr++) {
                for (int i = 0; i < graph[curr].size(); i++) {
                    Node next = graph[curr].get(i);
                    if (dist[curr] == INF || dist[next.x] <= dist[curr] + next.weight) continue;
                    dist[next.x] = dist[curr] + next.weight;
                    updated = true;
                }
            }

            if (!updated) break;
        }

        if (updated) {
            System.out.println(-1);
        } else {
            for (int i = 1; i < N; i++) {
                sb.append(dist[i] == INF ? -1 : dist[i])
                        .append("\n");
            }

            System.out.println(sb);
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
}

/**
 * 1번 정점에서 나머지 정점까지의 최소 거리 출력
 * 단, 음수 사이클이 존재하는 경우 -1 출력
 * 도달할 수 없다면 -1 출력
 */