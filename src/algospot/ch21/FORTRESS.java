package algospot.ch21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class FORTRESS {

    static int longest;
    static int n;
    static int[] x, y, radius;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());
        while (c-- > 0) {
            n = Integer.parseInt(br.readLine());
            x = new int[n];
            y = new int[n];
            radius = new int[n];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                x[i] = Integer.parseInt(st.nextToken());
                y[i] = Integer.parseInt(st.nextToken());
                radius[i] = Integer.parseInt(st.nextToken());
            }

            Node root = getTree(0);

            System.out.println(solve(root));
        }
    }

    public static int sqrDist(int a, int b) {
        return (int) (Math.pow(y[a] - y[b], 2) + Math.pow(x[a] - x[b], 2));
    }

    // 성벽 a가 성벽 b를 포함하는지 확인한다.
    public static boolean encloses(int a, int b) {
        return radius[a] > radius[b] && sqrDist(a, b) < Math.pow(radius[a] - radius[b], 2);
    }

    // 성벽 트리에서 parent가 child의 부모인지 확인한다
    // parent는 child를 꼭 '직접 포함'해야 한다
    public static boolean isChild(int parent, int child) {
        if (!encloses(parent, child)) return false;

        for (int i = 0; i < n; i++) {
            if (i != parent && i != child && encloses(parent, i) && encloses(i, child)) return false;
        }

        return true;
    }

    // root 성벽을 루트로 하는 트리를 생성한다
    public static Node getTree(int root) {
        Node ret = new Node();

        for (int ch = 0; ch < n; ch++) {
            // ch 성벽이 root 성벽에 직접적으로 포함되어 있다면
            // 트리를 만들고 자손 목록에 추가
            if (isChild(root, ch)) ret.children.add(getTree(ch));
        }

        return ret;
    }

    public static int height(Node root) {
        List<Integer> heights = new ArrayList<>();

        for (int i = 0; i < root.children.size(); i++) {
            heights.add(height(root.children.get(i)));
        }

        // 만약 자식이 하나도 없다면 0 반환
        if (heights.isEmpty()) return 0;

        heights.sort(Integer::compareTo);

        // root를 최상위 노드로 하는 경로를 고려
        if (heights.size() > 2) {
            longest = Math.max(longest, 2 + heights.get(heights.size() - 2) + heights.get(heights.size() - 1));
        }

        // 트리의 높이는 서브트리 노핑의 최대치에 1을 더해 계산
        return heights.get(heights.size() - 1) + 1;
    }

    public static int solve(Node root) {
        longest = 0;
        int h = height(root);
        return Math.max(longest, h);
    }

    static class Node {
        List<Node> children = new ArrayList<Node>();
    }
}
