package ch8.union_find;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 집합 표현하기(G5)
public class p050 {
    static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        A = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            A[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (op == 0) {
                union(a, b);
            } else {
                System.out.println(find(a) == find(b) ? "yes" : "no");
            }
        }
    }

    private static int find(int a) {
        if (A[a] != a) {
            A[a] = find(A[a]);
        }
        return A[a];
    }

    private static void union(int a, int b) {
        if (a == b) return;
        int parentA = find(a);
        int parentB = find(b);
        A[parentB] = parentA;
    }
}
