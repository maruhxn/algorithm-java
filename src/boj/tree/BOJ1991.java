package boj.tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ1991 {

    static Map<String, String[]> tree;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        tree = new HashMap<>();

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String node = st.nextToken();
            String left = st.nextToken();
            String right = st.nextToken();

            tree.put(node, new String[]{left, right});
        }

        preOrder("A");
        sb.append("\n");
        inOrder("A");
        sb.append("\n");
        postOrder("A");

        System.out.println(sb);
    }

    static void preOrder(String root) {
        if (root.equals(".")) return;

        sb.append(root);
        String[] child = tree.get(root);
        preOrder(child[0]);
        preOrder(child[1]);
    }

    static void inOrder(String root) {
        if (root.equals(".")) return;

        String[] child = tree.get(root);
        inOrder(child[0]);
        sb.append(root);
        inOrder(child[1]);
    }

    static void postOrder(String root) {
        if (root.equals(".")) return;

        String[] child = tree.get(root);
        postOrder(child[0]);
        postOrder(child[1]);
        sb.append(root);
    }
}
