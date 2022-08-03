#include <iostream>
#include <iomanip>
#include <queue>
#include <cmath>
using namespace std;

queue<string> ans;

string getNum(int r, int c) {
    int level = max(abs(r), abs(c));
    int ret = (int) pow(2 * level + 1, 2);

    if (r == level) {
        ret -= (level - c);
    } else if (c == -level) {
        ret -= 2 * level + (level - r);
    } else if (r == -level) {
        ret -= 4 * level + (c + level);
    } else {
        ret -= 6 * level + (r + level);
    }

    return to_string(ret);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int r1, c1, r2, c2; cin >> r1 >> c1 >> r2 >> c2;
    int maxLenNum = 0;
    for (int r = r1; r <= r2; r++) {
        for (int c = c1; c <= c2; c++) {
            string num = getNum(r, c);
            ans.push(num);
            if (num.size() > maxLenNum) {
                maxLenNum = num.size();
            }
        }
    }
    int colSize = c2 - c1 + 1;
    while (!ans.empty()) {
        int c = 0;
        while (c++ < colSize) {
            cout << setw(maxLenNum) << ans.front() << ' ';
            ans.pop();
        }
        cout << '\n';
    }
    return 0;
}