#include <iostream>
#include <vector>
#include <algorithm>
#include <iterator>
using namespace std;

int n;
vector<int> st;

bool dfs(int pos, int prev) {
    for (int sz = 1; sz <= st.size()/2; sz++) {
        bool flag = true;
        for (int i = 1; i <= sz; i++) {
            if (st[pos-i] != st[pos-i-sz]) {
                flag = false;
                break;
            }
        }
        if (flag) {
            return false;
        }
    }
    if (pos == n) {
        copy(st.begin(), st.end(), ostream_iterator<int>(cout, ""));
        return true;
    }
    for (int i = 1; i < 4; i++) {
        if (i != prev) {
            st.push_back(i);
            if (dfs(pos + 1, i)) {
                return true;
            }
            st.pop_back();
        }
    }
    return false;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> n;
    st.reserve(n);
    dfs(0, -1);
}