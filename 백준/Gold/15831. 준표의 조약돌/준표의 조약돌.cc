#include <iostream>
#include <vector>
#include <algorithm>
#include <iterator>
using namespace std;

int n, b, w;
vector<bool> isWhite;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> n >> b >> w;
    isWhite.resize(n, false);
    cin.get();
    for (int i = 0; i < n; i++)
        isWhite[i] = (cin.get() == 'W') ? true : false;

    int lp = 0, rp = 0;
    int cntW = 0, cntB = 0;
    int answer = 0;
    while (rp < n) {
        if (isWhite[rp])    cntW++;
        else                cntB++;
        rp++;
        while (lp < n && cntB > b) {
            if (isWhite[lp])    cntW--;
            else                cntB--;
            lp++;
        }
        if (cntW >= w)  answer = max(answer, rp - lp);
    }
    cout << answer;
}