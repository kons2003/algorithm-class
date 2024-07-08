// 이진검색트리2
package hw;

import java.util.EmptyStackException;

public class hw6_1 {
    public static void main(String[] args) {
        System.out.println("hw6_1:이길수");

        // (1) 연산에 사용할 키값 배열 초기화
        int[] addList = {13, 6, 2, 10, 1, 5, 7, 11, 4, 3, 8, 9, 3, 6, 10, 13};
        int[] searchList = {12, 3, 6, 10, 13};
        int[] removeList = {12, 3, 6, 10, 13, 2, 1, 5, 4, 7, 8, 9, 11};

        // (2) 정수 키값을 저장할 공백 이진검색트리 tree를 생성
        MyBinarySearchTree tree = new MyBinarySearchTree();

        // (3) addList에 저장된 키값들을 차례대로 트리에 삽입
        for(int i = 0; i < addList.length; i++) {
            tree.add((addList[i]));
        }

        // (4) tree를 중순위 순회하여 키값을 출력
        tree.inorder();

        // (5) searchList에 저장된 키값들을 차례대로 트리에서 검색하여 결과 출력
        for(int i = 0; i < searchList.length; i++) {
            System.out.print(tree.contains(searchList[i]) + " ");
        }
        System.out.println();

        // (6) tree의 키값 합을 구하여 출력
        System.out.println(tree.sum());

        // (7) removeList에 저장된 키값들을 차례대로 트리에서 삭제 **** 선택 과제
        for(int i = 0; i < removeList.length; i++) {
            tree.remove((removeList[i]));
        }

        // (8) tree를 중순위 순회하여 키값을 출력 **** 선택 과제
        tree.inorder();

        // (9) tree의 키값 합을 구하여 출력 **** 선택 과제
        System.out.println(tree.sum());
    }
}

// 정수 키값을 갖는 이진검색트리를 구현하는 클래스
class MyBinarySearchTree {
    private Node root = null;  	// 루트 노드를 가리키는 인스턴스 변수

    // 노드의 구조를 정의하는 클래스
    private class Node {
        int key;
        Node left;
        Node right;
    }

    // 매개변수로 받은 키값을 트리에 삽입 - 구현 세부사항을 알지 못해도 호출할 수 있음
    public void add(int key) {
        root = treeInsert(root, key);
    }

    // 트리를 중순위 순회하는 public 메소드 - 구현 세부사항을 알지 못해도 호출할 수 있음
    public void inorder() {
        System.out.print("( ");
        inorder(root);
        System.out.println(")");
    }

    // t를 루트로 하는 트리를 중순위 순회하여 키값 출력 - 매개변수(루트노드 t)로 인해 구현 세부사항을 알아야 호출할 수 있으므로 private 메소드로 따로 두었음
    private void inorder(Node t) {
        if(t != null) {
            inorder(t.left);
            System.out.print(t.key + " ");
            inorder(t.right);
        }
    }

    // 키 값을 매개변수로 받아 그 키값 존재 여부를 리턴 (재귀적으로 구현하지 않은 경우임)
    public boolean contains(int key) {
        Node t = root; // t를 루트로 선언
        while (t != null) {
            if (key == t.key) { // key 값이 존재함
                return true;
            } else if (key < t.key) { // 루트보다 key 값이 작을떄 왼쪽 자식 검사
                t = t.left;
            } else { // 루트보다 key 값이 클떄 오른쪽 자식 검사
                t = t.right;
            }
        }
        return false; // key 값 존재 안 함
    }


    // 트리의 키값 합을 구하여 리턴
    public int sum() {
        return sum(root);
    }

    // t를 루트로 하는 트리의 키값 합을 구하여 리턴 (재귀 알고리즘)
    private int sum(Node t) {
        if (t == null) { // 루트가 비어있으면 0 리턴
            return 0;
        }
        return t.key + sum(t.left) + sum(t.right);
    }

    // 매개변수로 받은 키값을 트리에서 삭제. 키값이 없으면 삭제하지 않으면 됨 **** 선택 과제
    public void remove(int key) {
        // 공백 트리인 경우 삭제 실패이므로 아무 것도 하지 않음
        if (root == null) {
            return;
        }
        // 공백 트리가 아닌 경우
        if (contains(key)) { // 삭제할 노드 존재
            Node r = root; // 자식 노드 r, 삭제할 노드
            Node p = null; // r의 부모 노드 p
            while (r != null) {
                if (key == r.key) { // key와 r 값 같음
                    break;
                } else if (key < r.key) { // r 루트보다 key 값이 작을떄 왼쪽 자식으로 이동
                    p = r;
                    r = r.left;
                } else { // r 루트보다 key 값이 클떄 오른쪽 자식으로 이동
                    p = r;
                    r = r.right;
                }
            }
            treeDelete(r, p);
        } else { // 삭제할 노드 없음, 삭제 안 함
            return;
        }
    }

    // t를 루트로 하는 트리에 key를 삽입하고 삽입 후 루트 노드를 리턴 - 매개변수(루트노드 t)로 인해 구현 세부사항을 알아야 호출할 수 있으므로 private 메소드로 따로 두었음
    private Node treeInsert(Node t, int key) {
        if(t == null) {
            Node r = new Node();
            r.key = key;
            return r;
        }
        else if(key < t.key) {
            t.left = treeInsert(t.left, key);
            return t;
        }
        else if(key > t.key) {
            t.right = treeInsert(t.right, key);
            return t;
        }
        else {
            return t;  // 이미 존재하는 키값인 경우
        }
    }

    // 부모 노드가 p인 노드 r을 트리에서 삭제 **** 선택 과제
    private void treeDelete(Node r, Node p) {
        if (r == root) { // r이 루트 노드인 경우
            root = deleteNode(root);
        } else if (r == p.left) { // r이 루트 노드가 아닌 경우
            p.left = deleteNode(r); // r이 p의 왼쪽 자식
        } else { // r이 루트 노드가 아닌 경우
            p.right = deleteNode(r); // r이 p의 오른쪽 자식
        }
    }

    // 노드 r을 삭제하고 r을 대신할 노드를 리턴 **** 선택 과제
    private Node deleteNode(Node r) {
        if (r.left == null && r.right == null) { // Case 1
            return null;
        } else if (r.left == null && r.right != null) { // Case 2-1
            return r.right;
        } else if (r.left != null && r.right == null) { // Case 2-2
            return r.left;
        } else { // Case 3
            Node s = r.right; // 삭제한 오른쪽 자식을 s로 선언
            Node parent = null; // 부모 노드
            while (s.left != null) { // 왼쪽 자식이 없을 떄까지
                parent = s;
                s = s.left;
            }
            r.key = s.key;
            if (s == r.right) {
                r.right = s.right;
            } else {
                parent.left = s.right;
            }
            return r;
        }
    }
}