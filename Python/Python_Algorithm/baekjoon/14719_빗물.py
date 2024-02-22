H, W = map(int, input().split())
height = list(map(int, input().split()))
rain = 0

for idx in range(1, W-1):
    left_max = max(height[:idx])
    right_max = max(height[idx+1:])

    minimal_max = min(left_max, right_max)

    if height[idx] < minimal_max:
        rain += minimal_max - height[idx]

print(rain)