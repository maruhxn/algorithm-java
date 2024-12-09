package boj.dfs;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 단절점(P4)
// FindCutVertex를 이용하면 쉽게 구할 수 있음
public class BOJ11266 {

    static ArrayList<Integer>[] graph;
    static int[] discovered;
    static boolean[] isCutVertex;
    static int counter;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[V + 1];
        discovered = new int[V + 1];
        isCutVertex = new boolean[V + 1];

        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
            discovered[i] = -1;
        }

        // 그래프 만들기
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        for (int i = 1; i < discovered.length; i++) {
            if (discovered[i] == -1) findCutVertex(i, true);
        }

        List<Integer> result = new ArrayList<>();
        for (int j = 1; j < isCutVertex.length; j++) {
            if (isCutVertex[j]) result.add(j);
        }

        bw.append(result.size() + "\n");
        for (Integer i : result) {
            bw.append(i + " ");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int findCutVertex(int curr, boolean isRoot) {
        discovered[curr] = counter++;
        int ret = discovered[curr];

        int children = 0;
        for (int i = 0; i < graph[curr].size(); i++) {
            int next = graph[curr].get(i);

            if (discovered[next] == -1) {
                ++children;
                int subtree = findCutVertex(next, false);

                if (!isRoot && subtree >= discovered[curr]) isCutVertex[curr] = true;

                ret = Math.min(ret, subtree);
            } else {
                ret = Math.min(ret, discovered[next]);
            }
        }

        if (isRoot && children >= 2) isCutVertex[curr] = true;

        return ret;
    }
}
