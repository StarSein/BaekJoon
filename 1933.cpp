#include <iostream>
#include <vector>
#include <set>
#include <algorithm>
using namespace std;
#define X first
#define Y second
#define pb push_back
typedef long long ll;

int N;
vector<pair<int, int>> schedule;
vector<int> result;

void Input() {
    cin >> N;
    for (int i = 0; i < N; i++) {
        int left, height, right;
        cin >> left >> height >> right;
        schedule.pb({left, height});
        schedule.pb({right, -height});
    }
}
void SortSchedule() {
    sort(schedule.begin(), schedule.end());
    schedule.pb({1e9+1, 1e9+1});
}
void GetVariation() {
    multiset<int> PBH; // PassingBuildingHeights
    PBH.insert(0);
    int recentX = schedule[0].X;
    int recentY = 0;
    int i = 0;
    while (i < 2*N) {
        while (recentX == schedule[i].X) {
            int h = schedule[i].Y;

            if (h > 0)  PBH.insert(h);
            else        PBH.erase(PBH.find(-h));

            i++;
        }
        int tallest = *prev(PBH.end());
        if (tallest != recentY) {
            result.pb(recentX);
            result.pb(tallest);
            recentY = tallest;
        }
        recentX = schedule[i].X;
    }
}
void PrintResult() {
    for (int& val : result) cout << val << ' ';
}
int main() {
    Input();
    SortSchedule();
    GetVariation();
    PrintResult();
    return 0;
}