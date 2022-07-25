#include <bits/stdc++.h>
using namespace std;
typedef tuple<int, int, int> ti;

const int MAX_X = 1e9;
int N, S;
vector<ti> vCar;
vector<bool> check;


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> N >> S;
    vCar.resize(N);
    check.resize(N+1);
    for (int i = 0; i < N; i++) {
        get<0>(vCar[i]) = i + 1;
    }
    for (int i = 0; i < N; i++) {
        cin >> get<1>(vCar[i]);
    }
    for (int i = 0; i < N; i++) {
        cin >> get<2>(vCar[i]);
    }
    sort(vCar.begin(), vCar.end(), [](ti &a, ti &b) {return get<1>(a) < get<1>(b);});
    for (int i = 0; i < vCar.size(); i++) {
        if (get<0>(vCar[i]) == S) {
            int li = i, ri = i;
            int lx = get<1>(vCar[i]), rx = get<1>(vCar[i]);
            int cp, cx, ch;
            while (true) {
                bool flag = true;
                if (li >= 0) {
                    tie(cp, cx, ch) = vCar[li];
                    if (lx <= cx) {
                        flag = false;
                        check[cp] = true;
                        lx = min(lx, max(cx - ch, 0));
                        rx = max(rx, min(cx + ch, MAX_X));
                        li--;
                    }
                }
                if (ri < N) {
                    tie(cp, cx, ch) = vCar[ri];
                    if (cx <= rx) {
                        flag = false;
                        check[cp] = true;
                        lx = min(lx, max(cx - ch, 0));
                        rx = max(rx, min(cx + ch, MAX_X));
                        ri++;
                    }
                }
                if (flag) {
                    break;
                }
            }
            break;
        }
    }
    for (int i = 1; i <= N; i++) {
        if (check[i]) {
            cout << i << ' ';
        }
    }
}