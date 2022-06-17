#include <iostream>
#include <vector>
#include <tuple>
#include <cmath>
using namespace std;
typedef tuple<int, int, int> ti;
typedef long long ll;

vector<ti> vRoom;

int n;
ll initA;

bool isWin(ll maxH, ll curA) {
    ll curH = maxH;
    int t, enemyA, enemyH, dA, dH;
    ll cntEA, cntMA;
    for (ti& room : vRoom) {
        if (get<0>(room) == 1) {
            tie(t, enemyA, enemyH) = room;
            cntMA = ceil(static_cast<double>(enemyH) / curA);
            cntEA = ceil(static_cast<double>(curH) / enemyA); 
            if (cntMA > cntEA) {
                return false;
            } else {
                curH -= static_cast<ll>(enemyA) * (cntMA - 1);
            }
        } else {
            tie(t, dA, dH) = room;
            curA += dA;
            curH = min(maxH, curH + dH);
        }
    }
    return true;
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> n >> initA;
    vRoom.reserve(n);
    int t, a, h;
    for (int i = 0; i < n; i++) {
        cin >> t >> a >> h;
        vRoom.emplace_back(t, a, h);
    }
    ll mid, left = 1, right = static_cast<ll>(123'456) * 1'000'000 * 1'000'000;
    ll answer;
    while (left <= right) {
        mid = left + (right - left) / 2;
        if (isWin(mid, initA)) {
            right = mid - 1;
            answer = mid;
        }
        else {
            left = mid + 1;
        }
    }
    cout << answer;
}