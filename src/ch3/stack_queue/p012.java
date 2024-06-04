package ch3.stack_queue;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

// 오큰수 구하기(G4)

/**
 * 투 포인터 비슷하게 풀려했으나 시간 초과..
 * O(n) 시간 내에 풀어야 함.
 */
public class p012 {
//    public static void main(String[] args) throws IOException {
//        // 오큰수: 주어진 수보다 오른쪽에 있으면서, 그 수보다 큰 수 중에서 가장 왼쪽에 있는 수
//        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        int n = Integer.parseInt(bf.readLine());
//        StringTokenizer st = new StringTokenizer(bf.readLine());
//        StringBuffer sb = new StringBuffer();
//
//        int[] num = new int[n];
//        for (int i = 0; i < n; i++) {
//            num[i] = Integer.parseInt(st.nextToken());
//        }
//
//        for (int i = 0; i < n; i++) {
//            int target = num[i];
//            boolean ok = false;
//            for (int j = i + 1; j < n; j++) {
//                if (num[j] > target) {
//                    sb.append(num[j]).append(" ");
//                    ok = true;
//                    break;
//                }
//            }
//            if(!ok) sb.append("-1 ");
//        }
//
//        System.out.println(sb.toString());
//    }

    public static void main(String[] args) throws IOException {
        // 오큰수: 주어진 수보다 오른쪽에 있으면서, 그 수보다 큰 수 중에서 가장 왼쪽에 있는 수
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int[] num = new int[n];
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (int i = 1; i < n; i++) { // 처음에는 항상 스택이 비어있으므로 최초값을 push 해서 초기화
            // 스택이 비어있지 않고, 현재 수열이 스택 top 인덱스가 가리키는 수열보다 클 경우
            while (!stack.isEmpty() && num[stack.peek()] < num[i]) {
                result[stack.pop()] = num[i]; // 정답 배열에 오큰수를 현재 수열로 저장
            }
            stack.push(i); // 신규 데이터 push
        }

        while (!stack.isEmpty()) { // 반복문 다 돌았는데 스택에 값이 남아있다면, 해당 인덱스에 -1을 매칭
            result[stack.pop()] = -1;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < n; i++) {
            bw.write(result[i] + " ");
        }
        bw.flush();
    }
}
