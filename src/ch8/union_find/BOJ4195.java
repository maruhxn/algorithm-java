package ch8.union_find;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// 친구 네트워크(G2)
public class BOJ4195 {
    static Map<String, String> map;
    static Map<String, Integer> friendCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            map = new HashMap<>();
            friendCnt = new HashMap<>();
            int F = Integer.parseInt(br.readLine()); // F <= 100,000
            for (int i = 0; i < F; i++) {
                String[] relation = br.readLine().split(" ");
                String first = relation[0];
                String second = relation[1];
                if (!map.containsKey(first)) {
                    map.put(first, first);
                    friendCnt.put(first, 1);
                }
                if (!map.containsKey(second)) {
                    map.put(second, second);
                    friendCnt.put(second, 1);
                }
                union(first, second);
            }
        }

    }

    private static String find(String a) {
        if (map.get(a) != a) {
            map.put(a, find(map.get(a)));
        }
        return map.get(a);
    }

    private static void union(String a, String b) {
        if (a == b) return;
        String parentA = find(a);
        String parentB = find(b);
        if (parentA != parentB) {
            friendCnt.put(parentA, friendCnt.get(parentA) + friendCnt.get(parentB));
            map.put(parentB, parentA);
        }
        System.out.println(friendCnt.get(parentA));
    }
}
