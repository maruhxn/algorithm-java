package boj.prefix_sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ16139_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str = br.readLine();
        int q = Integer.parseInt(br.readLine());

        Map<Character, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            map.computeIfAbsent(str.charAt(i), k -> new ArrayList<>()).add(i);
        }

        StringTokenizer st;
        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());
            char c = st.nextToken().charAt(0);
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            List<Integer> indexes = map.computeIfAbsent(c, k -> new ArrayList<>());

            int cnt = 0;
            for (int curr : indexes) {
                if (curr <= r && curr >= l) cnt++;
            }

            sb.append(cnt).append("\n");
        }

        System.out.println(sb);
    }
}
