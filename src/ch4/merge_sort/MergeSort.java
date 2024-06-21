package ch4.merge_sort;

import java.io.*;
import java.util.StringTokenizer;

public class MergeSort {
    static int[] arr;
    static int[] tmp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[n];
        tmp = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        mergeSort(0, n - 1);

        for (int i = 0; i < arr.length; i++) {
            bw.write(arr[i] + " ");
        }

        bw.flush();
        bw.close();
    }

    private static void mergeSort(int st, int ed) {
        if(st >= ed) return;

        int mid = (st + ed) / 2; // 중간 위치를 계산하여 균등 분할 - 분할(Divide)
        mergeSort(st, mid); // 앞쪽 부분 리스트 정렬 - 정복(Conquer)
        mergeSort(mid, ed); // 뒷쪽 부분 리스트 정렬 - 정복(Conquer)
        merge(st, mid, ed); // 정렬된 2개의 부분 배열을 합병하는 과정 -결합(Combine)
    }

    private static void merge(int st, int mid, int ed) {
        int k = st; // 결과 배열에 대한 인덱스
        int index1 = st; // 왼쪽 리스트에 대한 인덱스
        int index2 = mid + 1; // 오른쪽 리스트에 대한 인덱스

        while (index1 <= mid && index2 <= ed) { // 병합 과정
            if(arr[index1] <= arr[index2]) tmp[k++] = arr[index1++];
            else tmp[k++] = arr[index2++];
        }

        // 한쪽 리스트가 먼저 끝난 경후, 남아 있는 값들 일괄 복사
        while (index1 <= mid) {
            tmp[k++] = arr[index1++];
        }

        while (index2 <= ed) {
            tmp[k++] = arr[index2++];
        }

        for (int i = st; i <= ed; i++) {
            arr[i] = tmp[i];
        }
    }
}
