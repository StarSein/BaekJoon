#include <bits/stdc++.h>

using namespace std;

int n, cnt = 0;
vector <bool> col, diag1, diag2;

int search(int y)
{
	if (y == n) {
		cnt++;
	}
	else {
		for (int x = 0; x < n; x++) {
			if (col[x] || diag1[x + y] || diag2[x - y + n - 1])
				continue;
			col[x] = diag1[x + y] = diag2[x - y + n - 1] = true;
			search(y + 1);
			col[x] = diag1[x + y] = diag2[x - y + n - 1] = false;
		}
	}
	return 0;
}


int main()
{
	cin >> n;
	col.assign(n, false);
	diag1.assign(2 * n - 1, false);
	diag2.assign(2 * n - 1, false);
	search(0);
	cout << cnt;
	return 0;
}