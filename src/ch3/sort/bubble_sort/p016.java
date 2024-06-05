package ch3.sort.bubble_sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 버블 소트 프로그램1(G2)

/**
 * 버블소트 수행 도중 변화가 없는 시점의 i 값을 출력
 */
public class p016 {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        ArrayList<Integer> arr = new ArrayList<>();
//        ArrayList<Integer> sorted_arr = new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//            int num = sc.nextInt();
//            arr.add(num);
//            sorted_arr.add(num);
//        }
//
//        sorted_arr.sort((o1, o2) -> o1 - o2);
//
//        int max = 0;
//        for (int i = 0; i < n; i++) {
//            int idx = sorted_arr.indexOf(arr.get(i));
//            if (idx < i) {
//                max = i - idx;
//            }
//        }
//
//        System.out.println(max + 1);
//    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        mData[] a = new mData[n];
        for (int i = 0; i < n; i++) {
            a[i] = new mData(Integer.parseInt(bf.readLine()), i); // (값, 정렬 전 인덱스)
        }
        Arrays.sort(a);

        int max = 0;
        for (int i = 0; i < n; i++) {
            if (max < a[i].index - i) {
                max = a[i].index - i; // 정렬 전 인덱스 - 정렬 후 인덱스
            }
        }

        System.out.println(max + 1);
    }

}

class mData implements Comparable<mData> {
    int value;
    int index;

    public mData(int value, int index) {
        super();
        this.value = value;
        this.index = index;
    }

    @Override
    public int compareTo(mData o) {
        return this.value - o.value;
    }
}