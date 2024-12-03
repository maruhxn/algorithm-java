package algospot.ch24;

// 배열의 구간 최소 쿼리를 해결하기 위한 세그먼트 트리 구현

import java.util.Arrays;

public class RMQ {

    private static int INT_MAX = 987654321;
    private int n; // 배열의 길이
    private int[] rangeMin;

    public RMQ(int[] array) {
        n = array.length;
        rangeMin = new int[n * 4];
        init(array, 0, n - 1, 1);
    }

    // node 노드가 array[left..right] 배열을 표현할 때
    // node를 루트로 하는 서브트리를 초기화하고, 이 구간의 최소치를 반환한다.
    private int init(int[] array, int left, int right, int node) {
        if (left == right) return rangeMin[node] = array[left];

        int mid = (left + right) / 2;
        int leftMin = init(array, left, mid, node * 2);
        int rightMin = init(array, mid + 1, right, node * 2 + 1);

        return rangeMin[node] = Math.min(leftMin, rightMin);
    }

    public int query(int left, int right) {
        return query(left, right, 1, 0, n - 1);
    }

    /**
     * 구간 트리의 질의 처리
     * node가 표현하는 범위 array[nodeLeft..nodeRight]가 주어질 때, 이 범위와 array[left..right]의 교집합의 최소치를 구한다
     * O(lgN)
     *
     * @param left
     * @param right
     * @param node
     * @param nodeLeft
     * @param nodeRight
     * @return
     */
    private int query(int left, int right, int node, int nodeLeft, int nodeRight) {
        // 두 구간이 겹치지 않으면 아주 큰 값을 반환하여 무시함
        if (right < nodeLeft || left > nodeRight) return INT_MAX;

        // node가 표현하는 범위가 array[left..right]에 완전히 포함되는 경우
        if (left <= nodeLeft && right >= nodeRight) return rangeMin[node];

        // 양쪽 구간을 나눠서 푼 뒤 결과를 합친다.
        int mid = (nodeLeft + nodeRight) / 2;
        return Math.min(query(left, right, node * 2, nodeLeft, mid),
                query(left, right, node * 2 + 1, mid + 1, nodeRight));
    }

    public int update(int index, int newValue) {
        return update(index, newValue, 1, 0, n - 1);
    }

    /**
     * 구간 트리의 갱신
     * array[index] = newValue로 바뀌었을 때 node를 루트로 하는 구간 트리를 갱신하고 노드가 표현하는 구간의 최소치를 반환한다.
     * O(lgN)
     *
     * @param index
     * @param newValue
     * @param node
     * @param nodeLeft
     * @param nodeRight
     * @return
     */
    private int update(int index, int newValue, int node, int nodeLeft, int nodeRight) {
        // index가 노드가 표현하는 구간과 상관없는 경우 무시한다
        if (index < nodeLeft || index > nodeRight) return rangeMin[node];

        // 트리의 리프까지 내려온 경우, 갱신 후 반환한다
        if (nodeLeft == nodeRight) return rangeMin[node] = newValue;

        int mid = (nodeLeft + nodeRight) / 2;
        return rangeMin[node] = Math.min(update(index, newValue, node * 2, nodeLeft, mid),
                update(index, newValue, node * 2 + 1, mid + 1, nodeRight));
    }

    public int[] queryTwoElements(int left, int right) {
        return queryTwoElements(left, right, 1, 0, n - 1);
    }

    /**
     * 특정 구간에서 최소치 두 개 찾기
     * node가 표현하는 범위 array[nodeLeft..nodeRight]가 주어질 때, 이 범위와 array[left..right]의 교집합의 최소치 2개를 구한다
     *
     * @param left
     * @param right
     * @param node
     * @param nodeLeft
     * @param nodeRight
     * @return
     */
    private int[] queryTwoElements(int left, int right, int node, int nodeLeft, int nodeRight) {
        // 두 구간이 겹치지 않으면 아주 큰 값을 반환하여 무시함
        if (right < nodeLeft || left > nodeRight) return new int[]{INT_MAX, INT_MAX};

        // 구간 길이가 1일 경우, 해당 구간에 포함된 숫자와 아주 큰 값 반환
        if (nodeLeft == nodeRight) return new int[]{rangeMin[node], INT_MAX};

        // 양쪽 구간을 나눠서 푼 뒤 결과를 합친다.
        int mid = (nodeLeft + nodeRight) / 2;

        int[] leftResult = queryTwoElements(left, right, node * 2, nodeLeft, mid);
        int[] rightResult = queryTwoElements(left, right, node * 2 + 1, mid + 1, nodeRight);

        int[] totalResult = new int[leftResult.length + rightResult.length];
        System.arraycopy(leftResult, 0, totalResult, 0, leftResult.length);
        System.arraycopy(rightResult, 0, totalResult, leftResult.length, rightResult.length);
        Arrays.sort(totalResult);

        return findTwoSmallestUniqueValues(totalResult);
    }

    /**
     * 중복을 제거한 후 가장 작은 두 값을 반환한다.
     *
     * @param array 입력 배열
     * @return 중복 제거 후 가장 작은 두 값
     */
    private int[] findTwoSmallestUniqueValues(int[] array) {
        // 배열 정렬
        Arrays.sort(array);

        // 중복을 제거하며 가장 작은 두 값을 찾음
        int firstMin = INT_MAX, secondMin = INT_MAX;

        for (int value : array) {
            if (value == firstMin) continue; // 중복값 무시
            if (value < firstMin) {
                secondMin = firstMin;
                firstMin = value;
            } else if (value < secondMin) {
                secondMin = value;
            }
        }

        return new int[]{firstMin, secondMin};
    }

    public static void main(String[] args) {
        int[] array = {0, 3, 2, 7, 9, 11, 4, 6};
        RMQ rmq = new RMQ(array);

        // 테스트 1: 구간 최소 쿼리
        System.out.println("Minimum in range [1, 4]: " + rmq.query(1, 4)); // Expected: 2
        System.out.println("Minimum in range [0, 7]: " + rmq.query(0, 7)); // Expected: 0

        // 테스트 2: 업데이트 후 구간 최소 쿼리
        rmq.update(2, 0);
        System.out.println("After update, minimum in range [1, 4]: " + rmq.query(1, 4)); // Expected: 0

        // 테스트 3: 두 최소값 구하기
        System.out.println("Two minimums in range [0, 7]: " + Arrays.toString(rmq.queryTwoElements(0, 7))); // Expected: [0, 1]
    }
}
