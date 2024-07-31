package ch9.binary_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// 이진 검색 트리(G4)
public class BOJ5639 {

    static class Node {
        int v;
        Node left;
        Node right;

        public Node(int v) {
            this.v = v;
        }
    }

    static List<Integer> arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new ArrayList<>();
        while (true) {
            String s = br.readLine();
            if (s == null)
                break;

            if (s.length() <= 0)
                break;
            arr.add(Integer.parseInt(s));
        }

        Node rootNode = new Node(arr.get(0));
        for (int i = 1; i < arr.size(); i++) {
            Integer v = arr.get(i);
            Node currNode = rootNode;

            while (true) {
                if (currNode.v > v) {
                    if (currNode.left != null) currNode = currNode.left;
                    else {
                        currNode.left = new Node(v);
                        break;

                    }
                } else if (currNode.v < v) {
                    if (currNode.right != null) currNode = currNode.right;
                    else {
                        currNode.right = new Node(v);
                        break;
                    }
                }
            }

        }

        postOrder(rootNode);

    }

    private static void postOrder(Node node) {
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.v);
    }
}