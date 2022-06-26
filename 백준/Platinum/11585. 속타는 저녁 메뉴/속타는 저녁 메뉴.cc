#include <iostream>
#include <vector>
using namespace std;

int answer = 0;
vector<char> p, s;
vector<int> table;
void makeTable(vector<char> &p) {
    int j = 0;
    for (int i = 1; i < p.size(); i++) {
        while (j > 0 && p[i] != p[j]) {
            j = table[j-1];
        }
        if (p[i] == p[j]) {
            table[i] = ++j;
        }
    }
}

void kmp(vector<char> &s, vector<char> &p) {
    int j = 0;
    for (int i = 0; i < s.size(); i++) {
        while (j > 0 && s[i] != p[j]) {
            j = table[j-1];
        }
        if (s[i] == p[j]) {
            if (j == p.size() - 1) {
                answer++;
                j = table[j];
            } else {
                ++j;
            }
        }
    }
}

int gcd(int a, int b) {
    int tmp;
    while (b) {
        tmp = a;
        a = b;
        b = tmp % b;
    }
    return a;
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n; cin >> n;
    table.resize(n, 0);
    p.reserve(n);
    s.reserve(2*n-1);
    char c;
    for (int i = 0; i < n; i++) {
        cin >> c;
        p.push_back(c);
    }
    for (int i = 0; i < n; i++) {
        cin >> c;
        s.push_back(c);
    }
    s.insert(s.end(), s.begin(), prev(s.end(), 1));

    makeTable(p);
    kmp(s, p);
    int div = gcd(answer, n);
    cout << answer / div << '/' << n / div;
}