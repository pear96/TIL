def time_to_str(minutes):
    h = minutes // 60
    m = minutes % 60
    return f"{h:02}:{m:02}"


def solution(n, t, m, timetable):
    start_time = 9 * 60  # 버스 시간
    people = []
    cnt = len(timetable)

    for _time in timetable:
        hour, minute = map(int, _time.split(":"))
        people.append(hour * 60 + minute)

    # 사람들 도착 시간으로 오름차순 정렬한다.
    people.sort()
    seat = m  # 남은 자리 수
    idx = 0  # 사람 index
    turn = 0

    while idx < cnt and turn < n:
        seat = m  # 버스가 새로 왔다.
        bus_time = start_time + turn * t

        for _ in range(m):
            if idx == cnt:
                break
            if people[idx] <= bus_time:
                seat -= 1
                idx += 1
            else:
                break
        turn += 1

    if seat > 0:
        answer = bus_time
    else:
        # 마지막으로 탄 놈보다 1분 빨리 간다.
        answer = people[idx-1] - 1
        if answer > bus_time:
            answer = bus_time

    return time_to_str(answer)

print(solution(1, 1, 5, ["08:00", "08:01", "08:02", "08:03"]))
print(solution(2, 10, 2, ["09:10", "09:09", "08:00"]))
print(solution(	2, 1, 2, ["09:00", "09:00", "09:00", "09:00"]))
print(solution(1, 1, 5, ["00:01", "00:01", "00:01", "00:01", "00:01"]))
print(solution(1, 1, 1, ["23:59"]))
print(solution(10, 60, 45, ["23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"]))
print(solution(10, 25, 1, [ "09:00", "09:10" ,"09:20" ,"09:30" ,"09:40" ,"09:50",
"10:00", "10:10" ,"10:20" ,"10:30" ,"10:40" ,"10:50" ]))  # 10:29