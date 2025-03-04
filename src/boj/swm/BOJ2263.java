package boj.swm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2263 {

    static int[] inOrder, postOrder, inOrderIndex;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        inOrder = new int[n];
        postOrder = new int[n];
        inOrderIndex = new int[n + 1];  // 인덱스를 빠르게 찾기 위한 배열

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            inOrder[i] = Integer.parseInt(st.nextToken());
            inOrderIndex[inOrder[i]] = i;  // 값 -> 인덱스 매핑
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            postOrder[i] = Integer.parseInt(st.nextToken());
        }

        preOrder(0, n - 1, 0, n - 1);
        System.out.println(sb);
    }

    static void preOrder(int inStart, int inEnd, int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd) return;

        int root = postOrder[postEnd];  // 현재 트리의 루트
        sb.append(root).append(" ");

        int rootIndex = inOrderIndex[root];  // 인오더에서 루트의 위치
        int leftSize = rootIndex - inStart;  // 왼쪽 서브트리 크기

        // 왼쪽 서브트리
        preOrder(inStart, rootIndex - 1, postStart, postStart + leftSize - 1);
        // 오른쪽 서브트리
        preOrder(rootIndex + 1, inEnd, postStart + leftSize, postEnd - 1);
    }
}

/**
 * 포스트오더의 마지막 값이 항상 루트 노드
 *
 * 포스트오더 순회: 왼쪽 -> 오른쪽 -> 루트
 * 따라서, 포스트오더의 마지막 원소가 현재 트리의 루트가 됨.
 * 인오더를 통해 좌우 서브트리 구분
 *
 * 인오더 순회: 왼쪽 -> 루트 -> 오른쪽
 * 루트의 인덱스를 기준으로 좌우 서브트리를 나눌 수 있음.
 * 재귀적으로 처리
 * <p>
 * 루트를 출력한 후, 좌측 서브트리와 우측 서브트리에 대해 재귀 호출.
 */

/**
 * 7
 * 4 2 1 5 3 6 7
 * 4 2 5 7 6 3 1
 */