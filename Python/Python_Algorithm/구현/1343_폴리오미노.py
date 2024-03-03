"""
2024.03.03
유형 : 구현, 그리디
풀이 시간 : 15분?

- 처음에 '.' 을 기준으로 split을 했다. 근데 '.'이 연속으로 나오면 어떻게 처리할지 고민하다가 방향을 바꿨다.
- idx로 하나씩 보려고 했으나 X가 연속으로 4개가 나오는지 보기전에 무조건 2개를 먼저 보기 때문에, 사전순이라는 조건에 맞지 않는다.
- 다시 split으로 바꾸고 빈 문자열은 '.'으로 치환했다.
- 연속된 X를 하나의 뭉텅이씩 보기 때문에 홀수면 -1이 되고, 짝수라면 4를 넘는지 확인했다.
- 4 이하라면 BB만 붙여준다.
- 폴리오미노로 치환된 경우 마지막 '.'을 떼어줘야 한다.

[매우 쉬운 코드 ... ]
board = input()
board = board.replace("XXXX", "AAAA")
board = board.replace("XX", "BB")
print(board if "X" not in board else "-1")
"""

board = list(input().split("."))
answer = ""
cnt = len(board)

for i in range(cnt):
    if board[i] == "":
        answer += "."
    else:
        length = len(board[i])
        if length % 2 == 1:
            answer = "-1"
            break
        else:
            if length >= 4:
                answer += "AAAA" * (length // 4)
                answer += "BB" if length % 4 else ""
            else:
                answer += "BB"
            answer += "."

print(answer[:len(answer)-1] if answer != "-1" else answer)