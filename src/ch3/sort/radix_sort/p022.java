package ch3.sort.radix_sort;

import java.io.*;

// 수 정렬하기 3(B1)
public class p022 {
    public static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        br.close();
        radixSort(arr, 5);
        for (int i = 0; i < n; i++) {
            bw.write(arr[i] + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static void radixSort(int[] a, int maxSize) {
        int[] output = new int[a.length]; // 임시 정렬 배열
        int jaritsu = 1; // 현재 자릿수
        int count = 0;
        while (count != maxSize) { // 최대 자릿수만큼 반복
            int[] bucket = new int[10];
            for (int i = 0; i < a.length; i++) { // 현재 자릿수를 기준으로 a 배열 데이터를 bucket에 count
                bucket[(a[i] / jaritsu) % 10]++; // 일의 자리부터 시작
                // bucket: [0, 2, 2, 2, 1, 2, 0, 1, 0, 0]
            }

            for (int i = 1; i < 10; i++) { // 합 배열을 이용해 index 계산
                bucket[i] += bucket[i - 1]; // 합 배열 공식으로 bucket을 합 배열 형태로 변경
                // bucket: [0, 2, 4, 6, 7, 9, 9, 10, 10, 10]
            }

            for (int i = a.length - 1; i >= 0; i--) { // 현재 자릿수를 기준으로 정렬
                output[bucket[(a[i] / jaritsu % 10)] - 1] = a[i];
                bucket[(a[i] / jaritsu) % 10]--;
            }

            for (int i = 0; i < a.length; i++) { // 다음 자릿수를 이동하기 위해 현재 자릿수 기준 정렬 데이터 저장하기
                a[i] = output[i];
            }

            jaritsu = jaritsu * 10; // 자릿수 증가
            count++;
        }
    }
}
