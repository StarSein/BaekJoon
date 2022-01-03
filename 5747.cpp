#include <iostream>
using namespace std;
#define MAX_N 100

int n;
int arr_X[MAX_N] = { 0 }, arr_Y[MAX_N] = { 0 };
int sol = 0;

void input() {
    cin >> n;
    int num;
    for (int i = 0; i < n; i++) {
        cin >> num;
        arr_X[i] = num;
    }
    for (int i = 0; i < n; i++) {
        cin >> num;
        arr_Y[i] = num;
    }
}
void compute() {
    int cnt_xOdd = 0, cnt_yEven = 0;
    for (int i = 0; i < n; i++)
        if (arr_X[i] % 2 != 0)
            cnt_xOdd++;
    for (int i = 0; i < n; i++)
        if (arr_Y[i] % 2 == 0)
            cnt_yEven++;
    sol = abs(cnt_xOdd - cnt_yEven);
}
void output() {
    cout << sol << '\n';
}
int main() {
    while (1) {
        input();
        if (n == 0)
            break;
        compute();
        output();
    }
    return 0;
}