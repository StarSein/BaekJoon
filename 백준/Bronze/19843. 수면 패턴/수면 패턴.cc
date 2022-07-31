#include <iostream>
#include <map>
using namespace std;

map<string, int> table;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    table.emplace("Mon", 0);
    table.emplace("Tue", 1);
    table.emplace("Wed", 2);
    table.emplace("Thu", 3);
    table.emplace("Fri", 4);
    
    int T, N; cin >> T >> N;
    int curTime = 0;
    for (int i = 0; i < N; i++) {
        string day1, day2; int hour1, hour2;
        cin >> day1 >> hour1 >> day2 >> hour2;

        curTime += (24 * table[day2] + hour2) - (24 * table[day1] + hour1);
    }
    int diff = T - curTime;
    if (diff < 0) {
        cout << 0;
    } else if (diff > 48) {
        cout << -1;
    } else {
        cout << diff;
    }
}