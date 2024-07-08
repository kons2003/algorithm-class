// 상호배타적 집합 구현
package hw;

import java.util.Scanner;

public class hw8_1 {
    public static void main(String[] args) {
        System.out.println("hw8_1:이길수");
        Scanner input = new Scanner(System.in);

        // 사용자가 원하는 갯수의 원소를 갖는 상호배타적 집합 MySet 객체를 생성(생성시 각 원소에 대해 makeSet 연산 수행)
        int n = input.nextInt();
        MySet set = new MySet(n);

        // 사용자가 원하는 횟수의 union 연산을 수행
        int numberOfUnions = input.nextInt();
        for (int i = 0; i < numberOfUnions; i++) {
            int x = input.nextInt();
            int y = input.nextInt();
            set.union(x, y);
        }

        // 트리 구현을 확인하기 위해 모든 원소에 대해 각 원소의 부모를 출력
        set.printParent();

        // 모든 원소에 대해 findSet 연산을 수행
        for (int x = 0; x < n; x++) {
            set.findSet(x);
        }

        // 트리 구현을 확인하기 위해 모든 원소에 대해 각 원소의 부모를 출력
        set.printParent();

        input.close();
    }
}

// 트리를 이용하여 상호배타적 집합을 구현하는 클래스
class MySet {
    private int n; // 원소 수
    private int[] parent; // 각 원소의 부모를 나타내는 배열

    public MySet(int n) { // 원소 수(n)를 매개변수로 받아 집합 객체를 초기화하고, 각 원소에 대해 makeSet 연산을 수행
        this.n = n;
        parent = new int[n]; 
        for (int i = 0; i < n; i++) { // 0 ~ n-1의 원소로 이루어진 집합 만듦
            makeSet(i);
        }
    }

    public void makeSet(int x) { // x 하나로 이루어진 집합을 만듦
        parent[x] = x;
    }

    public int findSet(int x) { // x의 대표 원소를 리턴. 경로압축 사용
        if (parent[x] != x) { // x가 대표 원소(루트 노드)가 아니면,
            parent[x] = findSet(parent[x]); // x의 parent의 대표 원소를 찾아 x의 parent로 삼음
        }
        return parent[x];
    }

    public void union(int x, int y) { //  x가 속한 집합과 y가 속한 집합이 합집합을 생성하되, 연산 결과 x의 대표원소가 최종 대표원소가 되도록 할 것
        parent[findSet(y)] = findSet(x);
    }

    public void printParent() { // 구현을 확인할 수 있도록 모든 원소에 대해 각 노드의 부모를 출력 (대표원소가 아니라 부모원소를 출력)
        for (int i = 0; i < n; i++) {
            System.out.print(parent[i] + " ");
        }
        System.out.println(); // 모든 원소 출력 후 줄바꿈
    }
}
