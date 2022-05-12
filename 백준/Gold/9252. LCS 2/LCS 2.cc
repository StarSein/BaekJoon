#include <iostream>
#include <vector>
#include <stack>
using namespace std;

string a, b;
vector<vector<int>> dp;
stack<char> stAnswer;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> a >> b;
    dp.resize(a.size()+1, vector<int>(b.size()+1, 0));
    char cur;
    for (size_t i = 1; i <= a.size(); i++) {
        cur = a[i-1];
        for (size_t j = 1; j <= b.size(); j++) {
            if (b[j-1] == cur)  dp[i][j] = dp[i-1][j-1] + 1;
            else                dp[i][j] = max(dp[i-1][j], dp[i][j-1]);
        }
    }
    int i = a.size(), j = b.size();
    while (dp[i][j]) {
        if (dp[i][j] == dp[i-1][j])         
            i--;
        else if (dp[i][j] == dp[i][j-1])    
            j--;
        else {
            stAnswer.push(a[i-1]);
            i--; j--;
        }
    }
    cout << dp[a.size()][b.size()] << '\n';
    while (!stAnswer.empty()) {
        cout << stAnswer.top();
        stAnswer.pop();
    }
}