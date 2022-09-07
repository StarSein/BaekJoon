#include <iostream>
#include <unordered_set>
using namespace std;

unordered_set<int> s;


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int N; cin >> N;
    for (int i = 0; i < N; i++) {
        int num; cin >> num;
        s.insert(num);
    }
    int M; cin >> M;
    for (int i = 0; i < M; i++) {
        int num; cin >> num;
        cout << s.count(num) << ' ';
    }
    return 0;
}