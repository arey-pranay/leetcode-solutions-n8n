# Customer Placing The Largest Number Of Orders

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Database`  
**Time:** O(N log N)  
**Space:** O(N)

---

## Solution (java)

```java
-- Write your PostgreSQL query statement below
select customer_number from Orders
group by customer_number
order by count(order_number) desc
limit 1;

-- 1 -> 1
-- 2 -> 1
-- 3 -> 2
```

---

---
## Quick Revision
Find the customer who placed the most orders.
Group by customer, count their orders, and select the customer with the highest count.

## Intuition
The core idea is to aggregate the orders by customer and then identify the customer with the maximum count. This is a classic aggregation and ranking problem. We need to count how many orders each customer has placed and then find the customer associated with the largest count.

## Algorithm
1. **Group by Customer:** Use the `GROUP BY` clause on the `customer_number` column to group all orders belonging to the same customer.
2. **Count Orders:** For each group (customer), use the `COUNT(order_number)` aggregate function to determine the total number of orders placed by that customer.
3. **Order by Count:** Use the `ORDER BY` clause with `COUNT(order_number) DESC` to sort the customers in descending order based on their order count. The customer with the most orders will appear first.
4. **Limit to Top 1:** Use the `LIMIT 1` clause to select only the first row from the sorted result set, which corresponds to the customer with the largest number of orders.
5. **Select Customer Number:** Select the `customer_number` from this top row.

## Concept to Remember
*   **SQL Aggregation:** Using functions like `COUNT()`, `SUM()`, `AVG()`, `MAX()`, `MIN()` to perform calculations on groups of rows.
*   **`GROUP BY` Clause:** Essential for partitioning rows into groups based on one or more columns, allowing aggregate functions to operate on each group independently.
*   **`ORDER BY` Clause:** Used to sort the result set of a query based on one or more columns, either in ascending (`ASC`) or descending (`DESC`) order.
*   **`LIMIT` Clause:** Restricts the number of rows returned by a query, useful for fetching top/bottom N records.

## Common Mistakes
*   Forgetting to `GROUP BY` customer number before counting, leading to a total count of all orders instead of per customer.
*   Not ordering the results by the count in descending order, which would return an arbitrary customer if `LIMIT 1` is used without sorting.
*   Using `MAX(COUNT(order_number))` directly without `GROUP BY` and `ORDER BY`, which is syntactically incorrect and conceptually wrong for this problem.
*   Not handling ties: The problem statement implies there's a single customer with the largest number of orders. If there were ties, `LIMIT 1` would arbitrarily pick one. The current query handles this by picking one of the top customers.

## Complexity Analysis
- Time: O(N log N) - The dominant factor is the sorting operation (`ORDER BY`) which typically takes O(N log N) time, where N is the number of orders. The grouping and counting are usually O(N).
- Space: O(N) - In the worst case, if all customers are distinct, the database might need to store intermediate results for each customer during grouping and sorting.

## Commented Code
```sql
-- Select the customer_number
select customer_number
-- From the Orders table
from Orders
-- Group the rows by customer_number to count orders for each customer
group by customer_number
-- Order the grouped results by the count of order_number in descending order
-- This places the customer with the most orders at the top
order by count(order_number) desc
-- Limit the result to only the top row, which is the customer with the most orders
limit 1;
```

## Interview Tips
*   Clearly explain the purpose of each SQL clause (`GROUP BY`, `COUNT`, `ORDER BY`, `LIMIT`).
*   Discuss how you would handle ties if the problem asked for all customers with the maximum number of orders (e.g., using window functions like `RANK()` or `DENSE_RANK()`).
*   Mention the time and space complexity of the query and why.
*   Be prepared to translate this logic into other programming languages if asked, using data structures like HashMaps.

## Revision Checklist
- [ ] Understand the goal: find the customer with the most orders.
- [ ] Know how to group data in SQL (`GROUP BY`).
- [ ] Know how to count within groups (`COUNT`).
- [ ] Know how to sort results (`ORDER BY DESC`).
- [ ] Know how to select the top result (`LIMIT 1`).
- [ ] Understand the complexity of SQL operations.

## Similar Problems
*   1179. Reformat Department Table
*   1084. Sales Analysis III
*   1158. Market Analysis I
*   1148. Article Views I

## Tags
`Database` `SQL`
