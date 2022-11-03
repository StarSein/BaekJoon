#include <iostream>
#include <vector>
#include <algorithm>
#include <stack>
using namespace std;

struct Circle {
    int left;
    int right;

    Circle() = default;
    Circle(int l, int r) : left(l), right(r) {};
};

vector<Circle> vec;
stack<Circle> st;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N; cin >> N;
    vec.reserve(N);
    for (int i = 0; i < N; i++) {
        int x, r; cin >> x >> r;
        vec.emplace_back(x - r, x + r);
    }

    sort(vec.begin(), vec.end(), [](Circle &a, Circle &b) {return a.left != b.left ? a.left < b.left : a.right > b.right;});

    int ans = 1 + vec.size();
    for (Circle &e : vec) {
        while (!st.empty() && st.top().right <= e.left) {
            st.pop();
        }
        if (!st.empty()) {
            if (st.top().left == e.left) {
                if (st.top().right == e.right) {
                    ans++;
                } else {
                    st.top().left = e.right;
                }
            }
        }
        st.push(e);
    }
    cout << ans;
    return 0;
}