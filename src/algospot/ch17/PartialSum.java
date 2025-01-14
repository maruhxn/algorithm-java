package algospot.ch17;

// psum: 부분 합 (= 누적 합): 배열의 각 위치에 대해 배열의 시작부터 현재 위치까지의 원소의 합을 구해 둔 배열
public class PartialSum {

    static int[] pSum;
    static int[][] pSum2;

    static void partialSum(int[] arr) {
        pSum = new int[arr.length];
        pSum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            pSum[i] = pSum[i - 1] + arr[i];
        }
    }

    static int rangeSum(int st, int ed) {
        if (st == 0) return pSum[ed];
        return pSum[ed] - pSum[st - 1];
    }

    static int gridSum(int y1, int x1, int y2, int x2) {
        int ret = pSum2[y2][x2];
        if (y1 > 0) ret -= pSum2[y1 - 1][x2];
        if (y2 > 0) ret -= pSum2[y2][x1 - 1];
        if (y1 > 0 && x1 > 0) ret += pSum2[y1 - 1][x1 - 1];
        return ret;
    }
}
