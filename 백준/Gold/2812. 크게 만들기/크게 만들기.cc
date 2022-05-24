#include <iostream>
#include <vector>
#include <queue>
#include <string>
using namespace std;

const int NUM_DIGIT = 10;
vector<queue<int>> vqPos(NUM_DIGIT, queue<int>());
vector<int> num;
vector<int> answer;

int main() {
    int n, k; cin >> n >> k;
    num.reserve(n);
    int digit = 0;
    for (int i = 0; i < n; i++) {
        char c = cin.get();
        if (c == '\n')  c = cin.get();
        digit = c - '0';
        num.push_back(digit);
        vqPos[digit].push(i);
    }
    answer.reserve(n - k);
    int pos = 0, cnt = k;
    bool flag = false;
    while (pos < n) {
        digit = num[pos];
        flag = false;
        for (int i = digit + 1; i < NUM_DIGIT; i++) {
            while (!vqPos[i].empty() && vqPos[i].front() < pos)
                vqPos[i].pop();
            if (!vqPos[i].empty() && vqPos[i].front() <= pos + cnt) {
                flag = true;
                break;
            }
        }
        if (flag) {
            cnt--;
        } else {
            answer.push_back(digit);
        }
        pos++;
    }
    for (int i = 0; i < n - k; i++)
        cout << answer[i];
}