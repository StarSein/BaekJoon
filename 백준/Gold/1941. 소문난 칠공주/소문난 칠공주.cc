#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
using namespace std;
typedef pair<int, int> pi;

const int N = 5, K = 7;
vector<string> grid(N);
vector<pi> vPos;
int answer;

pi find(const pi& x, map<pi, pi>& roots) {
    if (roots[x] == x)
        return x;
    
    return roots[x] = find(roots[x], roots);
}

inline void merge(const pi& a, const pi& b, map<pi, pi>& roots) {
    roots[b] = a;
}

inline bool isAdjacent(const pi& a, const pi& b) {
    return (abs(a.first - b.first) + abs(a.second - b.second) <= 1);
}

bool isProper(vector<pi> vec) {
    int cntY = 0, cntS = 0;
    for (pi& e : vec) {
        if (grid[e.first][e.second] == 'Y') cntY++;
        else                                cntS++;
    }
    if (cntS > cntY) {
        map<pi, pi> roots;
        for (pi& e : vec)
            roots[e] = e;
        
        pi rootA, rootB;
        for (int i = 1; i < K; i++) {
            rootA = find(vec[i], roots);
            for (int j = 0; j < i; j++) {
                rootB = find(vec[j], roots);
                if (rootA != rootB && isAdjacent(vec[i], vec[j])) {
                    merge(rootA, rootB, roots);
                }
            }
        }
        bool flag = true;
        pi root = find(vec[0], roots);
        for (int i = 1; i < K; i++) {
            if (find(vec[i], roots) != root) {
                flag = false;
                break;
            }
        }
        return flag;
    }
    return false;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    for (int i = 0; i < N; i++)
        cin >> grid[i];
    
    vPos.reserve(N * N);
    for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
            vPos.push_back({i, j});
    
    vector<bool> vChoice(N * N - K, false);
    for (int i = 0; i < K; i++)
        vChoice.push_back(true);
    do {
        vector<pi> vChoosen;
        vChoosen.reserve(K);
        for (int i = 0; i < N * N; i++) {
            if (vChoice[i])
                vChoosen.push_back(vPos[i]);
        }
        if (isProper(vChoosen)) {
            answer++;
        }
    } while (next_permutation(vChoice.begin(), vChoice.end()));
    cout << answer;
}