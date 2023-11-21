class Row:
    def __init__(self, p, n):
        self.live = True
        self.p = p
        self.n = n

    def __repr__(self):
        return f" 존재 : {self.live} 앞 {self.p}, 뒤 :{self.n}"


def solution(n, k, cmd):
    history = []
    rows = [Row(i-1, i+1) for i in range(n)]
    rows[0].p = None
    rows[n-1].n = None
    now = k

    for command in cmd:
        order = command[0]
        if order == "Z":
            recent = history.pop()
            node = rows[recent]
            node.live = True
            if node.p:
                rows[node.p].n = recent
            if node.n:
                rows[node.n].p = recent
        elif order == "C":
            history.append(now)
            rows[now].live = False
            # 앞 뒤를 찾아와서 서로 이어준다.
            _p, _n = rows[now].p, rows[now].n
            if _p or _p == 0:
                rows[_p].n = _n
            if _n:
                rows[_n].p = _p
                now = _n
            else:
                now = _p
        elif order == "U":
            for _ in range(int(command[2])):
                now = rows[now].p
        elif order == "D":
            for _ in range(int(command[2])):
                now = rows[now].n

    answer = ""
    for row in rows:
        answer += "O" if row.live else "X"

    return answer

print(solution(8, 2, ["D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"]))