package boj.setandmap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * TreeSet<>(Collections.reverseOrder()) 을 이용하면 더 빠르게 조회 가능
 */
public class BOJ7785 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Map<String, String> map = new HashMap();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String status = st.nextToken();
            if (status.equals("enter")) {
                map.put(name, name);
            } else {
                map.remove(name);
            }
        }
        Object[] names = map.keySet().toArray();
        Arrays.sort(names, Collections.reverseOrder());

        for (Object name : names) {
            System.out.println(map.get(name));
        }
    }
}
