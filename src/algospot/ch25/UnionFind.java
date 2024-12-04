package algospot.ch25;

public class UnionFind {

    int[] parent, rank;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public void union(int x, int y) {
        x = find(x);
        y = find(y);

        // 이미 같은 집합에 속해있는 경우 걸러내기
        if (x == y) return;

        // 항상 y의 높이가 더 크도록
        if (rank[x] > rank[y]) {
            int temp = x;
            x = y;
            y = temp;
        }

        parent[x] = y; // x를 y의 자식으로 넣기

        // 두 트리의 높이가 같은 경우 높이 1 증가
        if (rank[x] == rank[y]) ++rank[y];
    }
}
