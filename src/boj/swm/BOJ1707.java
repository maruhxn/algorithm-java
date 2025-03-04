package boj.swm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1707 {

    static ArrayList<Integer>[] graph;
    static int[] color; // 1 -> RED, 0 -> 무색(아직 집합에 속하지 않음), -1 -> BLACK

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            graph = new ArrayList[V + 1];
            color = new int[V + 1];

            for (int i = 1; i <= V; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                graph[a].add(b);
                graph[b].add(a);
            }

            boolean isBipartite = true;
            for (int i = 1; i <= V; i++) {
                if (color[i] == 0) {
                    if (!dfs(i, 1)) {
                        isBipartite = false;
                        break;
                    }
                }
            }

            sb.append(isBipartite ? "YES" : "NO").append("\n");
        }

        System.out.println(sb);
    }

    static boolean dfs(int node, int c) {
        color[node] = c;

        for (int next : graph[node]) {
            if (color[next] == 0) {  // 인접 노드가 아직 색칠되지 않은 경우
                if (!dfs(next, -c)) {  // 반대 색으로 색칠 시도
                    return false;
                }
            } else if (color[next] == color[node]) {  // 인접 노드가 같은 색인 경우
                return false;
            }
        }
        return true;
    }
}
