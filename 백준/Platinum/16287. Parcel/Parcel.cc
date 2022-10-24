#include <iostream>
#include <cstring>
using namespace std;
typedef pair<int, int> pi;

const int MAX_N = 5000;
const int MAX_W = 4e5;
int a[MAX_N];
pi check[MAX_W];

int w, n;

bool getAns() {
    for (int i = 0; i < n - 1; i++) {
        for (int j = i + 1; j < n; j++) {
            int diff = w - a[i] - a[j];
            if (2 <= diff && diff < MAX_W) {
                const pi & p = check[diff];
                if ((p.first || p.second) && \
                p.first != i && p.first != j && \
                p.second != i && p.second != j) {
                    return true;
                }
            }
        }
    }
    return false;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> w >> n;
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    for (int i = 0; i < n - 1; i++) {
        for (int j = i + 1; j < n; j++) {
            check[a[i] + a[j]] = {i, j};
        }
    }
    cout << (getAns() ? "YES" : "NO");
    return 0;
}