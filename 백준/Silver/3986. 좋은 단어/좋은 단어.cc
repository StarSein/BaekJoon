#include <iostream>
#include <stack>
using namespace std;


int ans = 0;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N; cin >> N;
    for (int i = 0; i < N; i++) {
        string word; cin >> word;

        stack<char> st;
        for (char &c : word) {
            if (!st.empty() && c == st.top()) {
                st.pop();
            } else {
                st.push(c);
            }
        }

        if (st.empty()) {
            ans++;
        }
    }
    cout << ans;
    return 0;
}