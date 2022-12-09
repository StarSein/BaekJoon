from sys import stdin
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    N, M, Q = map(int, input().split())
    hidden_layer = [list(map(int, input().split())) for _ in range(M)]
    output_layer = list(map(int, input().split()))
    input_layers = [list(map(int, input().split())) for _ in range(Q)]
    return N, M, Q, hidden_layer, output_layer, input_layers


def solution(N: int, M: int, Q: int, hidden_layer: List[List[int]], output_layer: List[int], input_layers: List[List[int]]) -> List[int]:
    total_weights = [0 for _ in range(N)]
    default_output = output_layer[-1]
    for i, info in enumerate(hidden_layer):
        Ci = info[0]
        ind_list = info[1:Ci + 1]
        weight_list = info[Ci + 1:-1]
        Bi = info[-1]
        for j, ind in enumerate(ind_list):
            total_weights[ind - 1] += weight_list[j] * output_layer[i]
        default_output += output_layer[i] * Bi

    answers = []
    for input_layer in input_layers:
        answer = default_output + sum(total_weights[i] * Ai for i, Ai in enumerate(input_layer))
        answers.append(answer)
    return answers


if __name__ == '__main__':
    print(*solution(*read_input()), sep='\n')
