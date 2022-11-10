#include <iostream>
#include <vector>
using namespace std;

string A, B;
vector<int> fails;

void makeFails() {
    fails.resize(A.size());
    int j = 0;
    for (int i = 1; i < A.size(); i++) {
        while (j && A[i] != A[j]) {
            j = fails[j - 1];
        }
        fails[i] = ++j;
    }
}

int kmp() {
    int ret = 0;
    int j = 0;
    for (int i = 0; i < B.size(); i++) {
        while (j && B[i] != A[j]) {
            j = fails[j - 1];
        }
        if (j == A.size() - 1) {
            ret++;
            j = fails[j];
        } else {
            ++j;
        }
    }
    return ret;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> A >> B;
    B += B.substr(0, A.size() - 1);

    makeFails();
    cout << kmp();
    return 0;
}