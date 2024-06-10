package ch5.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

// 연결 요소의 개수 구하기(S2)
public class p023 {
    static ArrayList<Integer>[] A;
    static boolean visited[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        A = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        int result = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 1; i <= n; i++) {
            A[i] = new ArrayList<>();
        }
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            A[s].add(e);
            A[e].add(s);
        }

        for (int i = 1; i <= n; i++) {
            if (visited[i]) continue;
            stack.push(i);
            visited[i] = true;
            while (!stack.isEmpty()) {
                Integer popped = stack.pop();
                A[popped].forEach(value -> {
                    if (!visited[value]) {
                        stack.push(value);
                        visited[value] = true;
                    }
                });
            }
            result++;
        }

        System.out.println(result);
    }
}
