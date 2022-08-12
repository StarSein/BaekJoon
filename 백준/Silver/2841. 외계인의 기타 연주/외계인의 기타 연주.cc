#include <iostream>
#include <stack>
using namespace std;
typedef long long ll;

const int NUM_LINE = 6;
stack<int> arrStack[NUM_LINE + 1];
ll ans = 0;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N, P; cin >> N >> P;
    for (int i = 0; i < N; i++) {
        int line, fret; cin >> line >> fret;

        while (!arrStack[line].empty() && fret < arrStack[line].top()) {
            arrStack[line].pop();
            ans++;
        }
        if (arrStack[line].empty() || fret > arrStack[line].top()) {
            arrStack[line].push(fret);
            ans++;
        }
    }
    cout << ans;
    return 0;
}