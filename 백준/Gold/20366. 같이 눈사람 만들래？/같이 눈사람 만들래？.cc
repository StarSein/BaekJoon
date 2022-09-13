#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

const int INF = 2e9;
vector<int> hVec;


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);


    int N; cin >> N;
    hVec.resize(N);
    for (int i = 0; i < N; i++) {
        cin >> hVec[i];
    }

    sort(hVec.begin(), hVec.end());


    int ans = INF;
    for (int i = 0; i < hVec.size() - 3; i++) {
        for (int j = i + 3; j < hVec.size(); j++) {
            int heightA = hVec[i] + hVec[j];
            int lp = i + 1, rp = j - 1;
            while (lp < rp) {
                int heightB = hVec[lp] + hVec[rp];
                ans = min(ans, abs(heightA - heightB));
                if (heightB > heightA) {
                    rp--;
                } else {
                    lp++;
                }
            }
        }
    }
    cout << ans;
    return 0;
}