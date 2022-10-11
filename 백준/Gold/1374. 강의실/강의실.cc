#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

struct Event {
    int time;
    bool isStart;

    Event() = default;
    Event(int time, bool isStart): time(time), isStart(isStart) {};
};

vector<Event> vec;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N; cin >> N;
    vec.reserve(N);
    for (int i = 0; i < N; i++) {
        int id, s, e; cin >> id >> s >> e;
        vec.emplace_back(s, true);
        vec.emplace_back(e, false);
    }
    sort(vec.begin(), vec.end(), [](const Event &a, const Event &b) {return a.time != b.time ? a.time < b.time : a.isStart < b.isStart;});

    int capacity = 0, usage = 0;
    for (auto &cur : vec) {
        if (cur.isStart) {
            if (usage == capacity) {
                capacity++;
            }
            usage++;
        } else {
            usage--;
        }
    }
    cout << capacity;
    return 0;
}