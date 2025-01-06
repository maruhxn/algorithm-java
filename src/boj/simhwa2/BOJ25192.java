package boj.simhwa2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BOJ25192 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int ret = 0;
        Set<String> set = new HashSet<>();
        while(N-- > 0) {
            String s = br.readLine();
            if (s.equals("ENTER")) {
                set.clear();
                continue;
            }
            if(set.contains(s)) continue;

            set.add(s);
            ++ret;
        }

        System.out.println(ret);
    }
}
