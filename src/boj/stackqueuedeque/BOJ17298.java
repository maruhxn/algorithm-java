package boj.stackqueuedeque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ17298 {

    static int[] arr;


//    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int N = Integer.parseInt(br.readLine());
//        arr = new int[N];
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        for (int i = 0; i < N; i++) {
//            arr[i] = Integer.parseInt(st.nextToken());
//        }
//
//        StringBuilder sb = new StringBuilder();
//        Stack<Integer> stack = new Stack<>();
//        for (int i = N - 1; i >= 0; i--) {
//            if (stack.isEmpty()) {
//                stack.push(arr[i]);
//                arr[i] = -1;
//            } else if (arr[i] < stack.peek()) {
//                int temp = stack.peek();
//                stack.push(arr[i]);
//                arr[i] = temp;
//            } else {
//                stack.pop();
//                i++;
//            }
//        }
//
//        for (int i = 0; i < N; i++) {
//            sb.append(arr[i] + " ");
//        }
//
//        System.out.println(sb);
//    }

    // 더 직관적인 방식
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            /**
             * 스택이 비어있지 않으면서, 현재 원소가 스택의 맨 위 원소가 가리키는 원소보다 큰 경우
             * 해당 조건을 만족할 때까지 stack의 원소를 pop 하면서 해당 인덱스의 값을 현재 원소로 교체
             * ex) 4 2 7 이면, 4와 2는 모두 7이 될 것
             * => 가장 먼저 등장한 큰 수로 수정되지 못한 이전 원소들을 모두 채워버림
             */
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                arr[stack.pop()] = arr[i];
            }

            stack.push(i);
        }

        // 큰 수를 찾지 못한 원소들은 -1롤 채우기
        while (!stack.isEmpty()) {
            arr[stack.pop()] = -1;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(arr[i] + " ");
        }
        System.out.println(sb);
    }
}
