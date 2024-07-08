// 동전 바꾸기
package hw;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class hw11_1 {
    public static void main(String[] args) {
        System.out.println("hw11_1:이길수");
        Scanner input = new Scanner(System.in);

        // (1) 동전 액면 갯수(n), 
        int n = input.nextInt(); // 동전 액면 갯수 입력
        Integer[] coins = new Integer[n]; // 액수에 따라 필요한 코인 개수 저장 배열, 내림차순 정렬을 위해 Integer로 배열 선언

        // n개의 동전 액면을 입력받음
        for (int i = 0; i < n; i++) { 
            coins[i] = input.nextInt();
        }
        
        // (2) 동전 액면을 정렬
        Arrays.sort(coins, Collections.reverseOrder()); // 동전 액수에 따라 내림차순 정렬

        // (3) 동전 액면이 모두 바로 아래 액면의 배수인지 검사
        StringBuilder sb = new StringBuilder();
        boolean multipleCheck = true; // a, 배수인지 검사 결과 저장

        for (int i = 0; i < n-1; i++) {
            // (a) 검사 결과 배수가 아니면 실패
            if (coins[i] % coins[i+1] != 0) { // 현재 액수보다 낮은 전 액수가 배수가 아닐떄
                multipleCheck = false; // 배수가 아니어서 false 저장
                sb.append("그리디 알고리즘은 최적해를 보장하지 않습니다."); // 문자열 추가
                break; // 더이상 검사할 필요가 없으므로 멈춤
            }
        }

        // (b) 검사 결과 배수이면 거스름돈 액수를 입력받아 최적해를 구함
        if (multipleCheck) { // 배수일 때만
            int money = input.nextInt(); // 거스름돈 입력
            int coinCount; // 액수별 필요 코인 개수 저장할 변수

            for (int i = 0; i < n; i++) {
                // (b-1) 최적해를 구하는 과정에서 거스름돈 액수를 맞출 수 없으면 실패
                if (money != 0 && i == n - 1) { // 남은 거스름돈이 있고, 가진 동전 액명이 가장 작을떄
                    if (money % coins[i] != 0) { // 가장 작은 동전 액면으로 나누어떨어지지 않을떄
                        sb = new StringBuilder("원하는 거스름돈 액수를 맞출 수 없습니다."); // 저장한 문자열이 아닌 새로운 문자열 추가
                // (b-2) 최적해를 구했으면 동전액면 x 갯수 형식으로 출력하되 액면이 높은 동전부터 차례대로 출력할 것
                    } else {
                        coinCount = money / coins[i]; // 필요 코인 개수 저장
                        money %= coins[i]; // 남은 거스름돈 저장
                        sb.append(coins[i] + " x " + coinCount).append("\n"); // 출력 형식에 맞춰 문자열 추가
                    }
                } else if (money / coins[i] > 0){ // 해당 동전의 액면으로 거스름돈을 줄 수 있을떄, 줄 수 없으면 건너뜀
                    coinCount = money / coins[i]; // 필요 코인 개수 저장
                    money %= coins[i]; // 남은 거스름돈 저장
                    sb.append(coins[i] + " x " + coinCount).append("\n"); // 출력 형식에 맞춰 문자열 추가
                }
            }
            sb.deleteCharAt(sb.length()-1); // 마지막 줄바꿈 제거
        }

        System.out.println(sb); // 결과 출력
    }
}
