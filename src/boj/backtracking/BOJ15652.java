package boj.backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ15652 {
    static int N, M;
    static StringBuilder sb;
    static List<Integer> selected;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        selected = new ArrayList<>();

        recursion();

        System.out.println(sb);
    }

    public static void recursion() {
        if (selected.size() == M) {
            selected.forEach(num -> sb.append(num).append(" "));
            sb.append("\n");
            selected.remove(selected.size() - 1);
            return;
        }

        int lastNum = selected.isEmpty() ? 1 : selected.get(selected.size() - 1);

        for (int i = lastNum; i <= N; ++i) {
            selected.add(i);
            recursion();
        }

        if(!selected.isEmpty()) selected.remove(selected.size() - 1);
    }
}
