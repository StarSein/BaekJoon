#include <iostream>
#include <vector>
#include <algorithm>
#include <iterator>
#include <random>
using namespace std;

const int MAX_N = 50;
string s;
vector<int> seq, reqSize;
bool check[MAX_N + 1];
bool terminal = false;

void dfs(int pos) {
    if (terminal) {
        return;
    }
    if (pos == s.size()) {
        copy(seq.begin(), seq.end(), ostream_iterator<int>(cout, " "));
        terminal = true;
        return;
    }

    int num1 = s[pos] - '0';
    if (!check[num1]) {
        check[num1] = true;
        seq.push_back(num1);
        dfs(pos + 1);
        check[num1] = false;
        seq.pop_back();
    }
    if (pos < s.size() - 1) {
        int num2 = stoi(s.substr(pos, 2));
        if (num2 <= MAX_N && reqSize[num2] <= s.size() && !check[num2]) {
            check[num2] = true;
            seq.push_back(num2);
            dfs(pos + 2);
            check[num2] = false;
            seq.pop_back();
        }
    }

}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    seq.reserve(MAX_N);
    reqSize.resize(MAX_N + 1);
    for (int i = 1; i <= MAX_N; i++) {
        reqSize[i] = reqSize[i-1] + (i < 10 ? 1 : 2);
    }

    cin >> s;

    dfs(0);

    return 0;
}