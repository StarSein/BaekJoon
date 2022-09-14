#include <iostream>
#include <vector>
#include <algorithm>
#include <iterator>
using namespace std;
typedef pair<int, int> pi;

const int NUM_ROW = 6, NUM_COL = 3, NUM_TC = 4;
vector<pi> matchVec;
int result[NUM_ROW][NUM_COL], current[NUM_ROW][NUM_COL];
bool ans[NUM_TC];
int tc = 0;
bool flag = false;

void dfs(int pos, int res) {
    if (flag) {
        return;
    }
    for (int r = 0; r < NUM_ROW; r++) {
        for (int c = 0; c < NUM_COL; c++) {
            if (result[r][c] < current[r][c]) {
                return;
            }
        }
    }
    if (pos == matchVec.size()) {
        ans[tc] = true;
        flag = true;
        return;
    }

    auto &[a, b] = matchVec[pos];
    current[a][res]++;
    current[b][2-res]++;
    for (int i = 0; i < 3; i++) {
        dfs(pos + 1, i);
    }
    current[a][res]--;
    current[b][2-res]--;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    for (int i = 0; i < NUM_ROW - 1; i++) {
        for (int j = i + 1; j < NUM_ROW; j++) {
            matchVec.emplace_back(i, j);
        }
    }

    for (int i = 0; i < 4; i++) {
        tc = i;
        for (int r = 0; r < NUM_ROW; r++) {
            for (int c = 0; c < NUM_COL; c++) {
                cin >> result[r][c];
            }
        }

        bool avail = true;
        for (int r = 0; r < NUM_ROW; r++) {
            if (result[r][0] + result[r][1] + result[r][2] != 5) {
                avail = false;
                break;
            }
        }
        if (!avail) {
            ans[tc] = false;
            continue;
        }

        flag = false;
        for (int j = 0; j < 3; j++) {
            dfs(0, j);
        }
        
    }
    
    copy(ans, ans + NUM_TC, ostream_iterator<int>(cout, " "));
    return 0;
}