package algospot.ch14;

public class ModulaPractice {
    public static void main(String[] args) {
        int a = 51235;
        int b = 5416;
        int M = 37;
        // (a+b)%M = ((a%M) + (b%M)) % M
        System.out.println((a + b) % M);
        System.out.println((a % M + b % M) % M);

        // (a-b)%M = ((a%M) - (b%M)) % M
        System.out.println((a - b) % M);
        System.out.println((a % M - b % M) % M);

        // (a*b)%M = ((a%M) * (b%M)) % M
        System.out.println((a * b) % M);
        System.out.println((a % M * b % M) % M);
    }
}
