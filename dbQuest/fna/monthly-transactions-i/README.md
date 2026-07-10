# Monthly Transactions I

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Database`  
**Time:** O(N)  
**Space:** O(N)

---

## Solution (java)

```java
# Write your MySQL query statement below
select DATE_FORMAT(trans_date, '%Y-%m') as month, 
country , 
count(*) as trans_count , 
count(if(state="approved",state,null)) as approved_count , 
sum(amount) as trans_total_amount , 
sum(if(state="approved",amount,0))as approved_total_amount
from Transactions
group by month , country
```

---

---
## Quick Revision
This problem requires writing a MySQL query to extract monthly transaction data, including the count of transactions and approved transactions, as well as total and approved amounts. The solution involves using aggregation functions and conditional logic in SQL.

## Intuition
The "aha moment" comes from realizing that we can use the `DATE_FORMAT` function to group transactions by month, while also using conditional logic with `if()` to count only approved transactions.

## Algorithm

1. Use `DATE_FORMAT(trans_date, '%Y-%m')` to extract the month from the transaction date.
2. Group the results by both `month` and `country`.
3. Count all transactions using `count(*)`.
4. Count only approved transactions using `count(if(state="approved",state,null))`.
5. Calculate the total amount of all transactions using `sum(amount)`.
6. Calculate the total amount of only approved transactions using `sum(if(state="approved",amount,0))`.

## Concept to Remember
* Using aggregation functions like `count()` and `sum()` with conditions.
* Conditional logic in SQL using `if()`.
* Date formatting and extraction.

## Common Mistakes

* Forgetting to use the correct date format specifier (`'%Y-%m'`) when grouping by month.
* Not handling `NULL` values properly in conditional logic.
* Failing to use parentheses correctly around conditions in SQL.

## Complexity Analysis
- Time: O(N) - reason: single pass through data
- Space: O(N) - reason: aggregating results

## Commented Code

```sql
-- Extract the month from transaction date and group by month and country
select 
  DATE_FORMAT(trans_date, '%Y-%m') as month, 
  country, 

  -- Count all transactions
  count(*) as trans_count,

  -- Count only approved transactions
  count(if(state="approved",state,null)) as approved_count,

  -- Calculate total amount of all transactions
  sum(amount) as trans_total_amount,

  -- Calculate total amount of only approved transactions
  sum(if(state="approved",amount,0)) as approved_total_amount

from Transactions
group by month, country;
```

## Interview Tips

* Practice writing SQL queries on a variety of problems.
* Be mindful of date formats and how they're used in grouping.
* Don't overcomplicate the query with unnecessary joins or subqueries.

## Revision Checklist
- [ ] Understand MySQL functions for date manipulation.
- [ ] Practice using conditional logic in SQL.
- [ ] Review common pitfalls in SQL queries.

## Similar Problems

* LeetCode: #1817, Monthly Transactions II
* HackerRank: SQL Challenges

## Tags
`Array`, `Hash Map`, `SQL`, `Date Formatting`
