package boj.swm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ14584 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int N = Integer.parseInt(br.readLine());
        String[] words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        for (int moveCnt = 0; moveCnt < 26; moveCnt++) {
            StringBuilder sb = new StringBuilder();
            // 해독
            for (int i = 0; i < s.length(); ++i) {
                char c = s.charAt(i);
                sb.append(c - moveCnt < 'a' ? (char) (c - moveCnt + 26) : (char) (c - moveCnt));
            }

            String t = sb.toString();
            for (String target : words) {
                if (t.contains(target)) {
                    System.out.println(t);
                    return;
                }
            }
        }
    }
}
