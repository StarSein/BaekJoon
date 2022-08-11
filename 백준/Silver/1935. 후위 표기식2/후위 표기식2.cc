#include <iostream>
#include <stack>
#include <iomanip>
using namespace std;

const int MAX_N = 26;
double num[MAX_N];
stack<double> st;

int charToInt(char &c) {
    return c - 'A';
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N; cin >> N;
    string s; cin >> s;
    for (int i = 0; i < N; i++) {
        cin >> num[i];
    }

    for (char &c : s) {
        if (isalpha(c)) {
            st.push(num[charToInt(c)]);
        } else {
            double rightOperand = st.top();
            st.pop();
            double leftOperand = st.top();
            st.pop();
            if (c == '+') {
                st.push(leftOperand + rightOperand);
            } else if (c == '-') {
                st.push(leftOperand - rightOperand);
            } else if (c == '*') {
                st.push(leftOperand * rightOperand);
            } else {
                st.push(leftOperand / rightOperand);
            }
        }
    }
    double ans = st.top();
    cout.precision(2);
    cout << fixed << ans;
    return 0;
}