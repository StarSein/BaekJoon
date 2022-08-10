#include <iostream>
#include <stack>
#include <vector>
#include <algorithm>
#include <iterator>
#include <string>
#include <cstring>
using namespace std;
typedef pair<char, int> pci;

vector<char> ans;
stack<pci> st;
int cntBracket = 0;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    string s; cin >> s;
    ans.reserve(s.size());
    for (char &c : s) {
        if (isalpha(c)) {
            ans.push_back(c);
        } else if (c == '(') {
            cntBracket++;
        } else if (c == ')') {
            cntBracket--;
        } else {
            int curPriority = 0;
            if (c == '*' || c == '/') {
                curPriority = 2 * cntBracket + 1;
            } else {
                curPriority = 2 * cntBracket;
            }
            
            while (!st.empty() && curPriority <= st.top().second) {
                ans.push_back(st.top().first);
                st.pop();
            }
            st.emplace(c, curPriority);
        }
    }
    while (!st.empty()) {
        ans.push_back(st.top().first);
        st.pop();
    }

    copy(ans.begin(), ans.end(), ostream_iterator<char>(cout, ""));
    return 0;
}