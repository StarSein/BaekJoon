#include <iostream>
#include <string>
#include <queue>
using namespace std;

string inp;
void input() {
    cin >> inp;
}
bool compute() {
    int cnt_w = 0, cnt_o = 0, cnt_l = 0, cnt_f = 0;
    queue<char> qWord;
    for (char c : inp)
        qWord.push(c);
    while (!qWord.empty()) {
        char c = qWord.front();
        qWord.pop();
        switch (c) {
            case 'w':
                cnt_w += 1;
                if (cnt_o | cnt_l | cnt_f != 0)
                    return false;
                break;

            case 'o':
                cnt_o += 1;
                if (cnt_w == 0)
                    return false;
                break;

            case 'l':
                cnt_l += 1;
                if (cnt_w == 0 || cnt_o == 0 || cnt_w != cnt_o)
                    return false;
                break;

            case 'f':
                cnt_f += 1;
                if (cnt_w == 0 || cnt_o == 0 || cnt_l == 0 || !(cnt_w == cnt_o && cnt_o == cnt_l))
                    return false;
                break;
        }
        if (cnt_w == cnt_o && cnt_o == cnt_l && cnt_l == cnt_f)
            cnt_w = cnt_o = cnt_l = cnt_f = 0;
    }
    if (cnt_w == cnt_o && cnt_o == cnt_l && cnt_l == cnt_f)
        return true;
    else
        return false;
}
void output(bool res) {
    cout << res;
}
int main() {
    input();
    output(compute());
    return 0;
}