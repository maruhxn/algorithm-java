package algospot.ch28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 무향 그래프에서 절단점을 찾는 알고리즘
public class FindCutVertex {
    static ArrayList<Integer>[] graph;
    // discovered[i] = i번 정점의 발견 순서 (-1로 초기화)
    static int[] discovered;
    // 각 정점이 절단점인지 여부 저장 (false로 초기화)
    static boolean[] isCutVertex;
    // 지금까지 발견한 정점의 수
    static int counter;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        graph = new ArrayList[N];
        discovered = new int[N];
        isCutVertex = new boolean[N];
        counter = 0;

        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
            discovered[i] = -1;
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        findCutVertex(5, true);

        for (int i = 0; i < isCutVertex.length; i++) {
            if (isCutVertex[i]) System.out.printf("%d ", i);
        }
    }

    // node를 루트로 하는 서브트리에 있는 절단점들을 찾는다
    // 반환 값은 해당 서브트리에서 역방향 간선으로 갈 수 있는 정점 중
    // 가장 일찍 발견된 정점의 발견 시점. 처음 호출할 때는 isRoot=true로 둔다.
    public static int findCutVertex(int node, boolean isRoot) {
        // 발견 순서 기록
        discovered[node] = counter++;
        int ret = discovered[node];

        // 루트인 경우의 절단점 판정을 위해 자손 서브트리의 개수를 센다.
        // 루트인 경우, 둘 이상의 자손을 가질 때만 절단점이 된다. (children >= 2일 경우 절단점)
        int children = 0;
        // 모든 인접 정점을 순회하면서
        for (int i = 0; i < graph[node].size(); i++) {
            int next = graph[node].get(i);

            if (discovered[next] == -1) { // 아직 방문한 적이 없다면
                ++children;

                // 이 서브트리에서 갈 수 있는 가장 높은 정점의 번호
                int subtree = findCutVertex(next, false);

                // 그 노드가 자기 자신 이하에 있다면 현재 위치는 절단점
                // subtree >= discovered[node]인 경우, 역방향 간선이 없다고 볼 수 있음(부모로의 역방향 간선은 제외)
                // subtree < discovered[node]인 경우, 역방향 간선을 통해 node보다 더 위의 선조로 갈 수 있음(역방향 간선이 있음)
                if (!isRoot && subtree >= discovered[node]) isCutVertex[node] = true;

                ret = Math.min(ret, subtree);
            } else { // 방문한 적이 있다면 (역방향 간선)
                ret = Math.min(ret, discovered[next]);
            }
        }

        if (isRoot) isCutVertex[node] = (children >= 2);
        return ret;
    }
}
