/*
[1차 채점] WA.
궁수들은 왼쪽 궁수부터 순서대로 화살을 쏘는 게 아니라,
동시에 일제 타격을 한다.

[2차 채점] WA.
궁수가 바뀔 때
사정거리 내에 있는 적들이 포함된 queue를 초기화해주지 않아서
사정거리 바깥의 궁수가 남아있는 적을 제거하는 Logic Error가 발생했다.
*/
#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;
#define ROW first
#define COL second
typedef pair<int, int> pi;

const int MAX_SIZE = 15;
const int NUM_ARCHOR = 3;
int n, m, d;
int init[MAX_SIZE][MAX_SIZE];
int grid[MAX_SIZE][MAX_SIZE];
int archor[NUM_ARCHOR];
int initEnemy, numEnemy;
int answer = 0;
int dy[3] = {0, -1, 0};
int dx[3] = {-1, 0, 1};

inline int getDist(int ya, int xa, int yb, int xb) {
    return abs(ya - yb) + abs(xa - xb);
}

int attackEnemy() {
    int nr, nc;
    vector<pi> killed;
    pi cur;
    for (int i = 0; i < NUM_ARCHOR; i++) {
        queue<pi> q;
        bool visit[n][m];
        fill(&visit[0][0], &visit[0][0] + n * m, false);
        q.push({n-1, archor[i]});
        visit[n-1][archor[i]] = true;
        while (!q.empty()) {
            cur = q.front();
            q.pop();
            if (grid[cur.ROW][cur.COL]) {
                killed.push_back({cur.ROW, cur.COL});
                break;
            }
            
            for (int j = 0; j < 3; j++) {
                nr = cur.ROW + dy[j];
                nc = cur.COL + dx[j];
                if (0 <= nr && nr < n && 0 <= nc && nc < m && getDist(nr, nc, n, archor[i]) <= d && !visit[nr][nc]) {
                    q.push({nr, nc});
                    visit[nr][nc] = true;
                }
            }
        }
    }
    int cntKill = 0;
    for (auto& e : killed) {
        if (grid[e.ROW][e.COL]) {
            grid[e.ROW][e.COL] = 0;
            cntKill++;
            numEnemy--;
        }
    }
    return cntKill;
}

void moveEnemy() {
    numEnemy -= count(grid[n-1], grid[n-1] + m, 1);
    for (int row = n-1; row > 0; row--) {
        copy(grid[row-1], grid[row-1] + m, grid[row]);
    }
    fill(grid[0], grid[0] + m, 0);
}

int getNumKill() {
    int numKill = 0;
    numEnemy = initEnemy;
    while (numEnemy > 0) {
        numKill += attackEnemy();
        moveEnemy();
    }
    return numKill;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> n >> m >> d;
    for (int r = 0; r < n; r++) {
        for (int c = 0; c < m; c++) {
            cin >> init[r][c];
            if (init[r][c]) initEnemy++;
        }
    }
    vector<bool> vec(m-NUM_ARCHOR, false);
    vec.insert(vec.end(), NUM_ARCHOR, true);
    do {
        int j = 0;
        for (int i = 0; i < vec.size(); i++)
            if (vec[i]) archor[j++] = i;
        copy(&init[0][0], &init[0][0] + MAX_SIZE * MAX_SIZE, &grid[0][0]);
        answer = max(answer, getNumKill());
    } while (next_permutation(vec.begin(), vec.end()));
    cout << answer;
}