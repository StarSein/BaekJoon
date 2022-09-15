#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>
#include <iterator>
using namespace std;

typedef struct problem {
    int id;
    int num;
    int level;
    int group;
} problem;

struct cmp1a {
    bool operator() (problem &a, problem &b) {
        return (a.level != b.level ? a.level < b.level : a.num < b.num);
    }
};

struct cmp1b {
    bool operator() (problem &a, problem &b) {
        return (a.level != b.level ? a.level > b.level : a.num > b.num);
    }
};

struct cmp2a {
    bool operator() (problem &a, problem &b) {
        return a.num > b.num;
    }
};

struct cmp2b {
    bool operator() (problem &a, problem &b) {
        return a.num < b.num;
    }
};

const int MAX_L = 100, MAX_G = 100, MAX_N = 1e5;
priority_queue<problem, vector<problem>, cmp1a> pq1a[MAX_G + 1];
priority_queue<problem, vector<problem>, cmp1b> pq1b[MAX_G + 1];
priority_queue<problem, vector<problem>, cmp1a> pq2a;
priority_queue<problem, vector<problem>, cmp1b> pq2b;
priority_queue<problem, vector<problem>, cmp2a> pq3a[MAX_L + 1];
priority_queue<problem, vector<problem>, cmp2b> pq3b[MAX_L + 1];

bool solved[2 * MAX_N];
int idArr[MAX_N + 1];
int cnt = 0;
vector<int> ans;


int recommend1(int g, int x) {
    if (x == 1) {
        auto &pq = pq1a[g];
        while (!pq.empty() && solved[pq.top().id]) {
            pq.pop();
        }
        return pq.top().num;
    } else {
        auto &pq = pq1b[g];
        while (!pq.empty() && solved[pq.top().id]) {
            pq.pop();
        }
        return pq.top().num;
    }
}

int recommend2(int x) {
    if (x == 1) {
        while (!pq2a.empty() && solved[pq2a.top().id]) {
            pq2a.pop();
        }
        return pq2a.top().num;
    } else {
        while (!pq2b.empty() && solved[pq2b.top().id]) {
            pq2b.pop();
        }
        return pq2b.top().num;
    }
}

int recommend3(int x, int l) {
    if (x == 1) {
        for (int i = l; i <= MAX_L; i++) {
            auto &pq = pq3a[i];
            while (!pq.empty() && solved[pq.top().id]) {
                pq.pop();
            }
            if (!pq.empty()) {
                return pq.top().num;
            }
        }
    } else {
        for (int i = l - 1; i > 0; i--) {
            auto &pq = pq3b[i];
            while (!pq.empty() && solved[pq.top().id]) {
                pq.pop();
            }
            if (!pq.empty()) {
                return pq.top().num;
            }
        }
    }
    return -1;
}

void add(int p, int l, int g) {
    idArr[p] = cnt;
    problem prob = {cnt, p, l, g};
    pq1a[g].push(prob);
    pq1b[g].push(prob);
    pq2a.push(prob);
    pq2b.push(prob);
    pq3a[l].push(prob);
    pq3b[l].push(prob);

    cnt++;
}

void solve(int p) {
    solved[idArr[p]] = true;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int N; cin >> N;
    for (int i = 0; i < N; i++) {
        int P, L, G; cin >> P >> L >> G;

        add(P, L, G);
    }

    int M; cin >> M;
    for (int i = 0; i < M; i++) {
        string query; cin >> query;
        if (query.front() == 'r') {
            if (query.back() == 'd') {
                int G, x; cin >> G >> x;
                ans.push_back(recommend1(G, x));
            } else if (query.back() == '2') {
                int x; cin >> x;
                ans.push_back(recommend2(x));
            } else {
                int x, L; cin >> x >> L;
                ans.push_back(recommend3(x, L));
            }
        } else if (query.front() == 'a') {
            int P, L, G; cin >> P >> L >> G;
            add(P, L, G);
        } else {
            int P; cin >> P;
            solve(P);
        }
    }
    copy(ans.begin(), ans.end(), ostream_iterator<int>(cout, "\n"));
    return 0;
}