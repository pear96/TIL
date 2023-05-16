# 회의실 수, 예약된 회의 수
N, M = map(int, input().split())

reserved = dict()
free = dict()
names = list()

for _ in range(N):
    name = input()
    reserved[name] = list()
    free[name] = list()
    names.append(name)

names.sort()

for _ in range(M):
    data = input().split()
    start, end = int(data[1]), int(data[2])
    reserved[data[0]].append((start, end))

for name in names:
    if reserved[name]:
        reserved[name].sort()
        res = reserved[name]
        l = len(res)

        s, e = 9, 18

        for i in range(l):
            rs, re = res[i]
            if s < rs:
                free[name].append((s, rs))
                s = re
            else:
                s = re
                if i < l-1:
                    e = res[i+1][0]
                    if s < e:
                        free[name].append((s, e))
                    s = e
        if s < 18:
            free[name].append((s, 18))
    else:
        free[name] = [(9, 18)]



# 각 회의실에 대한 정보를 회의실 이름의 오름차순으로 출력한다.
# 첫째 줄에는 Room 회의실이름: 을 출력한다.
# 둘째 줄에는 예약가능 시간을 출력한다.
# 예약 가능한 시간이 없다면, Not available을 출력한다.
# 예약 가능한 시간대의 개수를 n이라고 할 때, n available: 을 출력하고,
# 뒤따른 n개의 줄에 예약 가능한 시간대를  09-18 형태로 출력해야 한다.
# 한 자리 수의 경우 앞에 0을 붙여 두 자리 수로 만들어야 함에 유의하라.
for i in range(N):
    name = names[i]
    print(f"Room {name}:")
    l = len(free[name])
    if l:
        print(f"{l} available:")
        for s, e in free[name]:
            if s == 9:
                print(f"09-{e}")
            else:
                print(f"{s}-{e}")
    else:
        print("Not available")
    if i < N-1:
        print("-----")