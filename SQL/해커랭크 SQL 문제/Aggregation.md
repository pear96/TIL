### 누적 수입 최대치와, 최대 누적 수입을 가진 사람의 수

- 누적 수입 = salary * month
- 서브쿼리를 쓰려고 했는데 쓰지 않고도 해결할 수 있다.

```sql
select salary * months as total, count(*)
from employee
group by total
order by total desc
limit 1;
```

