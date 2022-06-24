#include <iostream>
#include <cmath>
using namespace std;


const int SIZE = 3;
bool visit[SIZE][SIZE];


int main() {
    int l; cin >> l;

    int curr, prev, cr, cc, pr, pc;
    cin >> prev;
    pr = (prev-1) / SIZE;
    pc = (prev-1) % SIZE;
    visit[pr][pc] = true;

    bool answer = true;

    for (int i = 1; i < l; i++) {
        cin >> curr;

        cr = (curr-1) / SIZE;
        cc = (curr-1) % SIZE;
        pr = (prev-1) / SIZE;
        pc = (prev-1) % SIZE;

        if (visit[cr][cc]) {
            answer = false;
            break;
        }

        if (~(cr + pr) & 1 && ~(cc + pc) & 1 && !visit[(pr+cr)/2][(pc+cc)/2]) {
            answer = false;
            break;
        }

        visit[cr][cc] = true;
        prev = curr;
    }
    cout << (answer ? "YES" : "NO");
}