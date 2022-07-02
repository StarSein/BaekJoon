#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef pair<ll, ll> pll;

int n;
vector<pll> vec;

const int MONTH = 12;
ll dayElse[MONTH] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
ll day2020[MONTH] {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

ll prefE[MONTH];
ll pref2[MONTH];

map<ll, ll> table;

ll strToLL(string date, string time) {
    ll year = stoll(date.substr(0, 4));
    ll month = stoll(date.substr(5, 2));
    ll day = stoll(date.substr(8, 2));

    ll hour = stoll(time.substr(0, 2));
    ll min = stoll(time.substr(3, 2));
    ll sec = stoll(time.substr(6, 2));

    ll ret = sec + 60 * min + 3600 * hour;
    if (year == 2020) {
        ret += 24 * 3600 * day;
        ret += 24 * 3600 * pref2[month-1];
        ret += 24 * 3600 * table[year];
    } else {
        ret += 24 * 3600 * day;
        ret += 24 * 3600 * prefE[month-1];
        ret += 24 * 3600 * table[year];
    }
    return ret;
}




int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    for (int i = 1; i < MONTH; i++) {
        prefE[i] = prefE[i-1] + dayElse[i-1];
        pref2[i] = pref2[i-1] + day2020[i-1];
    }

    table.emplace(2019, 0);
    table.emplace(2020, 365);
    table.emplace(2021, 731);
    table.emplace(2022, 1096);

    cin >> n;
    if (n == 0) {
        cout << 0;
        return 0;
    }

    vec.reserve(n);
    string date, time; int opinion; ll tmp;
    for (int i = 0; i < n; i++) {
        cin >> date >> time >> opinion;
        tmp = strToLL(date, time);
        vec.push_back({tmp, opinion});
    }

    sort(vec.begin(), vec.end());

    const double MOD = 365 * 24 * 3600;
    ll tN = vec[n-1].first;
    double up = 0, down = 0, exp;
    double p;
    for (int i = 1; i <= n; i++) {
        exp = ((double)tN-vec[i-1].first)/MOD;
        p = max(pow(0.5, exp), pow(0.9, n-i));
        up += p * vec[i-1].second;
        down += p;
    }
    cout << round(up/down);
}