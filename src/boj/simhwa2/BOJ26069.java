package boj.simhwa2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ26069 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Set<String> list = new HashSet<>();
        list.add("ChongChong");

        StringTokenizer st;
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            String b = st.nextToken();

            if (list.contains(a)) {
                list.add(b);
            } else if (list.contains(b)) {
                list.add(a);
            }
        }

        System.out.println(list.size());
    }
}
