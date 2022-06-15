#include <iostream>
#include <vector>
#include <algorithm>
#include <iterator>
using namespace std;

string inp;
vector<char> vChar, bestSubstr, curSubstr;
vector<bool> vCheck;


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> inp;
    vChar.reserve(inp.size());
    vCheck.resize(inp.size(), false);
    bestSubstr.reserve(inp.size());
    curSubstr.reserve(inp.size());
    copy(inp.begin(), inp.end(), back_inserter(vChar));
    for (int cnt = 0; cnt < inp.size(); cnt++) {
        bestSubstr.insert(bestSubstr.begin(), {'Z' + 1});
        int addedIdx = -1;
        for (int idx = 0; idx < inp.size(); idx++) {
            if (!vCheck[idx]) {
                curSubstr.clear();
                vCheck[idx] = true;
                for (int i = 0; i < inp.size(); i++) {
                    if (vCheck[i])
                        curSubstr.push_back(vChar[i]);
                }
                vCheck[idx] = false;
                if (curSubstr < bestSubstr) {
                    bestSubstr = curSubstr;
                    addedIdx = idx;
                }
            }
        }
        vCheck[addedIdx] = true;
        copy(bestSubstr.begin(), bestSubstr.end(), ostream_iterator<char>(cout));
        cout << '\n';
    }
}