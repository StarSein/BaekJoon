#include <iostream>
#include <vector>
#include <algorithm>
#include <iterator>
#include <numeric>
using namespace std;

vector<int> vHeight, vLeftMax, vRightMax;


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int h, w; cin >> h >> w;
    vHeight.reserve(w);
    vLeftMax.resize(w);
    vRightMax.resize(w);
    istream_iterator<int> cin_iter(cin), eos;
    copy(cin_iter, eos, back_inserter(vHeight));
    for (int i = 0; i < w - 1; i++)
        vLeftMax[i+1] = max(vLeftMax[i], vHeight[i]);
    for (int i = w - 1; i > 0; i--)
        vRightMax[i-1] = max(vRightMax[i], vHeight[i]);

    vector<int> vec(w - 2);
    for (int i = 0; i < w - 2; i++)
        vec[i] = i + 1;
    
    cout << accumulate(vec.begin(), vec.end(), 0, [](int sum, int i) {return sum + max(min(vLeftMax[i], vRightMax[i]) - vHeight[i], 0);});
}