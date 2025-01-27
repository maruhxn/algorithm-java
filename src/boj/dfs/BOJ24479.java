package boj.dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ24479 {

    static ArrayList<Integer>[] graph;
    static int[] answer;
    static int depth;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N];
        answer = new int[N];

        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a - 1].add(b - 1);
            graph[b - 1].add(a - 1);
        }

        for (int i = 0; i < N; i++) {
            graph[i].sort((o1, o2) -> o1 - o2);
        }

        dfs(R - 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(answer[i]).append("\n");
        }

        System.out.println(sb);
    }

    static void dfs(int node) {
        depth++;
        answer[node] = depth;

        for (int i = 0; i < graph[node].size(); i++) {
            int next = graph[node].get(i);
            if (answer[next] == 0) {
                dfs(next);
            }
        }
    }
}
