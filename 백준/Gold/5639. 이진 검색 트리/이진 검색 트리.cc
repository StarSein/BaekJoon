#include <iostream>
#include <vector>
#include <algorithm>
#include <iterator>
using namespace std;

const int MAX_N = 1e4, ROOT = 1;
int tree[MAX_N+1][3];
bool isLeftChild[MAX_N+1];
vector<int> stack;
vector<int> ans;

void postOrder(int curNode) {
    if (tree[curNode][1]) {
        postOrder(tree[curNode][1]);
    }
    if (tree[curNode][2]) {
        postOrder(tree[curNode][2]);
    }
    ans.push_back(tree[curNode][0]);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    stack.reserve(MAX_N);

    int key; cin >> key;
    tree[ROOT][0] = key;
    int idx = ROOT;
    stack.push_back(idx);
    while (cin >> key) {
        if (key < tree[stack.back()][0]) {
            tree[stack.back()][1] = ++idx;
        } else {
            while (stack.size() >= 2 && key > tree[stack[stack.size()-2]][0]) {
                stack.pop_back();
            }
            tree[stack.back()][2] = ++idx;
            stack.pop_back();
        }
        stack.push_back(idx);
        tree[stack.back()][0] = key;
    }

    postOrder(ROOT);
    copy(ans.begin(), ans.end(), ostream_iterator<int>(cout, "\n"));
    return 0;
}