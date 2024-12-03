package algospot.ch22;

import java.io.*;
import java.util.Random;
import java.util.StringTokenizer;

public class INSERTION_TREAP {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int C = Integer.parseInt(br.readLine());

        while (C-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] moves = new int[50000];
            int[] result = new int[50000];

            Node candidates = null;

            for (int i = 1; i <= N; i++) {
                candidates = insert(candidates, new Node(i));
            }

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                moves[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = N - 1; i >= 0; --i) {
                int offset = i + 1 - moves[i];
                Node k = kth(candidates, offset);
                result[i] = k.key;
                candidates = erase(candidates, k.key);
            }

            for (int i = 0; i < N; i++) {
                bw.write(result[i] + " ");
            }

            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static class Node {
        int key;  // 이 노드에 저장된 원소
        int priority; // 이 노드의 우선순위(proirity)
        int size; // 이 노드를 루트로 하는 서브트리의 크기
        Node left, right;

        // data, left, right 초기화 및 난수 우선순위 생성
        Node(int data) {
            this.key = data;
            this.priority = new Random().nextInt(100);
            this.left = this.right = null;
        }

        void setLeft(Node left) {
            this.left = left;
            calcSize();
        }

        void setRight(Node right) {
            this.right = right;
            calcSize();
        }

        void calcSize() {
            size = 1;
            if (left != null) size += left.size;
            if (right != null) size += right.size;
        }
    }

    public static Node insert(Node root, Node node) {
        if (root == null) {
            return node;
        }

        // node가 root를 대체헤야 한다. 해당 서브트리를 반으로 잘라 각각 자손으로 한다.
        if (root.priority < node.priority) {
            Node[] splitted = split(root, node.key);
            node.setLeft(splitted[0]);
            node.setRight(splitted[1]);
            return node;
        } else if (root.key > node.key) {
            root.setLeft(insert(root.left, node));
        } else {
            root.setRight(insert(root.right, node));
        }
        return root;
    }

    // split 두 개의 트립으로 분리한다.
    public static Node[] split(Node root, int key) {
        Node[] pair = new Node[2];
        if (root == null) {
            return pair;
        }

        // 루트가 key 미만이면 오른쪽 서브트리를 쪼갠다.
        if (root.key < key) {
            Node[] rs = split(root.right, key);
            root.setRight(rs[0]);
            return new Node[]{root, rs[1]};
        } else { // root가 key 이상이면 왼쪽 서브트리를 쪼갠다.
            Node[] ls = split(root.left, key);
            root.setLeft(ls[1]);
            return new Node[]{ls[0], root};
        }
    }

    // root를 루트로 하는 트립에서 key를 지우고 결과 트립의 루트를 반환한다.
    public static Node erase(Node root, int key) {
        if (root == null) return root;

        // root를 지우고 양 서브트리를 합친 뒤 반환한다.
        if (root.key == key) {
            Node ret = merge(root.left, root.right);
            root = null;
            return ret;
        }

        if (root.key > key) {
            root.setLeft(erase(root.left, key));
        } else {
            root.setRight(erase(root.right, key));
        }
        return root;
    }

    // a와 b가 두 개의 트립이고, max(a) < min(b)일 때 이 둘을 합친다.
    private static Node merge(Node a, Node b) {
        if (a == null) return b;
        if (b == null) return a;

        if (a.priority < b.priority) {
            b.setLeft(merge(a, b.left));
            return b;
        }
        a.setRight(merge(a.right, b));
        return a;
    }

    // root를 루트로 하는 트리 중에서 k번째 원소를 반환한다.
    public static Node kth(Node root, int k) {
        // 왼쪽 서브트리의 크기를 우선 계산한다.
        int leftSize = 0;

        if (root.left != null) {
            leftSize = root.left.size;
        }
        if (k <= leftSize) {
            return kth(root.left, k);
        }
        if (k == leftSize + 1) return root;

        return kth(root.right, k - leftSize - 1);
    }

// root를 루트로 하는 트리에서 key보다 작은 키 값의 수를 반환한다.
public static int countLessThan(Node root, int key) {
    if (root == null) return 0;

    if (root.key >= key) return countLessThan(root.left, key);

    int ls = root.left != null ? root.left.size : 0;

    return ls + 1 + countLessThan(root.right, key);
}

}
