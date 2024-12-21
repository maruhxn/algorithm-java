package algospot.ch14;

public class GCD {

    public static int gcdSimple(int p, int q) {
        if (p < q) {
            int tmp = p;
            p = q;
            q = tmp;
        }

        if (q == 0) return p;
        return gcdSimple(p - q, q);
    }

    public static int gcd(int p, int q) {
        if (q == 0) return p;
        return gcd(q, p % q);
    }

    public static void main(String[] args) {
        System.out.println(gcdSimple(1023, 6));
        System.out.println(gcd(1023, 6));
    }
}
