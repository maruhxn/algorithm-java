package boj.recursion;

import java.io.*;
import java.util.*;

public class BOJ24060 {
    static int[] arr;
    static int[] tmp;
    static int n, c, cnt, ret;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new int[n];
        tmp = new int[n];
        for(int i = 0; i < n; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        ret = -1;

        mergeSort(0, n - 1);

        System.out.println(ret);
    }

    public static void mergeSort(int left, int right) {
        if(left >= right) return;
        if(ret != -1) return;

        int mid = (left + right) / 2;
        mergeSort(left, mid);
        mergeSort(mid + 1, right);
        merge(arr, left, mid, right);
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        if(ret != -1) return;
        int k = left;
        int index1 = left;
        int index2 = mid + 1;

        while (index1 <= mid && index2 <= right) { // 병합 과정
            if(arr[index1] <= arr[index2]) tmp[k++] = arr[index1++];
            else tmp[k++] = arr[index2++];
        }

        while(index1 <= mid) {
            tmp[k++] = arr[index1++];
        }

        while(index2 <= right) {
            tmp[k++] = arr[index2++];
        }

        // 복사
        for(int i = left; i <= right; ++i) {
            if(++cnt == c) ret = tmp[i];
            arr[i] = tmp[i];
        }
    }
}