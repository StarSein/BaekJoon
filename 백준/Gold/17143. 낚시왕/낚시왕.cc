#include <iostream>
using namespace std;
typedef pair<int, int> pi;

struct Shark {
    int s, d, z;

    Shark() = default;
    Shark(int s, int d, int z): s(s), d(d), z(z) {};

    Shark* winner(Shark* e) {
        if (z > e->z) {
            return this;
        } else {
            return e;
        }
    }
};

const int SZ = 100;
int R, C, M;
Shark* grid[SZ][SZ][2];

int dy[5] {0, -1, 1, 0, 0};
int dx[5] {0, 0, 0, 1, -1};

int catchShark(int c, int i) {
    int ret = 0;
    for (int r = 0; r < R; r++) {
        if (grid[r][c][i]) {
            ret = grid[r][c][i]->z;
            grid[r][c][i] = nullptr;
            break;
        }
    }
    return ret;
}

pi nextLoc(Shark* shark, int r, int c) {
    int nr = r, nc = c;
    if ((shark->d) <= 2) {
        for (int s = (shark->s) % (2 * R - 2); s; s--) {
            if (shark->d == 1 && nr == 0) {
                shark->d = 2;
            } else if (shark->d == 2 && nr == R - 1) {
                shark->d = 1;
            }
            nr += dy[shark->d];
            nc += dx[shark->d];
        }
    } else {
        for (int s = (shark->s) % (2 * C - 2); s; s--) {
            if (shark->d == 3 && nc == C - 1) {
                shark->d = 4;
            } else if (shark->d == 4 && nc == 0) {
                shark->d = 3;
            }
            nr += dy[shark->d];
            nc += dx[shark->d];
        }
    }
    return make_pair(nr, nc);
}

void moveSharks(int cur, int nex) {
    for (int r = 0; r < R; r++) {
        for (int c = 0; c < C; c++) {
            if (grid[r][c][cur]) {
                Shark *shark = grid[r][c][cur];
                auto [nr, nc] = nextLoc(shark, r, c);
                if (grid[nr][nc][nex]) {
                    grid[nr][nc][nex] = grid[nr][nc][nex]->winner(shark);
                } else {
                    grid[nr][nc][nex] = shark;
                }
                grid[r][c][cur] = nullptr;
            }
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> R >> C >> M;
    for (int i = 0; i < M; i++) {
        int r, c, s, d, z; cin >> r >> c >> s >> d >> z;
        grid[r - 1][c - 1][0] = new Shark(s, d, z);
    }

    int ans = 0;
    for (int c = 0, i = 0; c < C; c++, i = 1 - i) {
        ans += catchShark(c, i);
        moveSharks(i, 1 - i);
    }
    cout << ans;
    return 0;
}