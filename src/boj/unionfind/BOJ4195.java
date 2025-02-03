package boj.unionfind;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ4195 {

    static Map<String, String> parent;
    static Map<String, Integer> size;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int F = Integer.parseInt(br.readLine());

            parent = new HashMap<>();
            size = new HashMap<>();

            StringTokenizer st;
            for (int i = 0; i < F; i++) {
                st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b = st.nextToken();
                if (!parent.containsKey(a)) {
                    parent.put(a, a);
                    size.put(a, 1);
                }
                if (!parent.containsKey(b)) {
                    parent.put(b, b);
                    size.put(b, 1);
                }

                union(a, b);

                sb.append(size.get(find(b))).append("\n");
            }
        }
        System.out.println(sb);
    }

    static String find(String name) {
        if (parent.get(name).equals(name)) return name;
        parent.put(name, find(parent.get(name)));
        return parent.get(name);
    }

    static void union(String x, String y) {
        x = find(x);
        y = find(y);

        if (x.equals(y)) return;

        parent.put(x, y);
        size.put(y, size.get(x) + size.get(y));
    }
}
