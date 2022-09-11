#include <iostream>
#include <cstring>
using namespace std;

const int MAX_N = 30, SZ = 40000;
int wArr[MAX_N];
int visit[2 * SZ + 1];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    memset(visit, -1, sizeof(visit));

    int n; cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> wArr[i];
    }
    visit[SZ] = 0;
    visit[SZ + wArr[0]] = 0;
    visit[SZ - wArr[0]] = 0;
    for (int i = 1; i < n; i++) {
        for (int j = size(visit) - 1; j >= 0; j--) {
            if (visit[j] != -1 && visit[j] != i) {
                int nj1 = j + wArr[i];
                if (visit[nj1] == -1) {
                    visit[nj1] = i;
                }
                int nj2 = j - wArr[i];
                if (visit[nj2] == -1) {
                    visit[nj2] = i;
                }
            }
        }
    }

    int m; cin >> m;
    for (int i = 0; i < m; i++) {
        int q; cin >> q;
        
        cout << (visit[q + SZ] != -1 ? 'Y' : 'N') << ' ';
    }
    return 0;
}