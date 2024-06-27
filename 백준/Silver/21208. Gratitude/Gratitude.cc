#include <iostream>
#include <unordered_map>
#include <vector>
#include <algorithm>
using namespace std;

int N, K;
unordered_map<string, int> frequencies, lastWrittens;
vector<string> lines;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N >> K;
    cin.ignore();
    int endI = 3 * N;
    for (int i = 0; i < endI; i++) {
        string line;
        getline(cin, line);
        frequencies[line]++;
        lastWrittens[line] = i;
    }

    for (auto& iter : frequencies) {
        string line = iter.first;
        lines.emplace_back(line);
    }

    sort(lines.begin(), lines.end(), [](string& a, string& b) {
        if (frequencies[a] == frequencies[b]) return lastWrittens[a] > lastWrittens[b];
        return frequencies[a] > frequencies[b];
    });

    endI = min(K, (int) lines.size());
    for (int i = 0; i < endI; i++) {
        cout << lines[i] << '\n';
    }

    return 0;
}