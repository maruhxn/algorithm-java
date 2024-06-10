package ch4.merge_sort;

import java.io.*;

// 수 정렬하기 2(S5)
public class p020 {
    public static int[] arr, tmp;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(bf.readLine());
        arr = new int[n];
        tmp = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }

        mergeSort(0, n - 1);
        for (int i = 0; i < n; i++) {
            bw.write(arr[i] + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static void mergeSort(int s, int e) {
        if (s >= e) return;
        int m = s + (e - s) / 2;
        mergeSort(s, m);
        mergeSort(m + 1, e);
        for (int i = s; i <= e; i++) { // tmp 배열에 일단 옮기기
            tmp[i] = arr[i];
        }

        int k = s; // arr의 요소를 가리키는 포인터
        int p = s;
        int q = m + 1;
        while (p <= m && q <= e) { // 두 그룹 병합
            if (tmp[p] > tmp[q]) {
                arr[k++] = tmp[q++];
            } else {
                arr[k++] = tmp[p++];
            }
        }

        while (p <= m) { // 한쪽 그룹이 모두 선택된 후 남아있는 값 정리
            arr[k++] = tmp[p++];
        }

        while (q <= e) {
            arr[k++] = tmp[q++];
        }
    }
}
