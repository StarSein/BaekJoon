#include <bits/stdc++.h>
using namespace std;
typedef pair<int, int> pi;

const int MAX_T = 100, INF = 1e9;
int maxW[MAX_T + 1];
vector<pi> vCloth;

inline int getTime(const int T, const pi &cloth) {
    return 30 + (cloth.first - T) * cloth.second;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    memset(maxW, -1, sizeof(maxW));

    int n, k; cin >> n >> k;
    for (int i = 0; i < n; i++) {
        int t, w; cin >> t >> w;
        maxW[t] = max(maxW[t], w);
    }

    for (int t = 40; t <= MAX_T; t++) {
        if (maxW[t] != -1) {
            vCloth.emplace_back(t, maxW[t]);
        }
    }

    int ans = INF;

    int maxM = -1;
    int T = vCloth[0].first;
    for (auto &e : vCloth) {
        maxM = max(maxM, getTime(T, e));
    }
    ans = min(ans, maxM);

    if (k >= 2 && n >= 2) {
        int T1 = vCloth[0].first;
        int maxM1 = -1;
        for (int i = 1; i < vCloth.size(); i++) {
            int T2 = vCloth[i].first;
            
            maxM1 = max(maxM1, getTime(T1, vCloth[i-1]));

            for (int x = i; x < vCloth.size(); x++) {
                int m1 = maxM1;
                int maxM2 = getTime(T2, vCloth[x]);
                
                for (int a = i; a < vCloth.size(); a++) {
                    if (getTime(T2, vCloth[a]) > maxM2) {
                        m1 = max(m1, getTime(T1, vCloth[a]));
                    }
                }
                ans = min(ans, m1 + maxM2);
            }
        }
    }

    if (k == 3 && vCloth.size() >= 3) {
        int T1 = vCloth[0].first;
        int maxM1 = -1;
        for (int i = 1; i < vCloth.size() - 1; i++) {
            int T2 = vCloth[i].first;
            
            maxM1 = max(maxM1, getTime(T1, vCloth[i-1]));

            for (int j = i + 1; j < vCloth.size(); j++) {
                int T3 = vCloth[j].first;
                
                for (int x = i; x < vCloth.size() - 1; x++) {
                    int m1 = maxM1;
                    int maxM2 = getTime(T2, vCloth[x]);

                    for (int a = i; a < j; a++) {
                        if (getTime(T2, vCloth[a]) > maxM2) {
                            m1 = max(m1, getTime(T1, vCloth[a]));
                        }
                    }

                    for (int y = j; y < vCloth.size(); y++) {
                        int mm1 = m1;
                        int maxM3 = getTime(T3, vCloth[y]);

                        for (int b = j; b < vCloth.size(); b++) {
                            if (getTime(T3, vCloth[b]) > maxM3 && getTime(T2, vCloth[b]) > maxM2) {
                                mm1 = max(mm1, getTime(T1, vCloth[b]));
                            }
                        }
                        ans = min(ans, mm1 + maxM2 + maxM3);
                    }
                }
            }
        }
    }
    cout << ans;
    return 0;
}