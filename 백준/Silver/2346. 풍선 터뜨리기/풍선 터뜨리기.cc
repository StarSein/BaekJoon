#include <iostream>
#include <algorithm>
#include <iterator>
using namespace std;

typedef struct {
    int val;
    bool popAvail;
} ball;

const int MAX_N = 1e3;
ball arr[MAX_N + 1];
int ans[MAX_N];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int N; cin >> N;
    for (int i = 1; i <= N; i++) {
        cin >> arr[i].val;
        arr[i].popAvail = true;
    }

    int numBall = N;

    int curIndex = 1, cntRightMove = 0;
    for (int i = 0; i < N; i++) {
        while (cntRightMove) {
            int nextIndex = (curIndex < N ? curIndex + 1 : 1);
            if (arr[nextIndex].popAvail) {
                cntRightMove--;
            }
            curIndex = nextIndex;
        }
        ans[i] = curIndex;
        arr[curIndex].popAvail = false;
        numBall--;
        cntRightMove = arr[curIndex].val;
        if (cntRightMove < 0) {
            cntRightMove += 1;
        }
        if (numBall) {
            cntRightMove %= numBall;
        }
        if (cntRightMove <= 0) {
            cntRightMove += numBall;
        }
    }
    copy(ans, ans + N, ostream_iterator<int>(cout, " "));
    return 0;
}