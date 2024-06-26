package ch6.greedy;

import java.io.IOException;

// 강의실 배정(G5)
// 실패..
public class BOJ11000 {
    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int n = Integer.parseInt(br.readLine());
//        StringTokenizer st;
//        ArrayList<TimeInfo> arr = new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//            st = new StringTokenizer(br.readLine());
//            long start = Long.parseLong(st.nextToken());
//            long end = Long.parseLong(st.nextToken());
//            arr.add(new TimeInfo(start, end));
//        }
//
//        arr.sort((t1, t2) -> {
//            return (int) (t1.start == t2.start ? t1.end - t2.end : t1.start - t2.start);
//        });
//
//        ArrayList<TimeInfo> classes = new ArrayList<>();
//        classes.add(arr.get(0));
//
//        for (int i = 1; i < n; i++) {
//            TimeInfo ti = arr.get(i);
//            for (TimeInfo prevClass : classes) {
//                if (ti.start >= prevClass.end) {
//                    arr.set(i, ti);
//                    break;
//                } else {
//                    classes.add(ti);
//                    break;
//                }
//            }
//        }
//
//        System.out.println(classes.size());
//
//    }
//
//    static class TimeInfo {
//        long start;
//        long end;
//
//        public TimeInfo(long start, long end) {
//            this.start = start;
//            this.end = end;
//        }
    }
}