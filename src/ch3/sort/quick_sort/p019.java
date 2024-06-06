package ch3.sort.quick_sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// K번째 수 구하기(S5)

/**
 * pivot을 더 효율적으로 선정하는 방법 공부 필요..
 * 또한, 필요한 계산만 하도록 코드를 개선해볼 필요도 있다. -> 우리는 2번째 요소만 필요함
 */
public class p019 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(bf.readLine());
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        quickSort(arr, 0, n - 1);
        System.out.println(arr[k - 1]);
    }

    private static void quickSort(long[] arr, int st, int ed) {
        if (st >= ed) return;

        int pivot = (st + ed) / 2;
        long pivotValue = arr[pivot];

        int left = st;
        int right = ed;
        while (left <= right) {
            while (arr[left] < pivotValue) left++;
            while (arr[right] > pivotValue) right--;
            if (left <= right) {
                swap(arr, left, right);
                left++;
                right--;
            }
        }

        quickSort(arr, st, right);
        quickSort(arr, left, ed);

    }

    private static void swap(long[] arr, int i, int j) {
        long temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

/**
 * 퀵 정렬 과정
 * 1. pivot 설정
 * 2. pivot을 기준으로 다음의 과정을 거쳐서 데이터를 2개의 집합으로 분할
 * 2-1. start < pivot -> start++
 * 2-2. end > pivot -> end--
 * 2-3. start > pivot && end < pivot -> swap(start, end), start++, end--
 * 2-4. start와 end가 만날 때까지 2-1 ~ 2-3 반복
 * 2-5. start와 end가 만나면 만난 지점의 데이터와 pivot의 데이터를 비교해서 pivot이 더 크면 만난 지점의 오른쪽, 작으면 만난 지점의 왼쪽에 pivot 데이터 삽입
 * 3. 분리 집합에서 다시 pivot 선정
 * 4. 분리 집합이 1개 이하가 될 때까지 1 ~ 3 반복
 * <p>
 * 퀵 정렬 과정
 * 1. pivot 설정
 * 2. pivot을 기준으로 다음의 과정을 거쳐서 데이터를 2개의 집합으로 분할
 * 2-1. start < pivot -> start++
 * 2-2. end > pivot -> end--
 * 2-3. start > pivot && end < pivot -> swap(start, end), start++, end--
 * 2-4. start와 end가 만날 때까지 2-1 ~ 2-3 반복
 * 2-5. start와 end가 만나면 만난 지점의 데이터와 pivot의 데이터를 비교해서 pivot이 더 크면 만난 지점의 오른쪽, 작으면 만난 지점의 왼쪽에 pivot 데이터 삽입
 * 3. 분리 집합에서 다시 pivot 선정
 * 4. 분리 집합이 1개 이하가 될 때까지 1 ~ 3 반복
 */

/**
 * 퀵 정렬 과정
 * 1. pivot 설정
 * 2. pivot을 기준으로 다음의 과정을 거쳐서 데이터를 2개의 집합으로 분할
 * 2-1. start < pivot -> start++
 * 2-2. end > pivot -> end--
 * 2-3. start > pivot && end < pivot -> swap(start, end), start++, end--
 * 2-4. start와 end가 만날 때까지 2-1 ~ 2-3 반복
 * 2-5. start와 end가 만나면 만난 지점의 데이터와 pivot의 데이터를 비교해서 pivot이 더 크면 만난 지점의 오른쪽, 작으면 만난 지점의 왼쪽에 pivot 데이터 삽입
 * 3. 분리 집합에서 다시 pivot 선정
 * 4. 분리 집합이 1개 이하가 될 때까지 1 ~ 3 반복
 */