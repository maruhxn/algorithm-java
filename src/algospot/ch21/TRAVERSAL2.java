package algospot.ch21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.util.Arrays.copyOfRange;

/**
 * 전위: 루트 -> 왼 -> 오
 * 중위: 왼 -> 루트 -> 오
 * 후위: 왼 -> 오 -> 루트
 */
public class TRAVERSAL2 {
    static int[] preorder, inorder;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());
        while (c-- > 0) {
            n = Integer.parseInt(br.readLine());
            preorder = new int[n];
            inorder = new int[n];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                preorder[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                inorder[i] = Integer.parseInt(st.nextToken());
            }

            printPostOrder(preorder, inorder);
        }
    }

    public static void printPostOrder(int[] preorder, int[] inorder) {
        // 트리에 포함된 노드의 수
        int N = preorder.length;

        // 기저 사례: 텅 빈 트리면 종료
        if (preorder.length == 0) return;

        // 이 트리의 루트는 전위 탐색 결과로부터 곧장 알 수 있음
        int root = preorder[0];

        // 이 트리의 왼쪽 서브트리의 크기는 중위 탐색 결과에서 루트의 위치를 찾아내어 알 수 있다
        int rootIndexOfInorder = 0;
        while (inorder[rootIndexOfInorder] != root) {
            rootIndexOfInorder++;
        }

        int L = rootIndexOfInorder;

        printPostOrder(copyOfRange(preorder, 1, L + 1), copyOfRange(inorder, 0, L));
        printPostOrder(copyOfRange(preorder, L + 1, N), copyOfRange(inorder, L + 1, N));

        System.out.print(root + " ");
    }
}

/**
 * 1. 루트 찾고 결과 마지막에 넣기 -> preorder[0]
 * 2. 오른쪽 서브트리 호출 + 왼쪽 서브트리 호출
 */
