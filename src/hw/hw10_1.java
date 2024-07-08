// 최소신장트리
package hw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class hw10_1 {

    private static int n; // 정점 수
    private static int[] parent; // 각 노드의 부모 노드 번호
    private static int[] rank; // 각 노드의 랭크

    public static void main(String[] args) {
        System.out.println("hw10_1:이길수");
        Scanner input = new Scanner(System.in);
        // 1. 그래프를 입력받음
        // 1) 정점 수(n), 간선 수(e)를 입력받음
        n = input.nextInt(); // 정점 수
        int e = input.nextInt(); // 간선 수

        // 2) e개의 간선(v1, v2, weight)를 입력받아 간선 목록에 추가
        Edge[] edges = new Edge[e]; // 간선 배열 초기화
        for (int i = 0; i < e; i++) {
            int v1 = input.nextInt(); // 정점 입력
            int v2 = input.nextInt(); // 정점 입력
            int weight = input.nextInt(); // 가중치 입력
            edges[i] = new Edge(v1, v2, weight); // 간선 객체 생성 후 배열에 저장
        }

        // 2. 크루스칼 알고리즘을 이용하여 최소신장트리를 구함
        // 1) 간선 가중치 오름차순으로 간선 목록을 정렬
        Arrays.sort(edges);

        // 2) 최소신장트리 간선 목록을 구함
        parent = new int[n]; // 부모 노드 배열 초기화
        rank = new int[n]; // 랭크 배열 초기화
        for (int i = 0; i < n; i++) {
            makeSet(i); // 각각의 정점에 집합 생성
        }

        List<Edge> mst = new ArrayList<>(); // 최소신장트리를 저장할 리스트 생성
        for (Edge edge : edges) { // 모든 간선 반복
            if (findSet(edge.v1) != findSet(edge.v2)) { // v1, v2 정점에 같은 집합에 있는지 검사
                mst.add(edge); // 같은 집합이 아니라면 간선을 추가한다.
                union(edge.v1, edge.v2); // 두 집합을 합친다.
            }
        }

        // 3. 최소신장트리 간선 목록을 출력
        System.out.print("["); // 맨 앞 출력
        for (int i = 0; i < mst.size(); i++) {
            Edge edge = mst.get(i);
            System.out.printf("(%s,%s,%s)", edge.v1, edge.v2, edge.weight);
            if (i < mst.size() - 1) { // 마지막 간선 목록 출력이 아니면 ", " 출력
                System.out.print(", ");
            }
        }
        System.out.println("]"); // 맨 뒤 출력
    }

    // 하나의 원소 x로 구성된 집합 생성
    static void makeSet(int x) {
        parent[x] = x; // 자기 자신을 부모로 하는 집합
        rank[x] = 0; // 초기 랭크 0
    }

    // x의 대표 원소를 알아냄 - 경로압축 사용
    static int findSet(int x) {
        if (x != parent[x]) { // x가 x의 부모가 아닐때
            parent[x] = findSet(parent[x]);
        }
        return parent[x];
    }

    //  x가 속한 집합과 y가 속한 집합을 합침
    static void union(int x, int y) {
        int setX = findSet(x); // x의 대표 원소
        int setY = findSet(y); // y의 대표 원소

        if (setX != setY) { // 두 대표원소가 다를때
            if (rank[setX] > rank[setY]) { // setX의 랭크가 더 클떄
                parent[setY] = setX;
            } else { // setX의 랭크가 더 작거나 같을때
                parent[setX] = setY;
                if (rank[setX] == rank[setY]) { // 랭크가 서로 같을떄
                    rank[setY]++;
                }
            }
        }
    }
}

// 간선 클래스
class Edge implements Comparable<Edge> {
    int v1, v2, weight; // 정점과 가중치

    public Edge(int v1, int v2, int weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) { // ClassCastException 방지를 위해 간선의 가중치를 기준으로 비교함
        return Integer.compare(this.weight, o.weight);
    }
}