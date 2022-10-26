#include <iostream>
#include <vector>
#include <stack>
using namespace std;
typedef long long ll;
typedef pair<int, int> pi;

vector<int> vec;

ll calcPair(int s, int e) {
    ll ret = 0;
    stack<pi> st;
    st.emplace(vec[s], 1);
    for (int i = s + 1; i <= e; i++) {
        while (!st.empty() && st.top().first < vec[i]) {
            ret += st.top().second;
            st.pop();
        }
        if (!st.empty()) {
            if (st.top().first > vec[i]) {
                ret++;
            } else {
                ret += st.top().second++;
                if (st.size() > 1) {
                    ret++;
                }
                continue;
            }
        }
        st.emplace(vec[i], 1);
    }
    return ret;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int N; cin >> N;
    vec.resize(N);
    for (int i = 0; i < N; i++) {
        cin >> vec[i];
    }
    cout << calcPair(0, N - 1);
    return 0;
}