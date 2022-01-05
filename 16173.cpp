#include <iostream>
#include <queue>
using namespace std;
#define MAX_N 3
#define COL first
#define ROW second

int n;
int matrix[MAX_N][MAX_N];
const pair<int, int> START_POINT {0, 0};
const int VAL_FINISH_POINT = -1;
void input() {
    cin >> n;
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            cin >> matrix[i][j];
}
string compute() {
    queue<pair<int, int>> qLoc;
    qLoc.push(START_POINT);
    while (!qLoc.empty()) {
        pair<int, int> loc = qLoc.front();
        qLoc.pop();
        if (matrix[loc.COL][loc.ROW] == VAL_FINISH_POINT)
            return "HaruHaru";
        
        int stepSize = matrix[loc.COL][loc.ROW];
        if (stepSize == 0)
            continue;
        
        if (loc.COL + stepSize < n)
            qLoc.push(make_pair(loc.COL + stepSize, loc.ROW));
        if (loc.ROW + stepSize < n)
            qLoc.push(make_pair(loc.COL, loc.ROW + stepSize));
    }
    return "Hing";
}
void output(string sol) {
    cout << sol;
}
int main() {
    input();
    output(compute());
    return 0;
}