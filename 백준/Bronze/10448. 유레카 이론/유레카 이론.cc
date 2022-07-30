#include <iostream>
#include <vector>
using namespace std;

const int MAX_K = 1e3;
vector<int> nums;
bool isAble[MAX_K+1];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    for (int num = 1, d = 2; num < 1000; num += d, d++) {
        nums.push_back(num);
    }
    for (int i = 0; i < nums.size(); i++) {
        for (int j = 0; j < nums.size(); j++) {
            for (int k = 0; k < nums.size(); k++) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum <= MAX_K) {
                    isAble[sum] = true;
                }
            }
        }
    }

    int T; cin >> T;
    while (T--) {
        int K; cin >> K;

        cout << isAble[K] << '\n';
    }
    return 0;
}