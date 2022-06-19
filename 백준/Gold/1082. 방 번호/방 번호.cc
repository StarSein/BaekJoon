#include <iostream>
#include <vector>
#include <algorithm>
#include <iterator>
using namespace std;

int first, other;
vector<int> vCost, answer;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n; cin >> n;
    vCost.resize(n);
    for (int i = 0; i < n; i++) {
        cin >> vCost[i];
    }
    int m; cin >> m;
    
    first = n-1;
    for (int i = n-2; i > 0; i--) {
        if (vCost[i] < vCost[first]) {
            first = i;
        }
    }
    if (m < vCost[first]) {
        cout << 0;
        return 0;
    }
    other = vCost[0] < vCost[first] ? 0 : first;

    answer.push_back(first);
    m -= vCost[first];
    while (m - vCost[other] >= 0) {
        answer.push_back(other);
        m -= vCost[other];
    } 

    for (int pos = 0; pos < answer.size(); pos++) {
        int curDigit = answer[pos];
        for (int digit = n-1; digit > curDigit; digit--) {
            if (m + vCost[curDigit] >= vCost[digit]) {
                answer[pos] = digit;
                m += vCost[curDigit] - vCost[digit];
                break;
            }
        }
    }
    copy(answer.begin(), answer.end(), ostream_iterator<int>(cout));
}