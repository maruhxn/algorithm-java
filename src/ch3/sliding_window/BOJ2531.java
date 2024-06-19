package ch3.sliding_window;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// 회전 초밥(S1)

/**
 * Set으로 구현해보려고 했으나, 윈도우 안에 중복 자체를 허용하지 못하게 되므로 구현이 더 복잡해짐..
 * -> Map으로 구현하여 현재 보고 있는 윈도우 안의 값을 키로, 그 개수를 값으로 하여 저장.
 * <p>
 * + 굳이 Map을 쓰지 않고, int[] eat = new int[d]; 를 통해 해당 종류의 초밥을 몇개 먹었는지 저장하는 배열을 만들어 이것만으로도 풀 수 있을 듯.
 */
public class BOJ2531 {
    static Map<Integer, Integer> currMap;
    static int maxCnt = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 접시 수
        int d = sc.nextInt(); // 초밥 가짓 수
        int k = sc.nextInt(); // 연속해서 먹는 접시 수
        int c = sc.nextInt(); // 쿠폰 번호

        Integer[] A = new Integer[n + k - 1];
        for (int i = 0; i < n; i++) {
            A[i] = sc.nextInt();
        }
        for (int i = 0; i < k - 1; i++) {
            A[n + i] = A[i];
        }

        currMap = new HashMap<>();
        for (int i = 0; i < k; i++) { // 첫번째 윈도우에 대해 map 초기화
            add(A[i]);
        }
        add(c);
        updateMaxCnt();

        for (int i = k; i < n + k - 1; i++) {
            int j = i - k;
            remove(A[j]);
            add(A[i]);
            add(c);

            updateMaxCnt();
        }

        System.out.println(maxCnt);
    }

    private static void updateMaxCnt() {
        int size = currMap.size();
        if (maxCnt < size) maxCnt = size;
    }

    private static void remove(Integer val) {
        if (currMap.get(val) == 1) currMap.remove(val);
        else currMap.put(val, currMap.get(val) - 1);
    }

    private static void add(Integer val) {
        if (currMap.containsKey(val)) currMap.put(val, currMap.get(val) + 1);
        else currMap.put(val, 1);
    }
}
