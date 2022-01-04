#include <iostream>
#include <vector>
using namespace std;
#define MAX_N 10

int n, s, r;
vector<int> v_numKayak(MAX_N + 2, 1);
vector<int> v_damaged, v_spare;
int numOfDisabled = 0;
void input() {
    cin >> n >> s >> r;
    int teamDamaged;
    for (int i = 0; i < s; i++) {
        cin >> teamDamaged;
        v_damaged.push_back(teamDamaged);
    }
    int teamSpare;
    for (int i = 0; i < r; i++) {
        cin >> teamSpare;
        v_spare.push_back(teamSpare);
    }
}
void lendKayak(int teamSpare, int teamDamaged) {
    v_numKayak[teamSpare] -= 1;
    v_numKayak[teamDamaged] += 1;
}
void compute() {
    for (int teamDamaged : v_damaged)
        v_numKayak[teamDamaged] -= 1;
    for (int teamSpare : v_spare)
        v_numKayak[teamSpare] += 1;

    int i = 1;
    while (i <= n) {
        if (v_numKayak[i] == 2 && v_numKayak[i-1] == 0)
            lendKayak(i, i-1);
        else if (v_numKayak[i] == 2 && v_numKayak[i+1] == 0)
            lendKayak(i, i+1);
        
        i++;
    }
    for (int numKayak : v_numKayak) {
        if (numKayak == 0)
            numOfDisabled++;
    }
}
void output() {
    cout << numOfDisabled;
}
int main() {
    input();
    compute();
    output();
    return 0;
}
