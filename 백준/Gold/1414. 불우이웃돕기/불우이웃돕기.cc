#include <iostream>
#include <vector>
#include <algorithm>
#include <iterator>
#include <tuple>
#include <string>
using namespace std;
typedef tuple<int, int, int> ti;

vector<string> vInp;
vector<ti> vEdge;
vector<int> vRoot;

inline int charToLen(const char& c) {
    return isupper(c) ? c - 'A' + 27 : c - 'a' + 1;
}

int find(int x) {
    if (vRoot[x] == x)
        return x;
    return vRoot[x] = find(vRoot[x]);
}

void merge(int a, int b) {
    if (a > b)
        swap(a, b);
    vRoot[b] = a;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n; cin >> n;
    vInp.reserve(n);
    istream_iterator<string> cin_iter(cin), eos; 
    copy(cin_iter, eos, back_inserter(vInp));
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            if (vInp[i][j] != '0')
                vEdge.emplace_back(charToLen(vInp[i][j]), i, j);
    sort(vEdge.begin(), vEdge.end(), less<ti>());
    vRoot.reserve(n);
    for (int i = 0; i < n; i++)
        vRoot.push_back(i);

    int weight, u, v, rootU, rootV, lenMST = 0, totalLen = 0;
    for (ti& edge : vEdge) {
        tie(weight, u, v) = edge;
        totalLen += weight;
        rootU = find(u);
        rootV = find(v);
        if (rootU != rootV) {
            merge(rootU, rootV);
            lenMST += weight;
        }
    }
    for (int i = 0; i < n; i++) {
        if (find(i) != 0) {
            cout << -1;
            return 0;
        }
    }
    cout << totalLen - lenMST;
    return 0;
}