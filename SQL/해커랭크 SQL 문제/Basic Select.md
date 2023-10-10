# [문자열] 이름의 처음과 끝이 모음인지 판단

### 마지막이 모음으로 끝나는 도시 이름 (중복 X)

```sql
select distinct(city)
from station
where right(city, 1) in ('a', 'e', 'i', 'o', 'u')
```



### 처음과 마지막이 모음으로 끝나는 도시 이름 (중복 X)

```sql
select distinct(city)
from station
where left(city, 1) in ('a', 'e','i','o','u') and right(city, 1) in ('a', 'e','i','o','u')
```

```sql
select DISTINCT CITY FROM STATION WHERE CITY REGEXP '^[aeiou]' AND CITY REGEXP '[aeiou]$'
```



### 모음으로 시작하지 않는 도시 이름 (중복 X) + 끝나지 않는 도시 이름 

```sql
select distinct(city)
from station
where city REGEXP '^[^aeiou]'
-- 또는
SELECT DISTINCT CITY FROM STATION WHERE CITY NOT LIKE "[AEIOU]%";
```

```sql
select distinct(city)
from station
where city REGEXP '[^aeiou]$'
```

시작 끝 둘 중 하나는 모음으로 시작하지 않는 것

```sql
select distinct(city)
from station
where city REGEXP '^[^aeiou]' or city REGEXP '[^aeiou]$'
```



### 75점 이상인 학생의 이름을 뒤에서 3글자, id 오름차순 정렬

```sql
select name
from students
where marks > 75
order by right(name, 3), id
```

