package algospot.ch24;

public class RangeResult {
    private int size;
    private int mostFrequent; // 가장 자주 등장하는 숫자의 출현 횟수
    private int leftNumber, leftFreq; // 왼쪽 끝 숫자와 왼쪽 끝 숫자의 출현 횟수
    private int rightNumber, rightFreq; // 오른쪽 끝 숫자와 오른쪽 끝 숫자의 출현 횟수

    public RangeResult merge(RangeResult a, RangeResult b) {
        RangeResult ret = new RangeResult();

        ret.size = a.size + b.size;

        // 왼쪽 부분 구간이 전부 a.leftNumber인 경우 ex) [1, 1, 1, 1]과 [1, 2, 2, 2]를 합칠 때
        ret.leftNumber = a.leftNumber;
        ret.leftFreq = leftFreq;
        if (a.size == a.leftFreq && a.leftNumber == b.leftNumber) {
            ret.leftFreq += b.leftFreq;
        }

        // 오른쪽 끝 숫자도 비슷하게 계산
        ret.rightNumber = b.rightNumber;
        ret.rightFreq = b.rightFreq;
        if (b.size == b.rightFreq && a.rightNumber == b.rightNumber) {
            ret.rightFreq += a.rightFreq;
        }

        // 기본적으로 가장 많이 출현하는 수의 빈도수는 둘 중 큰 쪽으로
        ret.mostFrequent = Math.max(a.mostFrequent, b.mostFrequent);

        // 왼쪽 구간의 오른쪽 끝 숫자와 오른쪽 구간의 왼쪽 끝 숫자가 합쳐지는 경우
        // 이 두수를 합쳤을 때 mostFrequent보다 커지는지 확인한다
        if (a.rightNumber == b.leftNumber) {
            ret.mostFrequent = Math.max(ret.mostFrequent, a.rightFreq + b.leftFreq);
        }

        return ret;
    }
}
