# Combine Two Tables

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Database`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
-- Write your pSQL query statement below
select p.firstName , p.lastName, a.city, a.state
from Person p 
left join address a
on p.personId = a.personId ;


```

---

---
## Quick Revision
Combine two tables by joining them on a common column. The solution uses an SQL query to perform the join.

## Intuition
The key insight is that we need to match rows between the `Person` and `Address` tables based on their respective `personId`. By using a left join, we can include all rows from the `Person` table, even if there's no matching row in the `Address` table.

## Algorithm
1. Identify the common column between the two tables (`personId`).
2. Use a left join to combine the two tables based on this common column.
3. Select the desired columns from both tables using the `SELECT` statement.

## Concept to Remember
* **Join**: Combining rows from two or more tables based on a common column.
* **Left Join**: Including all rows from one table and matching rows from another table based on the join condition.

## Common Mistakes
* Forgetting to specify the `ON` clause for the join.
* Using an inner join instead of a left join, which would exclude rows with no matching address.
* Not selecting the correct columns from both tables.

## Complexity Analysis
- Time: O(n) - The number of rows in the larger table.
- Space: O(1) - We're only using a constant amount of space to store the selected columns.

## Commented Code
```sql
-- Select the desired columns from both tables
SELECT p.firstName, p.lastName, a.city, a.state

-- From the Person table (left join with Address)
FROM Person p 

-- Left join with Address on personId
LEFT JOIN address a ON p.personId = a.personId;
```

## Interview Tips
* Practice writing SQL queries to perform joins and subqueries.
* Pay attention to the type of join used (inner, left, right) based on the problem requirements.
* Make sure to select only the necessary columns from both tables.

## Revision Checklist
- [ ] Understand the concept of joining two tables.
- [ ] Be able to write a simple SQL query with a left join.
- [ ] Practice solving problems involving joins and subqueries.

## Similar Problems
* LeetCode 143. Reorder List
* LeetCode 177. Nth Highest Salary

## Tags
`SQL`, `Join`, `Left Join`
