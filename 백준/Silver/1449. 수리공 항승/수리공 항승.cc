#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

vector<int> vLoc;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N, L; cin >> N >> L;
    vLoc.resize(N);
    for (int i = 0; i < N; i++) {
        cin >> vLoc[i];
    }
    
    sort(vLoc.begin(), vLoc.end());
    int cntTape = 1, prevLoc = vLoc[0];
    for (int i = 1; i < vLoc.size(); i++) {
        if (vLoc[i] > prevLoc + L - 1) {
            cntTape++;
            prevLoc = vLoc[i];
        }
    }
    cout << cntTape;
}