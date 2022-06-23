#include <iostream>
#include <vector>
#include <set>
#include <cmath>
#include <algorithm>
#include <iterator>
using namespace std;

string n;
vector<char> vec;
vector<string> seq;
vector<bool> visit;
set<vector<string>> answers;

void dfs(int pos, string rhs) {
    visit[pos] = true;
    seq.push_back(rhs);
    if (seq.size() == vec.size()) {
        answers.insert(seq);
    } else {
        for (int np = 0; np < vec.size(); np++) {
            if (!visit[np]) {
                if (np >= 1 && visit[np-1]) {
                    dfs(np, rhs + to_string(vec[np]));
                }
                if (np < vec.size() - 1 && visit[np+1]) {
                    dfs(np, to_string(vec[np]) + rhs);
                }
            }
        }
    }
    visit[pos] = false;
    seq.pop_back();
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> n;
    for (char &c : n) {
        vec.push_back(c);    
    }

    seq.reserve(n.size());
    visit.resize(n.size());
    for (int pos = 0; pos < vec.size(); pos++) {
        dfs(pos, to_string(vec[pos]));
    }
    cout << answers.size();
}