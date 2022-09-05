#include <iostream>
#include <stack>
#include <algorithm>
#include <iterator>
using namespace std;
typedef pair<int, int> pi;

const int MAX_N = 5e5;
stack<pi> st;
int heights[MAX_N], receivers[MAX_N];


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int N; cin >> N;
    for (int i = 0; i < N; i++) {
        cin >> heights[i];
    }

    for (int i = N - 1; i >= 0; i--) {
        while (!st.empty() && st.top().first < heights[i]) {
            receivers[st.top().second] = i + 1;
            st.pop();
        }
        st.emplace(heights[i], i);
    }
    copy(receivers, receivers + N, ostream_iterator<int>(cout, " "));
    return 0;
}