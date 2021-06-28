#include <bits/stdc++.h>

using namespace std;

int n;
vector <int> col, diag1, diag2, st;
string res = "";

int search(int y)
{
	if (y == n)
		return 0;
	else {
		for (int x = 0; x < n; x++) {
			if (col[x] || diag1[x + y] || diag2[x - y + n - 1])
				continue;
			col[x] = diag1[x + y] = diag2[x - y + n - 1] = 1;
			st.push_back(x + 1);
			search(y + 1);
			if (st.size() == n)
				return 0;
			st.pop_back();
			col[x] = diag1[x + y] = diag2[x - y + n - 1] = 0;
		}
	}
}

int main()
{
	cin >> n;
	col.assign(n, 0);
	diag1.assign(2 * n - 1, 0);
	diag2.assign(2 * n - 1, 0);

	search(0);

	for (int& i : st)
		printf("%d\n", i);
	return 0;
}