package algospot.ch22;

import java.util.TreeSet;

public class BSTMain {
    public static void main(String[] args) {
        BinaryTree bst = new BinaryTree();
        bst.insert(12);
        bst.insert(8);
        bst.insert(7);
        bst.insert(5);
        bst.insert(10);
        bst.insert(9);
        bst.insert(11);
        bst.insert(17);
        bst.insert(14);
        bst.insert(20);

        System.out.println(bst.getHeight());
        System.out.println(bst.getSize());

        bst.ascendingTraversal();
        bst.descendingTraversal();

        System.out.println("key 12 갖는 노드 삭제");
        bst.delete(12);

        bst.ascendingTraversal();
        bst.descendingTraversal();

        if (bst.search(11) != null) {
            System.out.println("노드를 찾았습니다.");
        }

        // TreeSet
        TreeSet<Integer> bst2 = new TreeSet<>();
        bst2.add(12);
        bst2.add(8);
        bst2.add(7);
        bst2.add(5);
        bst2.add(10);
        bst2.add(9);
        bst2.add(11);
        bst2.add(17);
        bst2.add(14);
        bst2.add(20);

        System.out.println(bst2.size());
        //오름차순 정렬
        for (Integer score : bst2) {
            System.out.print(score + " ");
        }
        System.out.println();

        //내림차순 정렬
        for (Integer score : bst2.descendingSet()) {
            System.out.print(score + " ");
        }
        System.out.println();

        System.out.println("key 12 갖는 노드 삭제");
        bst2.remove(12);

        //오름차순 정렬
        for (Integer score : bst2) {
            System.out.print(score + " ");
        }
        System.out.println();

        //내림차순 정렬
        for (Integer score : bst2.descendingSet()) {
            System.out.print(score + " ");
        }
        System.out.println();

        if (bst2.contains(11)) {
            System.out.println("노드를 찾았습니다.");
        }
    }

}
