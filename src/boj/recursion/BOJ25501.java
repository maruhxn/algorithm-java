package boj.recursion;

import java.io.*;

public class BOJ25501 {
    static int cnt;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        while(n-- > 0) {
            String str = br.readLine();
            cnt = 1;

            sb.append(recursion(str, 0, str.length() - 1) + " " + cnt).append("\n");
        }

        System.out.println(sb);
    }

    public static int recursion(String s, int l, int r) {
        if(l >= r) return 1;
        if(s.charAt(l) != s.charAt(r)) return 0;

        cnt++;
        return recursion(s, l+1, r - 1);
    }
}
