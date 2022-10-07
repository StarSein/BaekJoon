/*

1. p값을 기준으로 오름차순 정렬
2. 구간 쿼리 점 갱신 세그먼트 합 트리를 이용해 각 인덱스별 왼쪽 튜플의 개수를 구한다
3. 방향만 반대로 같은 작업을 통해 각 인덱스별 가능한 오른쪽 튜플의 개수를 구한다
4. 이때 p값이 같은 것끼리 한 번에 처리하는 것이 중요하다
5. 양쪽 튜플의 개수를 모두 구했다면, 각 인덱스에 대해 두 개수를 곱한 값의 총합이 정답이다

*/
#include <bits/stdc++.h>
using namespace std;
typedef pair<int, int> pi;
typedef long long ll;
#define P first
#define Q second

const int MAX_N = 1e5 + 1, MAX_NUM = 1e6;
pi arr[MAX_N];
int seg[4 * MAX_N], idx[MAX_NUM + 1];
vector<int> vec;
queue<pi> qPI;
queue<int> qI;

ll cntL[MAX_N], cntR[MAX_N];

void update(int node, int start, int end, int target) {
    if (start > target || end < target) {
        return;
    }
    if (start == target && end == target) {
        seg[node] += 1;
        return;
    }
    int mid = (start + end) >> 1;
    update(node << 1, start, mid, target);
    update(node << 1 | 1, mid + 1, end, target);
    seg[node] = seg[node << 1] + seg[node << 1 | 1];
}

int query(int node, int start, int end, int left, int right) {
    if (start > right || end < left) {
        return 0;
    }
    if (left <= start && end <= right) {
        return seg[node];
    }
    int mid = (start + end) >> 1;
    return query(node << 1, start, mid, left, right) + \
            query(node << 1 | 1, mid + 1, end, left, right);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n; cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> arr[i].P;
    }
    for (int i = 0; i < n; i++) {
        cin >> arr[i].Q;
    }
    sort(arr, arr + n, [](pi &a, pi &b) {return a.P < b.P;});

    memset(idx, -1, sizeof(idx));
    vec.resize(n);
    for (int i = 0; i < n; i++) {
        vec[i] = arr[i].Q;
    }
    sort(vec.begin(), vec.end());
    vec.erase(unique(vec.begin(), vec.end()), vec.end());
    for (int i = 0; i < n; i++) {
        idx[vec[i]] = i + 1;
    }

    int prevP = -1;
    int pos = 0;
    for (int i = 0; i <= n; i++) {
        if (i == n || arr[i].P != prevP) {
            while (!qPI.empty()) {
                const auto &e = qPI.front();
                cntL[pos++] = query(1, 1, n, 1, idx[e.Q] - 1);
                qI.push(idx[e.Q]);
                qPI.pop();
            }
            while (!qI.empty()) {
                update(1, 1, n, qI.front());
                qI.pop();
            }
        }
        if (i != n) {
            qPI.push(arr[i]);
            prevP = arr[i].P;
        }
    }

    memset(seg, 0, sizeof(seg));
    prevP = -1;
    pos = n - 1;
    for (int i = n - 1; i >= -1; i--) {
        if (i == -1 || arr[i].P != prevP) {
            while (!qPI.empty()) {
                const auto &e = qPI.front();
                cntR[pos--] = query(1, 1, n, idx[e.Q] + 1, n);
                qI.push(idx[e.Q]);
                qPI.pop();
            }
            while (!qI.empty()) {
                update(1, 1, n, qI.front());
                qI.pop();
            }
        }
        if (i != -1) {
            qPI.push(arr[i]);
            prevP = arr[i].P;
        }
    }
    
    ll ans = 0;
    for (int i = 0; i < n; i++) {
        ans += cntL[i] * cntR[i];
    }
    cout << ans;
    return 0;
}