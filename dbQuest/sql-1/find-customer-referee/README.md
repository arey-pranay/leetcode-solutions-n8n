# Find Customer Referee

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Database`  
**Time:** O(N)  
**Space:** O(1)

---

## Solution (java)

```java
-- # Write your MySQL query statement below
select name from Customer
where referee_id IS NULL OR referee_id <> 2
```

---

---
## Quick Revision
Select customer names where their referee is either not assigned or not customer ID 2.
This is a simple SQL query involving filtering rows based on conditions.

## Intuition
The problem asks us to find customers who are not referred by a specific referee (ID 2). This means we need to identify customers whose `referee_id` is either missing (NULL) or is not equal to 2. The "aha moment" is realizing that both conditions need to be satisfied for a customer to be included in the result.

## Algorithm
1.  Select the `name` column from the `Customer` table.
2.  Apply a `WHERE` clause to filter the rows.
3.  The filtering condition should include two parts connected by an `OR` operator:
    *   `referee_id IS NULL`: This checks for customers who have no referee assigned.
    *   `referee_id <> 2`: This checks for customers whose referee is not customer ID 2.
4.  Combine these conditions to retrieve all customers meeting either criterion.

## Concept to Remember
*   SQL `SELECT` statement for retrieving data.
*   SQL `WHERE` clause for filtering records.
*   Handling `NULL` values in SQL queries.
*   Using logical operators (`OR`, `AND`) in `WHERE` clauses.

## Common Mistakes
*   Forgetting to handle `NULL` values: Many might only consider `referee_id <> 2` and miss customers with no referee.
*   Using `AND` instead of `OR`: This would incorrectly filter out customers who are not referred by ID 2 but *are* referred by someone else (e.g., ID 1 or 3).
*   Incorrectly comparing `NULL`: `NULL` cannot be directly compared using `=` or `<>`. The correct syntax is `IS NULL` or `IS NOT NULL`.
*   Syntax errors in SQL: Typos in table names, column names, or keywords.

## Complexity Analysis
*   Time: O(N) - reason: The query needs to scan through all rows in the `Customer` table to apply the filter. N is the number of rows in the `Customer` table.
*   Space: O(1) - reason: The query only stores the names of the customers that satisfy the condition, which is a constant amount of extra space relative to the input size.

## Commented Code
```sql
-- Select the 'name' column from the 'Customer' table.
select name
-- Specify the table to retrieve data from.
from Customer
-- Filter the rows based on specific conditions.
where
    -- Include customers where the 'referee_id' is NULL (meaning they have no referee).
    referee_id IS NULL
    -- Use OR to combine conditions: either the referee_id is NULL OR it's not equal to 2.
    OR
    -- Include customers where the 'referee_id' is not equal to 2.
    referee_id <> 2;
```

## Interview Tips
*   Clearly state your understanding of the problem: "We need to find customers who are not referred by customer ID 2, which includes those with no referee at all."
*   Explain the logic for handling `NULL` values explicitly.
*   Walk through the `WHERE` clause conditions step-by-step, explaining why `OR` is used.
*   If asked about alternative approaches (though unlikely for this simple problem), you could mention filtering in application code after fetching all data, but emphasize the efficiency of doing it in the database.

## Revision Checklist
- [ ] Understand the problem statement: find customers NOT referred by ID 2.
- [ ] Identify the two conditions for exclusion: `referee_id IS NULL` and `referee_id = 2`.
- [ ] Formulate the `WHERE` clause using `OR` to include customers meeting *either* condition for being a valid result.
- [ ] Ensure correct `NULL` handling (`IS NULL`).
- [ ] Verify SQL syntax.

## Similar Problems
*   Select all customers that are not referred by the customer with id = 2
*   Find Customers With Positive Revenue in Each Session
*   Customers Who Bought Products A and B But Not C

## Tags
`SQL` `Database`
