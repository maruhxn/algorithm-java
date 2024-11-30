package algospot.ch22;

public class BinaryTree<T extends Comparable<T>> {
    Node<T> root;

    // 전위 순회
    public void preorder() {
        this.preorder(this.root);
        System.out.println();
    }

    private void preorder(Node<T> n) {
        if (n != null) {
            System.out.print(n.item + " ");
            preorder(n.left);
            preorder(n.right);
        }
    }

    // 중위 순회
    public void ascendingTraversal() {
        this.leftInOrder(this.root);
        System.out.println();
    }

    public void descendingTraversal() {
        this.rightInOrder(this.root);
        System.out.println();
    }

    private void leftInOrder(Node<T> n) {
        if (n != null) {
            leftInOrder(n.left);
            System.out.print(n.item + " ");
            leftInOrder(n.right);
        }
    }

    private void rightInOrder(Node<T> n) {
        if (n != null) {
            rightInOrder(n.right);
            System.out.print(n.item + " ");
            rightInOrder(n.left);
        }
    }

    // 후위 순회
    public void postorder() {
        this.postorder(this.root);
        System.out.println();
    }

    private void postorder(Node<T> n) {
        if (n != null) {
            postorder(n.left);
            postorder(n.right);
            System.out.print(n.item + " ");
        }
    }

    public int getHeight() {
        return this.getHeight(this.root);
    }

    private int getHeight(Node<T> n) {
        if (n == null) {
            return 0;
        } else {
            return 1 + Math.max(getHeight(n.left), getHeight(n.right));
        }
    }

    public int getSize() {
        return this.getSize(this.root);
    }

    private int getSize(Node<T> root) {
        if (root == null) {
            return 0;
        } else {
            return 1 + getSize(root.left) + getSize(root.right);
        }
    }

    // 탐색
    public Node search(T k) {
        return search(k, this.root);
    }

    private Node search(T k, Node n) {
        if (n == null) return null;

        int comp = n.item.compareTo(k);

        if (comp == 0) {
            return n;
        } else if (comp > 0) {  // 현재 노드보다 키값이 작은 경우
            return search(k, n.left);
        } else {
            return search(k, n.right);
        }
    }

    // 삽입

    public void insert(T k) {
        this.root = insert(k, root);
    }

    private Node insert(T k, Node n) {
        if (n == null) return new Node(k);

        int comp = n.item.compareTo(k);

        if (comp > 0) {
            n.setLeft(insert(k, n.left));
        } else {
            n.setRight(insert(k, n.right));
        }

        return n;
    }

    public void delete(T k) {
        this.root = delete(k, this.root);
    }

    // 삭제

    private Node<T> delete(T k, Node<T> n) {
        if (n == null) return null; // 노드가 리프일 경우 -> 해당 노드를 null로 설정

        // 일단 삭제할 노드 찾기
        int comp = k.compareTo(n.item);

        if (comp < 0) {
            // 삭제할 값이 현재 노드보다 작으면 왼쪽 서브트리로 이동
            n.left = delete(k, n.left);
        } else if (comp > 0) {
            // 삭제할 값이 현재 노드보다 크면 오른쪽 서브트리로 이동
            n.right = delete(k, n.right);
        } else {
            // 삭제할 노드를 찾은 경우
            if (n.left == null) {
                // 왼쪽 자식이 없는 경우, 오른쪽 자식을 반환 (자식이 없으면 null 반환)
                return n.right;
            } else if (n.right == null) {
                // 오른쪽 자식이 없는 경우, 왼쪽 자식을 반환
                return n.left;
            } else {
                // 자식이 둘인 경우 오른쪽 서브트리의 최소값을 찾아 현재 노드와 교체
                Node<T> minNode = findMin(n.right);
                n.item = minNode.item;
                // 오른쪽 서브트리에서 최소값 노드를 삭제
                n.right = delete(minNode.item, n.right);
            }
        }

        return n;
    }

    // 오른쪽 서브트리에서 최소값 노드 찾기
    private Node<T> findMin(Node<T> n) {
        while (n.left != null) {
            n = n.left;
        }
        return n;
    }

    // Getter & Setter

    public Node<T> getRoot() {
        return root;
    }

    public void setRoot(Node<T> root) {
        this.root = root;
    }
}

class Node<T extends Comparable<T>> {
    T item;
    Node<T> left;
    Node<T> right;

    public Node(T item) {
        this.item = item;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }
}
