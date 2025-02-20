package boj.swm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ6550 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input;
        while((input = br.readLine()) != null && !input.isEmpty()) {
            String[] split = input.split(" ");
            String s = split[0];
            String t = split[1];

            int sIdx = 0;
            for (int i = 0; i < t.length(); i++) {
                if(sIdx >= s.length()) break;
                if(s.charAt(sIdx) == t.charAt(i)) ++sIdx;
            }

            if(sIdx == s.length()) sb.append("Yes\n");
            else sb.append("No\n");
        }

        System.out.println(sb);
    }
}
