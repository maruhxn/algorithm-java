package algospot.ch24;

// 펜윅 트리의 구현. 가상의 배열 A[]의 부분합을 빠르게 구할 수 있도록 한다.
// 초기화시에는 A[]의 원소가 전부 0이라고 생각한다.
public class FenwickTree {
    int[] tree;

    FenwickTree(int N) {
        tree = new int[N + 1];
    }

    /**
     * A[0..pos]의 부분 합을 구한다
     * O(lgN)
     *
     * @param pos
     * @return
     */
    public int prefixSum(int pos) {
        // 인덱스가 1부터 시작한다고 가정
        ++pos;
        int ret = 0;
        while (pos > 0) {
            ret += tree[pos];
            pos -= (pos & -pos); // 다음 구간을 찾기 위해 최종 비트를 지운다
        }

        return ret;
    }

    /**
     * A[pos]에 val을 더한다.
     * O(lgN)
     *
     * @param pos
     * @param val
     */
    public void update(int pos, int val) {
        ++pos;
        while (pos < tree.length) {
            tree[pos] += val;
            pos += (pos & -pos);
        }
    }

    /**
     * A[start..end]까지의 구간 합을 구한다. psum[end] - psum[start - 1]
     *
     * @param start
     * @param end
     * @return
     */
    public int intervalSum(int start, int end) {
        return prefixSum(end) - prefixSum(start - 1);
    }
}
