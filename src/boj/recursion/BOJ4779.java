package boj.recursion;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ4779 {
    static String ret;
    static StringBuilder sb;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        String input;

        while ((input = br.readLine()) != null && !input.isEmpty()) {
            int N = Integer.parseInt(input);
            recursion(N);
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void recursion(int n) {
        if (n == 0) {
            sb.append("-");
            return;
        }

        // 선 추가
        recursion(n - 1);

        // 공백 추가
        for (int i = 0; i < Math.pow(3, n - 1); ++i) {
            sb.append(" ");
        }

        // 선 추가
        recursion(n - 1);
    }
}
