package ch8.union_find;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// 거짓말(G4)
public class p052 {
    static int[] know_people;
    static int[] parent;
    static ArrayList<Integer>[] party;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < k; i++) {
            // 루트가 0이면 진실을 알고있는 집합
            union(0, sc.nextInt());
        }

        party = new ArrayList[m];
        for (int i = 0; i < m; i++) {
            party[i] = new ArrayList<Integer>();
            int comingNum = sc.nextInt();
            if (comingNum == 1) {
                party[i].add(sc.nextInt());
            } else {
                int prev = sc.nextInt();
                party[i].add(prev);
                for (int j = 0; j < comingNum - 1; j++) {
                    int num = sc.nextInt();
                    union(prev, num);
                    prev = num;
                    party[i].add(num);
                }
            }
        }

        for (int i = 0; i < m; i++) {
            int first = party[i].get(0);
            if(find(first) != 0) count++;
        }

        System.out.println(count);

    }

    private static int find(int a) {
        if (parent[a] != a) {
            parent[a] = find(parent[a]);
        }
        return parent[a];
    }

    private static void union(int a, int b) {
        if (a == b) return;
        int parentA = find(a);
        int parentB = find(b);
        if (parentA < parentB) {
            parent[parentB] = parentA;
        } else {
            parent[parentA] = parentB;
        }

    }
}
/**
 * 아는 집합, 모르는 집합
 * 파티에 아는 집합의 인원이 포함되어 있으면,
 */