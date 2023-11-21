ENTER = 0
LEAVE = 1
ENTER_MSG = "님이 들어왔습니다."
LEAVE_MSG = "님이 나갔습니다."


def solution(record):
    answer = []
    user_nickname = dict()
    logs = []

    for r in record:
        log = r.split()
        if log[0] == "Enter":
            logs.append((log[1], ENTER))
            user_nickname[log[1]] = log[2]
        elif log[0] == "Leave":
            logs.append((log[1], LEAVE))
        else:
            user_nickname[log[1]] = log[2]

    for u_id, status in logs:
        answer.append(f"{user_nickname[u_id]}{ENTER_MSG if status == ENTER else LEAVE_MSG}")
    return answer