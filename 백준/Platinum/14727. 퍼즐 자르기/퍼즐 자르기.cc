#include <iostream>
#include <stack>
using namespace std;
typedef long long ll;
typedef pair<ll, ll> pll;

stack<pll> st;


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N; cin >> N;
    st.emplace(0, 1);
    
    ll ans = 0;
    for (int i = 0; i < N; i++) {
        int H; cin >> H;
        if (H > st.top().second) {
            st.emplace(i, H);
        } else if (H < st.top().second) {
            int leftBound = 0;
            while (!st.empty() && H <= st.top().second) {
                leftBound = st.top().first;
                ans = max(ans, st.top().second * (i - leftBound));
                st.pop();
            }
            st.emplace(leftBound, H);
        }
    }
    while (!st.empty()) {
        ans = max(ans, st.top().second * (N - st.top().first));
        st.pop();
    }
    cout << ans;
    return 0;
}