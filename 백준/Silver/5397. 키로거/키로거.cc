#include <iostream>
#include <list>
#include <algorithm>
#include <iterator>
using namespace std;


list<char> pw;


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int T; cin >> T;
    while (T--) {
        pw.clear();

        string keyLog; cin >> keyLog;

        list<char>::iterator iter = pw.begin();
        for (char &c : keyLog) {
            if (c == '<') {
                if (iter != pw.begin()) {
                    iter--;
                }
            } else if (c == '>') {
                if (iter != pw.end()) {
                    iter++;
                }
            } else if (c == '-') {
                if (iter != pw.begin()) {
                    pw.erase(prev(iter, 1));
                }
            } else {
                pw.insert(iter, c);
            }
        }
        copy(pw.begin(), pw.end(), ostream_iterator<char>(cout, ""));
        cout << '\n';
    }
    return 0;
}