#include <bits/stdc++.h>
using namespace std;

const int MAX_N = 1e5;
int arr[MAX_N], sortedArr[MAX_N];
unordered_map<int, int> pos;
queue<int> q;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N; cin >> N;
    for (int i = 0; i < N; i++) {
        cin >> arr[i];
    }

    copy(arr, arr + N, sortedArr);
    sort(sortedArr, sortedArr + N);

    for (int i = 0; i < N; i++) {
        pos[sortedArr[i]] = i;
    }

    int ans = 0;
    for (int i = 0; i < N; i++) {
        q.push(i);
        while (!q.empty()) {
            int curIdx = q.front();
            q.pop();
            int targetIdx = pos[arr[curIdx]];
            if (curIdx != targetIdx) {
                swap(arr[curIdx], arr[targetIdx]);
                ans++;
                q.push(curIdx);
            }
        }
    }
    cout << ans;
    return 0;
}