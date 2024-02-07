def solution(m, musicinfos):
    answer = '(None)'
    longest_time = 0

    cnt = len(musicinfos)
    infos = []
    # 입력값 조정 (시간 분 단위로 변환)
    for i in musicinfos:
        start, end, name, codes = i.split(",")
        s_h, s_m = map(int, start.split(":"))
        e_h, e_m = map(int, end.split(":"))
        infos.append([s_h * 60 + s_m, e_h * 60 + e_m, name, codes])

    # '#'붙은 코드 한글자로 바꾸기
    change_code = {"A#": "Z", "C#": "Y", "D#": "X", "F#": "W", "G#": "V"}
    for old_code, new_code in change_code.items():
        m = m.replace(old_code, new_code)
        for i in range(cnt):
            infos[i][3] = infos[i][3].replace(old_code, new_code)

    listen_time = len(m)

    for start, end, name, code in infos:
        play_time = end - start
        # 재생 시간이 짧아서 일단 얘는 아님
        if play_time < listen_time:
            continue

        # 재생시간에 맞춰 악보를 만들어보자.
        code_len = len(code)
        repeats = play_time // code_len
        codes = code * repeats
        if play_time > repeats * code_len:
            codes += code[:play_time - repeats * code_len]

        # 포함하는가?
        if m in codes and play_time > longest_time:
            answer = name
            longest_time = play_time

    return answer