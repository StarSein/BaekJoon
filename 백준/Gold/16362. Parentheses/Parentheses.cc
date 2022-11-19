#include <iostream>
#include <stack>
#include <regex>
using namespace std;

string s;


bool isError() {
    int cntOpen = 0;
    int prevOpen = -1;
    int prevClose = -2;
    int prevOpr = -2;
    int prevAlpha = -2;
    for (int i = 0; i < s.size(); i++) {
        const char &c = s[i];
        if (c == '(') {
            if (prevClose == i - 1) {
                return true;
            }
            prevOpen = i;
            cntOpen++;
        } else if (c == ')') {
            if (prevOpen == i - 1 || prevOpr == i - 1 || !cntOpen) {
                return true;
            }
            prevClose = i;
            cntOpen--;
        } else if (isalpha(c)) {
            if (prevAlpha == i - 1 || prevClose == i - 1) {
                return true;
            }
            prevAlpha = i;
        } else {
            if (prevOpen == i - 1 || prevOpr == i - 1) {
                return true;
            }
            prevOpr = i;
        }
    }
    return cntOpen != 0 || prevOpr == s.size() - 1;
}


bool isImproper() {
    // 괄호 안에 연산자 한 개가 아님
    stack<int> st;
    st.push(0);
    for (const char &c : s) {
        if (c == '(') {
            st.push(0);
        } else if (c == ')') {
            if (st.top() != 1) {
                return true;
            }
            st.pop();
        } else if (!isalpha(c)) {
            st.top()++;
        }
    }
    return st.top() != 1;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    getline(cin, s);
    s = regex_replace(s, regex(" "), "");

    if (isError()) {
        cout << "error";
    } else if (isImproper()) {
        cout << "improper";
    } else {
        cout << "proper";
    }
    return 0;
}