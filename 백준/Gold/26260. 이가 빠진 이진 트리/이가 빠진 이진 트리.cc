#include <algorithm>
#include <cmath>
#include <iostream>
using namespace std;

const int MAX_N = 1 << 17;
int N, X, idx, height;
int A[MAX_N];
int tree[MAX_N];

void preOrder(int node, int height) {
    if (height == 0) {
        return;
    }

    int half = 1 << (height - 2);
    int leftChild = node - half;
    int rightChild = node + half;

    preOrder(leftChild, height - 1);
    tree[node] = A[idx++];
    preOrder(rightChild, height - 1);
}

void postOrder(int node, int height) {
    if (height == 0) {
        return;
    }

    int half = 1 << (height - 2);
    int leftChild = node - half;
    int rightChild = node + half;

    postOrder(leftChild, height - 1);
    postOrder(rightChild, height - 1);
    cout << tree[node] << ' ';
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    // 입력을 받는다
    cin >> N;
    for (int i = 1; i <= N; i++) {
        cin >> A[i];
    }
    cin >> X;

    // 배열 A의 원소 -1을 X로 대체한다
    for (int i = 1; i <= N; i++) {
        if (A[i] == -1) {
            A[i] = X;
            break;
        }
    }

    // 배열 A를 오름차순으로 정렬한다
    sort(A + 1, A + N + 1);

    // 트리를 전위순회하며 각 노드에 배열 A의 원소 하나씩 순서대로 저장한다
    int root = (N + 1) >> 1;
    height = (int)log2(N + 1);
    idx = 1;
    preOrder(root, height);

    // 트리를 후위순회하며 저장된 수를 출력한다
    postOrder(root, height);
}