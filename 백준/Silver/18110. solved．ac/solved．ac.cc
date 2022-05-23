#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <numeric>
using namespace std;


vector<int> opinions;

int main() {
    int n; cin >> n;
    if (n == 0) {
        cout << 0;
        return 0;
    }
    opinions.resize(n, 0);
    for (int i = 0; i < n; i++)
        cin >> opinions[i];
    sort(opinions.begin(), opinions.end());
    int pos = round(opinions.size() * 0.15);
    vector<int>::iterator lit = next(opinions.begin(), pos);
    vector<int>::iterator rit = prev(opinions.end(), pos);
    int avr = round(static_cast<float> (accumulate(lit, rit, 0)) / (opinions.size() - 2 * pos));
    cout << avr;
    return 0;
}