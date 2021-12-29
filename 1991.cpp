#include <iostream>
#include <vector>
using namespace std;
#define pc pair<char, char>
#define ASCII_OF_A 65

class Node {
public:
    char name;
    Node* left = NULL;
    Node* right = NULL;

    Node(char n) : name(n) {}
};

Node* root = NULL;

int n;
const char ROOT_NAME = 'A';
vector<pc> v_child;
vector<string> v_out {"", "", ""};
enum TRAVERSAL_STYLE {PRE_ORDER, IN_ORDER, POST_ORDER};


void input() {
    cin >> n;
    v_child.assign(n+1, make_pair('.', '.'));
    char parentName, leftChildName, rightChildName;
    for (int i = 0; i < n; i++) {
        cin >> parentName >> leftChildName >> rightChildName;
        v_child[parentName - ASCII_OF_A] = make_pair(leftChildName, rightChildName);
    }
}
void checkChildAndConnect(Node* node) {
    int idx = (node->name) - ASCII_OF_A;
    char leftChildName = v_child[idx].first;
    char rightChildName = v_child[idx].second;
    if (leftChildName != '.') {
        node->left = new Node(leftChildName);
        checkChildAndConnect(node->left);
    }
    if (rightChildName != '.') {
        node->right = new Node(rightChildName);
        checkChildAndConnect(node->right);
    }
}
void makeBST() {
    root = new Node(ROOT_NAME);
    checkChildAndConnect(root);
}
void preOrder(Node* node) {
    if (node == NULL)
        return;
    
    v_out[PRE_ORDER].push_back(node->name);
    preOrder(node->left);
    preOrder(node->right);
}
void inOrder(Node* node) {
    if (node == NULL)
        return;
    
    inOrder(node->left);
    v_out[IN_ORDER].push_back(node->name);
    inOrder(node->right);
}
void postOrder(Node* node) {
    if (node == NULL)
        return;

    postOrder(node->left);
    postOrder(node->right);
    v_out[POST_ORDER].push_back(node->name);
}
void output() {
    for (string out : v_out)
        cout << out << '\n';
}
int main() {
    input();
    makeBST();
    preOrder(root);
    inOrder(root);
    postOrder(root);
    output();
    return 0;
}
