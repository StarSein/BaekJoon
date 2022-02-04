import sys
sys.setrecursionlimit(10_005)


class Node:
    def __init__(self, data: int):
        self.data = data
        self.left_child = None
        self.right_child = None


def insert_node(node: Node, data: int):
    if node.data > data:
        if node.left_child is None:
            node.left_child = Node(data)
        else:
            insert_node(node.left_child, data)
    else:
        if node.right_child is None:
            node.right_child = Node(data)
        else:
            insert_node(node.right_child, data)


def post_order(node: Node):
    if node is None:
        return

    post_order(node.left_child)
    post_order(node.right_child)
    print(node.data)


if __name__ == '__main__':
    key = int(input())
    root = Node(key)
    for inp in sys.stdin:
        key = int(inp)
        insert_node(root, key)
    post_order(root)
