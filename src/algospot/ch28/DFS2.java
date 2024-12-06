package algospot.ch28;

import java.util.ArrayList;

public class DFS2 {
    static ArrayList<Integer>[] graph;
    // discovered[i] = i번 정점의 발견 순서 (-1로 초기화)
    // finished[i] = dfs(i)가 종료했으면 1, 아니면 0
    static int[] discovered, finished;
    // 지금까지 발견한 정점의 수
    static int counter;

    public DFS2(int N) {
        graph = new ArrayList[N];
        discovered = new int[N];
        finished = new int[N];
        counter = 0;

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
            discovered[i] = -1;
        }
    }

    public static void dfs2(int node) {
        discovered[node] = counter++;
        // 모든 인접 정점을 순회하면서
        for (int i = 0; i < graph[node].size(); i++) {
            int next = graph[node].get(i);

            System.out.printf("(%d, %d) is a ", node, next);

            // 아직 방문한 적 없다면 방문
            if (discovered[next] == -1) {
                System.out.printf("tree edge\n");
                dfs2(next);
            }
            // 만약 next가 node보다 늦게 발견됐으면 next는 node의 후손(순방향 간선)
            else if (discovered[next] < discovered[node]) {
                System.out.printf("forward edge\n");
            }
            // 만약 dfs2(next)가 아직 종료하지 않았으면, next는 node의 선조(역방향 간선)
            else if (finished[next] == 0) {
                System.out.printf("back edge\n");
            }
            // 그 외에는 모두 교차 간선
            else {
                System.out.printf("cross edge\n");
            }

            // 현재 노드 방문했음을 알림
            finished[node] = 1;
        }
    }
}
