#include <iostream>
#include <string>
#include <queue>
#include <map>
using namespace std;
#define COL first
#define ROW second
#define MAX_N 10


int n;
pair<int, int> START_POINT = {1, 1};
const char NOT_PASSED = 46;
const char ONLY_VERT = 124;
const char ONLY_HORIZON = 45;
const char BOTH_DIR = 43;
char matrix[MAX_N + 1][MAX_N + 1];
queue<char> qMovements;

void input() {
    cin >> n;
    string inp_str;
    cin >> inp_str;
    for (char e : inp_str) {
        qMovements.push(e);
    }
}
void compute() {
    for (int col = 1; col < n + 1; col++) {
        for (int row = 1; row < n + 1; row++) {
            matrix[col][row] = NOT_PASSED;
        }
    }

    map<char, map<string, int>> cmdTable {
        {'U', {{"col", -1}, {"row", 0},  {"isVertical", 1}}},
        {'D', {{"col", 1},  {"row", 0},  {"isVertical", 1}}},
        {'L', {{"col", 0},  {"row", -1}, {"isVertical", 0}}},
        {'R', {{"col", 0},  {"row", 1},  {"isVertical", 0}}}
    };
    map<char, char> mVerticalUpdate {
        make_pair(NOT_PASSED,   ONLY_VERT),
        make_pair(ONLY_VERT,    ONLY_VERT),
        make_pair(ONLY_HORIZON, BOTH_DIR),
        make_pair(BOTH_DIR,     BOTH_DIR)
    };
    map<char, char> mHorizontalUpdate {
        make_pair(NOT_PASSED,   ONLY_HORIZON),
        make_pair(ONLY_VERT,    BOTH_DIR),
        make_pair(ONLY_HORIZON, ONLY_HORIZON),
        make_pair(BOTH_DIR,     BOTH_DIR)
    };

    pair<int, int> next, current = START_POINT;
    while (!qMovements.empty()) {
        char move = qMovements.front();
        qMovements.pop();
        next = make_pair(current.COL + cmdTable[move]["col"], current.ROW + cmdTable[move]["row"]);
        if (next.COL < 1 || next.COL > n || next.ROW < 1 || next.ROW > n)
            continue;
        
        bool isVertical = cmdTable[move]["isVertical"];
        if (isVertical) {
            matrix[current.COL][current.ROW] = mVerticalUpdate[matrix[current.COL][current.ROW]];
            matrix[next.COL][next.ROW] = mVerticalUpdate[matrix[next.COL][next.ROW]];
        } else {
            matrix[current.COL][current.ROW] = mHorizontalUpdate[matrix[current.COL][current.ROW]];
            matrix[next.COL][next.ROW] = mHorizontalUpdate[matrix[next.COL][next.ROW]];
        }

        current = next;
    }
}
void output() {
    for (int col = 1; col < n + 1; col++) {
        for (int row = 1; row < n + 1; row++) {
            cout << matrix[col][row];
        }
        cout << '\n';
    }
}
int main() {
    input();
    compute();
    output();
    return 0;
}