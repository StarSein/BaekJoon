#include <iostream>
#include <vector>
using namespace std;

vector<vector<int>> vBingo {
    {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
    {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
    {0, 4, 8}, {2, 4, 6}
};

int countBingo(const char& c, const string& inp) {
    int cnt = 0;
    for (auto& vec : vBingo) {
        bool flag = true;
        for (auto& idx : vec) {
            if (inp[idx] != c) {
                flag = false;
                break;
            }
        }
        if (flag)
            cnt++;
    }
    return cnt;
}

bool isInvalid(const string& inp) {
    int cntO = 0, cntX = 0;
    for (int i = 0; i < inp.size(); i++) {
        if (inp[i] == 'O')      cntO++;
        else if (inp[i] == 'X') cntX++;
    }
    if (cntO > cntX || cntX > cntO + 1)
        return true;
    
    int cntBX = countBingo('X', inp);
    int cntBO = countBingo('O', inp);
    if (!cntBX && !cntBO && cntO + cntX < inp.size())
        return true;
    if (cntBX && cntBO)
        return true;
    if (cntBX >= 3 || cntBO >= 2)
        return true;
    if (cntBX == 2)
        return cntX != 5;
    if (cntBX == 1 && cntX == cntO)
        return true;
    if (cntBO == 1 && cntX != cntO)
        return true;
    return false;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    string inp; cin >> inp;
    while (inp != "end") {
        if (isInvalid(inp))  cout << "invalid" << '\n';
        else                 cout << "valid" << '\n';
        cin >> inp;
    }
}