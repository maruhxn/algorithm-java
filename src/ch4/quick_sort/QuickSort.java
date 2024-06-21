package ch4.quick_sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class QuickSort {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        quickSort(arr, 0, n - 1);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    private static void quickSort(int[] arr, int st, int ed) {
        if (st >= ed) return;

        int pivot = st;
        int pivotValue = arr[pivot];

        int left = st + 1;
        int right = ed;
        while (left <= right) {
            while (left <= ed && arr[left] < pivotValue) left++; // pivot보다 큰 값이 나올 때까지 left++
            while (right >= st && arr[right] > pivotValue) right--; // pivot보다 작은 값이 나올 때까지 right--
            if (left <= right) {
                swap(arr, left, right);
                left++;
                right--;
            }
        }
        swap(arr, pivot, right);

        quickSort(arr, st, right - 1);
        quickSort(arr, right + 1, ed);
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
