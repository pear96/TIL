import sys
input = sys.stdin.readline

gear = list(map(int, input().split()))

ascending = 0
descending = 0
mixed = 0


for i in range(7):
    if gear[i] + 1 == gear[i+1]:
        ascending += 1
    elif gear[i] - 1 == gear[i+1]:
        descending += 1

if ascending == 7:
    print("ascending")
elif descending == 7:
    print("descending")
else:
    print("mixed")