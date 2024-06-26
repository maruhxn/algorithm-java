package ch6.greedy;

import java.util.ArrayList;
import java.util.Scanner;

// 회의실 배정(S1)
// 시작 시간이 빠른 순?
// 종료 시간이 빠른 순? -> ok
// 회의 시간이 짧은 순?
public class p035 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<TimeInfo> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            arr.add(new TimeInfo(start, end));
        }

        arr.sort((t1, t2) -> {
            if (t1.end != t2.end) {
                return t1.end > t2.end ? 1 : -1;
            } else {
                return t1.start > t2.start ? 1 : -1;
            }
        });

        int result = 0;
        TimeInfo prevTi = new TimeInfo(0, 0);
        for (int i = 0; i < n; i++) {
            TimeInfo ti = arr.get(i);
            if (ti.start >= prevTi.end) {
                prevTi = ti;
                result++;
            }
        }
        System.out.println(result);
    }

    static class TimeInfo {
        int start;
        int end;

        public TimeInfo(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}