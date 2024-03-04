"""
2024.03.04
유형 : 구현, 큐
풀이 시간 : 13분

- 힌트를 봐버렸다... 원래는 덱 안쓰고 list로만 구현하려고 했음
- 덱에서 앞의 원소를 놔두면서 K번째를 지우는 방법이 안떠올랐는데, 그냥 rotate 하면 되는거였다.
"""
from collections import deque

N, K = map(int, input().split())
answer = []
people = deque([i for i in range(1, N+1)])

while people:
    people.rotate(-(K-1))
    answer.append(people.popleft())

result = ", ".join(list(map(str, answer)))
print(f"<{result}>")