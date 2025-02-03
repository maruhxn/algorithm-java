package boj.unionfind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20040 {

    static int[] parent, rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N];
        rank = new int[N];

        for (int i = 0; i < N; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        int[][] turnData = new int[M][2];

        for (int currTurn = 0; currTurn < M; currTurn++) {
            st = new StringTokenizer(br.readLine());
            turnData[currTurn][0] = Integer.parseInt(st.nextToken());
            turnData[currTurn][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            int a = turnData[i][0];
            int b = turnData[i][1];

            if (find(a) == find(b)) {
                System.out.println(i + 1);
                System.exit(0);
            }
            union(a, b);
        }

        System.out.println(0);
    }

    static int find(int x) {
        return parent[x] == x ? x : (parent[x] = find(parent[x]));
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) return;

        if (rank[x] > rank[y]) {
            int temp = x;
            x = y;
            y = temp;
        }

        parent[x] = y;

        if (rank[x] == rank[y]) rank[y]++;
    }
}
