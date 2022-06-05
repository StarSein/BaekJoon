#include <iostream>
#include <vector>
using namespace std;

const int INF = 3'000'000;
vector<int> belt, cntDish;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n, d, k, c; cin >> n >> d >> k >> c;
    belt.resize(n + k - 1, 0);
    cntDish.resize(d + 1, 0);
    for (int i = 0; i < n; i++)
        cin >> belt[i];
    for (int i = 0; i < k - 1; i++)
        belt[i+n] = belt[i];
    
    cntDish[c] = INF;
    int curNum = 1;
    for (int i = 0; i < k; i++) {
        if (cntDish[belt[i]]++ == 0)
            curNum++;
    }
    int answer = curNum;
    for (int i = k; i < belt.size(); i++) {
        if (cntDish[belt[i]]++ == 0)
            curNum++;
        if (--cntDish[belt[i-k]] == 0)
            curNum--;
        answer = max(answer, curNum);
    }
    cout << answer;
}