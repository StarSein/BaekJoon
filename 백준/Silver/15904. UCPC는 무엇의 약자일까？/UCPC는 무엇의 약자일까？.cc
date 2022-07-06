#include <iostream>
using namespace std;


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    string s; getline(cin, s);
    char target[4] {'U', 'C', 'P', 'C'};

    int pos = 0;
    for (auto &c : s) {
        if (c == target[pos]) {
            pos++;
            if (pos == 4) {
                break;
            }
        }
    }
    if (pos == 4) {
        cout << "I love UCPC";
    } else {
        cout << "I hate UCPC";
    }
}