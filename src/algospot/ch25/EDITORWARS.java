package algospot.ch25;

import java.io.*;
import java.util.StringTokenizer;

// 실패..
public class EDITORWARS {

    static int n, m;
    static int[] parent, rank, enemy, size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int c = Integer.parseInt(br.readLine());
        while (c-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            parent = new int[n];
            rank = new int[n];
            enemy = new int[n];
            size = new int[n];

            boolean hasParadox = false;

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
                size[i] = 1;
                enemy[i] = -1;
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                String type = st.nextToken();
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if(hasParadox) continue;

                if (type.equals("ACK")) {
                    if (!ack(a, b)) {
                        hasParadox = true;
                        bw.append("CONTRADICTION AT " + (i + 1) + "\n");
                    }
                } else {
                    if (!dis(a, b)) {
                        hasParadox = true;
                        bw.append("CONTRADICTION AT " + (i + 1) + "\n");
                    }
                }

            }

            if(!hasParadox) bw.append("MAX PARTY SIZE IS " + maxParty() + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    private static int union(int x, int y) {
        // x나 y가 공집합인 경우 나머지 하나를 반환
        if (x == -1 || y == -1) return Math.max(x, y);

        x = find(x);
        y = find(y);

        // 이미 같은 집합에 속한 경우
        if (x == y) return x;

        // 항상 y의 높이가 더 크도록
        if (rank[x] > rank[y]) {
            int temp = x;
            x = y;
            y = temp;
        }
        if (rank[x] == rank[y]) ++rank[y];

        parent[x] = y;
        size[y] += size[x];
        return y;
    }

    // x와 y가 서로 적. 모순이 일어났다면 false, 아니면 true를 반환
    private static boolean dis(int x, int y) {
        // 우선 루트를 찾는다
        x = find(x);
        y = find(y);

        // 서로 같은 집합에 있었다면 이는 모순
        if (x == y) return false;

        // 적의 적은 나의 아군
        int a = union(x, enemy[y]);
        int b = union(y, enemy[x]);

        // 결과로 얻은 두 집합 역시 서로 적대 관계
        enemy[a] = b;
        enemy[b] = a;

        return true;
    }

    // x와 y가 서로 아군. 모순이 일어났다면 false, 아니면 true를 반환
    private static boolean ack(int x, int y) {
        // 우선 루트를 찾는다
        x = find(x);
        y = find(y);

        // 두 집합이 서로 적대관계라면 모순
        if (enemy[x] == y) return false;

        // 아군의 적은 나의 적
        int a = union(x, y); // 아군끼리 합쳐줌
        int b = union(enemy[x], enemy[y]); // 적끼리 합쳐줌

        enemy[a] = b;
        // 두 집합 다 적대하는 집합이 없으면 b는 -1(공집합)일 수도 있다.
        if (b != -1) enemy[b] = a;

        return true;
    }

    private static int maxParty() {
        int ret = 0;

        for (int node = 0; node < n; ++node) {
            if (parent[node] == node) {
                // 같은 모임 쌍을 두 번 세지 않기 위해 enemy < node인 경우만 센다
                // enemy == -1인 경우도 정확히 한 번씩 세게 된다.

                if (enemy[node] > node) continue;
                int mySize = size[node];
                int enemySize = enemy[node] == -1 ? 0 : size[enemy[node]];

                // 두 집합 중 큰 집합을 더한다
                ret += Math.max(mySize, enemySize);
            }
        }

        return ret;
    }
}
