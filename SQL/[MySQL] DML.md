# DML

Data Manipulation Language

## ✔️ SELECT

- ` ORDER BY` : https://www.mysqltutorial.org/mysql-order-by/

  - `ASC` & `DESC`

  - 다중 정렬 = `컬럼1, 컬럼2`

    ```sql
    SELECT * FROM TABLE
    ORDER BY COL1, COL2 DESC;
    ```
  
  - 수식 사용하기
  
    ```sql
    SELECT 
        orderNumber, 
        orderlinenumber, 
        quantityOrdered * priceEach
    FROM
        orderdetails
    ORDER BY 
       quantityOrdered * priceEach DESC;
    
    // 또는
    
    SELECT 
        orderNumber,
        orderLineNumber,
        quantityOrdered * priceEach AS subtotal
    FROM
        orderdetails
    ORDER BY subtotal DESC;
    ```
  
  - `NULL` 이 있는 경우엔 `ASC` 에서 NULL이 제일 위로 와버린다.



## INSERT

## UPDATE

## DELETE