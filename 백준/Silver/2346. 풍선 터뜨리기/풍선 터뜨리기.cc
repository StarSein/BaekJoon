#include <iostream>
#include <deque>
#include <algorithm>
#include <iterator>
#include <vector>
using namespace std;

struct ball {
    int num;
    int val;

    ball() = default;
    ball(int n, int v) : num(n), val(v) {}
};

deque<ball> dq;
vector<int> ans;

void rotateDeque(int val) {
    if (val > 0) {
        val--;
        for (int i = 0; i < val; i++) {
            dq.push_back(dq.front());
            dq.pop_front();
        }
    } else {
        val *= -1;
        for (int i = 0; i < val; i++) {
            dq.push_front(dq.back());
            dq.pop_back();
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int N; cin >> N;
    for (int i = 1; i <= N; i++) {
        int val; cin >> val;
        dq.emplace_back(i, val);
    }

    ans.reserve(N);

    while (!dq.empty()) {
        ball cur = dq.front();
        dq.pop_front();

        ans.push_back(cur.num);
        rotateDeque(cur.val);
    }
    
    copy(ans.begin(), ans.end(), ostream_iterator<int>(cout, " "));
    return 0;
}
