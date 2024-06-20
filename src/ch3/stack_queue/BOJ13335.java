package ch3.stack_queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 트럭(S1)
public class BOJ13335 {
    static Queue<Truck> truckQ;
    static Queue<Truck> bridgeQ;
    static int ableWeight;
    static int w;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 트럭 수
        w = Integer.parseInt(st.nextToken()); // 다리 길이
        int L = Integer.parseInt(st.nextToken()); // 최대 하중
        st = new StringTokenizer(br.readLine());
        truckQ = new LinkedList<>();
        bridgeQ = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            truckQ.add(new Truck(Integer.valueOf(st.nextToken()), 0));
        }

        int time = 0;
        ableWeight = L;

        while (true) {
            if (truckQ.isEmpty() && bridgeQ.isEmpty()) break;

            bridgeQ.stream().forEach(truck -> {
                truck.remainedTime--;
            });
            if (!bridgeQ.isEmpty() && bridgeQ.peek().remainedTime == 0) {
                offTruck();
            }

            if (!truckQ.isEmpty() && ableWeight >= truckQ.peek().value) {
                onTruck();
            }

            time++;
        }

        System.out.println(time);
    }

    private static void onTruck() {
        Truck truck = truckQ.poll();
        bridgeQ.add(truck);
        truck.remainedTime = w;
        ableWeight -= truck.value;
    }

    private static void offTruck() {
        Truck truck = bridgeQ.poll();
        ableWeight += truck.value;
    }

    static class Truck {
        int value;
        int remainedTime;

        public Truck(int value, int remainedTime) {
            this.value = value;
            this.remainedTime = remainedTime;
        }
    }
}