edges = []


def add_edges_get_max_edges():
    vertical_max = 0
    horizontal_max = 0
    for _ in range(6):
        direction, length = map(int, input().split())

        edges.append(length)
        if direction < 3:
            vertical_max = max(vertical_max, length)
        else:
            horizontal_max = max(horizontal_max, length)

    return vertical_max, horizontal_max


def get_max_idx(num: int):
    max_idx = edges.index(num)
    edges[max_idx] = 0
    return max_idx


def mark_edges_with_max(idx: int):
    for gap in (-1, 0, 1):
        edges[(idx+gap) % 6] = 0


def get_small_rectangle():
    small_rectangle = 1
    for edge in edges:
        if edge:
            small_rectangle *= edge

    return small_rectangle


def solution():
    cnt_per_m = int(input())
    v_max, h_max = add_edges_get_max_edges()
    v_max_idx = get_max_idx(v_max)
    h_max_idx = get_max_idx(h_max)

    mark_edges_with_max(v_max_idx)
    mark_edges_with_max(h_max_idx)
    total_area = v_max * h_max - get_small_rectangle()
    print(total_area * cnt_per_m)


solution()