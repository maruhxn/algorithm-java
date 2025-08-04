package boj.swm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ32953 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            int k = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < k; j++) {
                String stu  = st.nextToken();
                map.put(stu, map.getOrDefault(stu, 0) + 1);
            }
        }

        int cnt = 0;
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            if(entry.getValue() >= M) ++cnt;
        }

        System.out.println(cnt);
    }
}
