package ch3.stack_queue;

import java.util.Scanner;
import java.util.Stack;

// 스택으로 오름차순 수열 만들 (S2)

/**
 * 나는 stack의 peek 값과 현재 수열의 값을 비교하여 풀었지만,
 * 모범답안은 현재 수열의 값과 증가하는 자연수를 비교하여 풀었다.
 */
public class p011 {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        int[] a = new int[n];
//        Stack<Integer> stack = new Stack<>();
//        StringBuffer bf = new StringBuffer();
//
//        for (int i = 0; i < n; i++) {
//            a[i] = sc.nextInt();
//        }
//
//        int k = 1;
//        boolean result = true;
//        for (int i = 0; i < n; i++) {
//            if(stack.empty() || stack.peek() < a[i]) {
//                while(k != a[i]) {
//                    stack.push(k++);
//                    bf.append("+\n");
//                } // k == a[i]면 나옴
//                bf.append("+\n");
//                bf.append("-\n");
//                k++;
//            } else if (stack.peek() == a[i]) {
//                stack.pop();
//                bf.append("-\n");
//            } else { // peek > a[i]
//                System.out.println("NO");
//                result = false;
//                break;
//            }
//        }
//
//        if(result) System.out.println(bf.toString());
//    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        Stack<Integer> stack = new Stack<>();
        StringBuffer bf = new StringBuffer();

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        int num = 1;
        boolean result = true;
        for (int i = 0; i < n; i++) {
            int su = a[i];
            if (su >= num) {
                while (su >= num) { // 현재 수열의 값 >= 오름차순 자연수: 값이 같아질 때까지 push()
                    stack.push(num++);
                    bf.append("+\n");
                }
                stack.pop();
                bf.append("-\n");
            } else { // 현재 수열의 값 < 오름차순 자연수: pop() 수행
                int popped = stack.pop();
                if (popped > su) { // peek가 만들어야 하는 수열의 수보다 크면 수열 출력 불가능.
                    System.out.println("NO");
                    result = false;
                    break;
                } else {
                    bf.append("-\n");
                }
            }
        }

        if (result) System.out.println(bf.toString());
    }
}
