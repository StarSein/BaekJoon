#include <iostream>
#include <vector>
using namespace std;

const long long MOD = 998244353L;
int N, preIdx, treeSize;
vector<int> preOrder, postOrder, postIndices;
vector<vector<int>> tree;

long long getAnswer(int startPostIdx, int endPostIdx);


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N;
    preOrder.resize(N);
    for (int i = 0; i < N; i++) {
        cin >> preOrder[i];
    }
    postOrder.resize(N);
    for (int i = 0; i < N; i++) {
        cin >> postOrder[i];
    }

    postIndices.resize(N + 1);
    for (int i = 0; i < N; i++) {
        postIndices[postOrder[i]] = i;
    }

    tree.resize(N + 1);

    const int ROOT = preOrder[0];
    
    int ans = getAnswer(0, postIndices[ROOT]);
    cout << (treeSize == N ? ans : 0);
    return 0;
}

long long getAnswer(int startPostIdx, int endPostIdx) {
    long ret = 1L;

    int curNode = preOrder[preIdx];
    treeSize++;

    if (startPostIdx == endPostIdx) {
        return ret;
    }
    for (int i = 0; i < 2; i++) {
        if (++preIdx < N) {
            int child = preOrder[preIdx];
            int childIdx = postIndices[child];
            if (childIdx < startPostIdx || childIdx > endPostIdx) {
                return 0L;
            }

            tree[curNode].push_back(child);

            ret *= getAnswer(startPostIdx, childIdx);
            startPostIdx = childIdx + 1;
            if (startPostIdx == endPostIdx) {
                break;
            }
        }
    }

    if (tree[curNode].size() == 1) {
        ret *= 2L;
    }

    return ret % MOD;
}