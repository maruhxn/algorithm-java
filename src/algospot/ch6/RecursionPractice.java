package algospot.ch6;

import java.util.ArrayList;
import java.util.List;

/**
 * n개의 원소 중 4래를 고르는 모든 경우를 출력
 */
public class RecursionPractice {
    public static void main(String[] args) {
        pick(7, new ArrayList<>(), 4);
    }

    /**
     * n 개의 원소 중 m개를 고르는 모든 조합을 찾는 알고리즘
     * @param n 전체 원소의 수
     * @param picked 지금까지 고른 원소들의 번호
     * @param toPick 더 고를 원소의 수
     */
    public static void pick(int n, List<Integer> picked, int toPick) {
        // 기저 사례: 모든 원소를 골랐다면, print한다.
        // 종료 조건: toPick == 0, 더 이상 고를 원소가 없음
        if (toPick == 0) {
            printPicked(picked);
            return;
        }

        // 고를 수 있는 가장 작은 번호를 계산한다
        // 지금까지 고른 게 없다면 0번, 있다면 마지막으로 고른 원소 + 1
        int smallest = picked.isEmpty() ? 0 : picked.getLast() + 1;

        // 원소를 하나 고른다
        for (int next = smallest; next < n; next++) {
            picked.add(next);
            pick(n, picked, toPick - 1);
            picked.removeLast();
        }
    }

    private static void printPicked(List<Integer> picked) {
        System.out.println(picked);
    }
}
