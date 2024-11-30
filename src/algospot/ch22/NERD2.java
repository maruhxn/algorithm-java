package algospot.ch22;

import java.io.*;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * 처음엔 그냥 배열에 Node를 저장해서 정렬된 배열을 사용하려고 했으나, 원소를 추가하거나 삭제하는 부분에서 시간이 오래 걸릴 것이라고 판단.. + 배열 사용 시 O(N^2)이므로 불가능
 * -> 이진 검색 트리 필요
 */
public class NERD2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int C = Integer.parseInt(br.readLine());
        while (C-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int result = 0;

            TreeMap<Integer, Integer> tree = new TreeMap<>();

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int p = Integer.parseInt(st.nextToken());
                int q = Integer.parseInt(st.nextToken());

                if (isValidNerd(tree, p, q)) {
                    tree.put(p, q);

                    Iterator<Entry<Integer, Integer>> iterator = tree.headMap(p, false).descendingMap().entrySet().iterator();
                    while (iterator.hasNext()) {
                        Entry<Integer, Integer> entry = iterator.next();
                        if (entry.getValue() < q) {
                            iterator.remove(); // Iterator를 사용해 안전하게 제거
                        } else {
                            break; // 더 작은 pi의 값도 qi가 더 크다면 이후는 검사할 필요가 없음
                        }
                    }
                }

                result += tree.size();
            }

            bw.write(Integer.toString(result));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    // 새로 들어오는 값이 너드인지 확인하는 메서드
    private static boolean isValidNerd(TreeMap<Integer, Integer> tree, int pi, int qi) {
        Entry<Integer, Integer> higherEntry = tree.higherEntry(pi);
        return higherEntry == null || higherEntry.getValue() < qi;
    }
}
