"""
2024.03.04
유형 : 구현, 시뮬레이션
풀이 시간 : 15분

- 만들라는대로 만들면 된다. 대신 성민이가 중간에 전량 매도를 하면 수익을 얻기 때문에 이를 기록해야한다.
"""
money = int(input())
chart = list(map(int, input().split()))

# 잔액
jh_money, sm_money = money, money
# 주식 개수
jh_cnt, sm_cnt = 0, 0
# 이득
sm_benefit = 0
# 연속 상승, 하락
up = 0
down = 0

for i in range(14):
    # 준현
    if chart[i] <= jh_money:
        buy = jh_money // chart[i]
        jh_money -= chart[i] * buy
        jh_cnt += buy

    # 성민
    if i > 0:
        if chart[i] > chart[i-1]:
            down = 0
            up += 1
            if up == 3:
                # 전량 매도
                sm_benefit += sm_cnt * chart[i]
                sm_cnt = 0
                up = 0
        elif chart[i] < chart[i-1]:
            up = 0
            down += 1
            if down == 3 and chart[i] <= sm_money:
                # 전량 매수
                buy = sm_money // chart[i]
                sm_money -= chart[i] * buy
                sm_cnt += buy
                down = 0
        else:
            up, down = 0, 0

jh_benefit = jh_money + chart[13] * jh_cnt
sm_benefit += sm_money + chart[13] * sm_cnt

if jh_benefit == sm_benefit:
    print("SAMESAME")
elif jh_benefit > sm_benefit:
    print("BNP")
else:
    print("TIMING")
