package ch9.lowest_common_ancestor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// LCA 2(P5)
public class p075 {
    static int N, M, K;

    // LCA 관련 변수
    static int[] depth;    // depth[ i ] 는 i의 깊이
    static int[][] parent; // parent[K][V] 정점 V의 2^K번째 조상 정점 번호
    // parent[K][V] = parent[K-1][parent[K-1][V]];

    // TREE 변수
    static ArrayList[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 1. 입력 & 변수 준비
        N = Integer.parseInt(br.readLine());

        // 2^K >= N인 첫번째 K 찾기,  문제조건 : N >= 2, K를 -1부터 시작해야 아래 값이 나옴
        // N이 17이면 2^4 번째 조상까지 기록 필요    17 = 2^4 + 2^0
        // N이 16이면 2^4 번째 조상까지 기록 필요    16 = 2^4
        // N이 15이면 2^3 번째 조상까지 기록 필요    15 = 2^3 + 2^2 + 2^1 + 2^0
        K = -1;
        for (int i = 1; i <= N; i *= 2) {
            K++;
        }

        // LCA 관련 변수 초기화
        depth = new int[N + 1];
        parent = new int[K + 1][N + 1];          // 2^K 까지 표현해야하므로 K+1로 선언

        // TREE 변수 초기화
        tree = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<Integer>();
        }

        int a, b;
        for (int i = 1; i <= N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            // 양방향 간선
            tree[a].add(b);
            tree[b].add(a);
        }

        // 2. DEPTH 확인
        dfs(1, 1);

        // 3. 2^K 까지 parent 채우기
        fillParent();

        // 4. LCA 진행
        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            sb.append(LCA(a, b) + "\n");
        }

        System.out.println(sb.toString());

        br.close();
    }

    // DEPTH 확인, 부모 기록 DFS
    static void dfs(int id, int cnt) {
        // 1. depth를 기록
        depth[id] = cnt;

        // 2. 자식들의 depth를 기록
        int len = tree[id].size();
        for (int i = 0; i < len; i++) {
            int next = (Integer) tree[id].get(i);

            // 아직 깊이를 모르면 → 미방문 노드
            if (depth[next] == 0) {
                dfs(next, cnt + 1);
                parent[0][next] = id;        // 첫번째 부모를 기록  (2^0)
            }

        }
        return;
    }

    // 2 ^ K 부모 채우기
    static void fillParent() {
        // 2^K 번째 부모까지 채우기
        for (int k = 1; k <= K; k++) {
            for (int n = 1; n <= N; n++) {
                // parent[K][V] = parent[K-1][parent[K-1][V]];
                parent[k][n] = parent[k - 1][parent[k - 1][n]];
            }
        }
    }

    // 최소공통조상
    static int LCA(int a, int b) {
        // 1. depth[a] >= depth[b] 이도록 조정하기
        if (depth[a] < depth[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        // 2. 더 깊은 a를 2^K승 점프하여 depth를 맞추기
        //    깊이의 차를 2제곱수의 합을 이용해 좁히기 - 큰 수부터 Jump
        for (int i = K; i >= 0; i--) {
            if (Math.pow(2, i) <= depth[a] - depth[b]) {
                a = parent[i][a];
            }
        }

        // 3. depth를 맞췄는데 같다면 바로 종료
        if (a == b) return a;

        // 4. a-b는 같은 depth이므로 2^K승 점프하며 공통부모 바로 아래까지 올리기
        for (int i = K; i >= 0; i--) {
            if (parent[i][a] != parent[i][b]) {
                a = parent[i][a];
                b = parent[i][b];
            }
        }

        // 공통부모 바로 아래에서 반복문이 끝났으므로 첫번째 부모 (2^0) 리턴
        return parent[0][a];
    }
}
