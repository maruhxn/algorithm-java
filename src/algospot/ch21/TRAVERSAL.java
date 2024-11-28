package algospot.ch21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 전위: 루트 -> 왼 -> 오
 * 중위: 왼 -> 루트 -> 오
 * 후위: 왼 -> 오 -> 루트
 */
public class TRAVERSAL {
    static List<Integer> result;
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

            result = new ArrayList<>();

            solve(0, n - 1, 0);

            for (int i = n - 1; i >= 0; --i) {
                System.out.print(result.get(i) + " ");
            }
        }
    }

    public static void solve(int st, int ed, int rootIndex) {
        if(st > ed) return;
        if (st == ed) {
            result.add(preorder[rootIndex]);
            return;
        }

        int subTreeRoot = preorder[rootIndex];
        result.add(subTreeRoot);

        int rootIndexOfInorder = -1;

        for (int i = st; i <= ed; i++) {
            if (inorder[i] == subTreeRoot) {
                rootIndexOfInorder = i;
                break;
            }
        }

        int leftSubSize = rootIndexOfInorder - st;

        solve(rootIndexOfInorder + 1, ed, rootIndex + leftSubSize + 1);
        solve(st, rootIndexOfInorder - 1, rootIndex + 1);
    }
}

/**
 * 1. 루트 찾고 결과 마지막에 넣기 -> preorder[0]
 * 2. 오른쪽 서브트리 호출 + 왼쪽 서브트리 호출
 */
