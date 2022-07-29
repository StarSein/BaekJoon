#include <iostream>
#include <algorithm>
#include <vector>
#include <iterator>
using namespace std;

const int N = 9, TARGET = 100;
vector<int> heights(N);
vector<bool> check(N);

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    for (int i = 0; i < N; i++) {
        cin >> heights[i];
    }
    for (int i = 2; i < N; i++) {
        check[i] = true;
    }

    do {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            if (check[i]) {
                sum += heights[i];
            }
        }
        if (sum == TARGET) {
            vector<int> ans;
            ans.reserve(N - 2);
            for (int i = 0; i < N; i++) {
                if (check[i]) {
                    ans.push_back(heights[i]);
                }
            }
            sort(ans.begin(), ans.end());
            copy(ans.begin(), ans.end(), ostream_iterator<int>(cout, "\n"));
            break;
        }
    } while (next_permutation(check.begin(), check.end()));
    return 0;
}