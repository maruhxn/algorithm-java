package boj.swm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ25634 {

    static int N, M, K;
    static ArrayList<Integer>[] graph;
    static boolean[] isDestroyed;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 1번부터 N번까지 1-indexed 그래프 초기화
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        // 도로 정보 입력 (양방향)
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        // 파괴된 도시 정보 입력
        K = Integer.parseInt(br.readLine());
        isDestroyed = new boolean[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int a = Integer.parseInt(st.nextToken());
            isDestroyed[a] = true;
        }

        // [후보 선별] 폭탄을 떨어뜨릴 수 있는 도시는 반드시 파괴된 도시여야 하고,
        // 그 도시의 모든 인접 도시도 파괴되어 있어야 함.
        ArrayList<Integer> candidates = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (isDestroyed[i]) {
                boolean candidate = true;
                for (int nb : graph[i]) {
                    if (!isDestroyed[nb]) {
                        candidate = false;
                        break;
                    }
                }
                if (candidate) {
                    candidates.add(i);
                }
            }
        }

        // 후보 폭탄들의 효과로 커버되는 파괴된 도시를 체크합니다.
        // 폭탄 효과: 폭탄이 떨어진 도시와 그 인접 도시(단, 파괴된 도시인 경우)
        Set<Integer> set = new HashSet<>();
        for (int bomb : candidates) {
            if (!set.contains(bomb)) set.add(bomb);
            for (int nb : graph[bomb]) {
                if (isDestroyed[nb] && !set.contains(nb)) set.add(nb);
            }
        }

        // 후보 폭탄들의 효과가 전체 파괴된 도시 집합을 정확히 커버하면 답 출력, 아니면 -1 출력
        if (candidates.isEmpty() || set.size() != K) {
            System.out.println(-1);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(candidates.size()).append("\n");
            for (int cand : candidates) {
                sb.append(cand).append(" ");
            }
            System.out.println(sb);
        }
    }
}
