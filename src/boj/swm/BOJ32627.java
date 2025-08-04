package boj.swm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ32627 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String S = br.readLine();
        char[] chars = S.toCharArray();
        Map<Character, Queue<Integer>> map = new HashMap<>(); // 인덱스 저장

        for (int i = 0; i < N; i++) {
            map.computeIfAbsent(S.charAt(i), k -> new LinkedList<>()).add(i);
        }

        Object[] keyArr = map.keySet().toArray();
        Arrays.sort(keyArr);

        for (int i = 0; i < M; i++) {
            for(int j = 0; j < keyArr.length; j++) {
                Queue<Integer> indexes = map.get(keyArr[j]);
                if(indexes.isEmpty()) continue;

                int targetIdx = indexes.poll();
                chars[targetIdx] = '\0';
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(char c : chars) {
            if(c != '\0') sb.append(c);
        }

        System.out.println(sb);
    }
}
