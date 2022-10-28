#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

struct Student {
    int M, d;
    vector<int> A;
};

vector<Student> studVec;
vector<int> cntVec;

void include(Student &stud) {
    for (const int &algo: stud.A) {
        cntVec[algo]++;
    }
}

void exclude(Student &stud) {
    for (const int &algo: stud.A) {
        cntVec[algo]--;
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N, K, D; cin >> N >> K >> D;
    studVec.resize(N);
    for (Student &e: studVec) {
        cin >> e.M >> e.d;
        e.A.resize(e.M);
        for (int i = 0; i < e.M; i++) {
            cin >> e.A[i];
        }
    }

    sort(studVec.begin(), studVec.end(), [](Student &a, Student &b) {return a.d < b.d;});

    cntVec.resize(K + 1);
    int lp = 0;
    int rp = 0;
    int ans = 0;
    while (rp < N) {
        Student &left = studVec[lp];
        Student &right = studVec[rp];
        if (right.d <= left.d + D) {
            include(right);
            rp++;

        } else {
            exclude(left);
            lp++;
        }
        int knownCnt = 0;
        int wellKnownCnt = 0;
        int groupCnt = rp - lp;
        for (int algo = 1; algo <= K; algo++) {
            if (cntVec[algo]) {
                if (cntVec[algo] == groupCnt) {
                    wellKnownCnt++;
                }
                knownCnt++;
            }
        }
        ans = max(ans, (knownCnt - wellKnownCnt) * groupCnt);
    }
    cout << ans;
    return 0;
}