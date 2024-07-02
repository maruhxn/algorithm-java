package ch8.union_find;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

// 여행 계획 짜기(G4)
public class p051 {
    static int[] A;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        A = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            A[i] = i;
        }


        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                int isLinked = sc.nextInt();
                if (isLinked == 1) union(i, j);
            }
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            int node = sc.nextInt();
            set.add(find(node));
        }

        if (set.size() == 1) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static int find(int a) {
        if (A[a] != a) {
            A[a] = find(A[a]);
        }
        return A[a];
    }

    private static void union(int a, int b) {
        if (a == b || A[a] == A[b]) return;
        int parentA = find(a);
        int parentB = find(b);
        A[parentB] = parentA;
    }
}
