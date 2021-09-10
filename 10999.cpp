#include <iostream>
#include <cmath>
using namespace std;

long long arr[1000000];
long long segmentTree[4000000];

long long Make_segmentTree(int node, int start, int end) {
    if (start == end) return segmentTree[node] = arr[start];

    int mid = (start + end) / 2;
    long long left_result = Make_segmentTree(node * 2, start, mid);
    long long right_result = Make_segmentTree(node * 2 + 1, mid + 1, end);
    segmentTree[node] = left_result + right_result;
    return segmentTree[node];
}

long long Sum(int node, int start, int end, int left, int right) {
    if (left > end || right < start) return 0;
    if (left <= start && end <= right) return segmentTree[node];

    int mid = (start + end) / 2;
    long long left_result = Sum(node * 2, start, mid, left, right);
    long long right_result = Sum(node * 2 + 1, mid + 1, end, left, right);
    return left_result + right_result;
}

void Update_segmentTree(int node, int start, int end, int index, long long diff) {
    if (index < start || index > end) return;
    
    segmentTree[node] = segmentTree[node] + diff;

    if (start != end) {
        int mid = (start + end) / 2;
        Update_segmentTree(node * 2, start, mid, index, diff);
        Update_segmentTree(node * 2 + 1, mid + 1, end, index, diff);
    }
}

int main() {
    int N, M, K;
    scanf("%d %d %d", &N, &M, &K);

    for (int i = 0; i < N; i++) {
        cin >> arr[i];
    }

    Make_segmentTree(1, 0, N - 1);
    for (int i = 0; i < M + K; i++) {
        int a, b, c;
        long long d;
        cin >> a;
        if (a == 1) {
            cin >> b >> c >> d;
            for (int i = b - 1; i < c; i++) {
                Update_segmentTree(1, 0, N - 1, i, d);
            }
        } else {
            cin >> b >> c;
            cout << Sum(1, 0, N - 1, b - 1, c - 1) << endl;
        }
    }
    return 0;
}