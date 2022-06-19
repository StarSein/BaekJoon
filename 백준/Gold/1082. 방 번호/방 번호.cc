#include <iostream>
#include <vector>
#include <algorithm>
#include <iterator>
using namespace std;

int cheapDigit, minCost;
vector<int> vCost, answer;

void updateBest(int start, int end) {
    minCost = 50;
    for (int digit = start; digit < end; digit++) {
        if (vCost[digit] <= minCost) {
            minCost = vCost[digit];
            cheapDigit = digit;
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n; cin >> n;
    vCost.resize(n);
    for (int i = 0; i < n; i++) {
        cin >> vCost[i];
    }
    int m; cin >> m;
    bool flag = true;
    updateBest(0, n);
    if (cheapDigit == 0) {
        updateBest(1, n);
        flag = false;
    }

    int budget;
    if (flag) {
        answer.resize(m / minCost, cheapDigit);
        budget = m % minCost;
    } else {
        if (m >= minCost)
            answer.push_back(cheapDigit);
        else
            answer.push_back(0);

        budget = m - minCost;
        for (int i = 0; i < budget / vCost[0]; i++) {
            answer.push_back(0);
        }
        budget -= (answer.size() - 1) * vCost[0];
    }
    for (int pos = 0; pos < answer.size(); pos++) {
        int curDigit = answer[pos];
        for (int digit = n-1; digit > curDigit; digit--) {
            if (budget + vCost[curDigit] >= vCost[digit]) {
                answer[pos] = digit;
                budget += vCost[curDigit] - vCost[digit];
                break;
            }
        }
    }
    copy(answer.begin(), answer.end(), ostream_iterator<int>(cout));
}