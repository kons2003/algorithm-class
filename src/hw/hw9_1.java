// 계단오르기
package hw;

import java.util.Scanner;

public class hw9_1 {

    static int[] stair; // 계단별 점수를 저장할 배열
    static int[] score; // 계단별 규칙에 맞게 더한 최대 점수를 저장할 배열

    public static void main(String[] args) {
        System.out.println("hw9_1:이길수");
        Scanner input = new Scanner(System.in);

        // 계단 수 입력받음
        int n = input.nextInt();

        stair = new int[n + 1]; // 0 ~ n
        score = new int[n + 1]; // 0 ~ n

        // 계단별 점수 입력 받음
        for (int i = 1; i <= n; i++) {
            stair[i] = input.nextInt();
        }

        // score[0] = stair[0]; // int형 배열은 0으로 초기화 되기 때문에 명시 안 함
        score[1] = stair[1]; // 첫번째 계단을 밟는 경우 최대합은 자기 자신임
        if (n >= 2) { // 계단 수가 2개 이상을 경우
            score[2] = stair[1] + stair[2]; // 첫번째 계단 값과 자기 자신의 값 더함
        }

        System.out.println(maxScore(n)); // 최대 점수 출력
        System.out.println(scoreOfStepsTaken(n)); // 최대 점수를 얻기 위해 밟은 계단의 점수 출력
    }

    // 최대 점수 구하기 (Bottom-up)
    static int maxScore(int n) {
        for (int i = 3; i <= n; i++) { // score[1],[2]의 값은 있으므로 3 ~ n까지 반복
            score[i] = Math.max(score[i - 2], score[i - 3] + stair[i - 1]) + stair[i]; // (두 계단씩 올랐을 경우), (연속으로 두 계단을 밟은 경우) 중에서 최대 값을 저장
        }
        return score[n]; // 최대 점수 리턴
    }

    // 최대 점수를 얻기 위해 밟은 계단의 점수 구하기, 구한 최대 점수부터 더하기 이전 값을 구해서 문자열 리턴
    static String scoreOfStepsTaken(int i) {
        StringBuilder sb = new StringBuilder();
        while (i > 0) { // 시작점 0 제외
            sb.insert(0, stair[i] + " "); // 문자열 맨 앞에 밟은 계단의 점수 추가
            if (i >= 2 && score[i] == score[i - 2] + stair[i]) { // 두 계단씩 올랐을 경우
                i -= 2; // 두 계단 오른 것이므로 i - 2
            } else if (i >= 2) { // 한 계단씩 연속으로 오른 경우
                sb.insert(0, stair[i - 1] + " ");
                i -= 3; // 연속으로 계단 오른 것이므로 i - 3
            } else { // i == 1, 시작 지점 0을 제외하기 위함
                i--;
            }
        }
        return sb.toString(); // 문자열로 변환 후 리턴
    }
}
