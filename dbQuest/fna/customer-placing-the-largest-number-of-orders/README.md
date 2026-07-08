# Customer Placing The Largest Number Of Orders

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Database`  
**Time:** O(n log n)  
**Space:** O(n)

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
Find the customer who has placed the largest number of orders. We solve this by grouping the orders by customer and counting them, then sorting in descending order.

## Intuition
The key insight here is that we can use SQL's grouping feature to group the orders by customer, and then count the number of orders each customer has made. By ordering these counts in descending order, we can easily find the customer with the most orders.

## Algorithm

1. Use a GROUP BY clause on the Orders table to group the rows by customer_number.
2. Use the COUNT function to count the number of order_number's for each group (i.e., each customer).
3. Order the groups in descending order based on their counts.
4. Limit the output to only the first row, which corresponds to the customer with the most orders.

## Concept to Remember
* SQL grouping: allows you to perform aggregation operations on grouped data
* COUNT function: returns the number of rows in a group
* ORDER BY clause: sorts rows based on one or more columns

## Common Mistakes
* Forgetting to use GROUP BY when trying to count unique values
* Using COUNT(*) instead of COUNT(order_number) (since we only care about order_number's)
* Not realizing that LIMIT 1 is needed to return just the customer with the most orders

## Complexity Analysis
- Time: O(n log n) / sorting is done in time proportional to the number of customers, which is at most n
- Space: O(n) / we need to store the counts for each group

## Commented Code
```sql
-- Group the orders by customer_number
SELECT 
  -- Select only the customer_number column from the grouped data
  customer_number 
  
FROM 
  Orders
  
GROUP BY 
  customer_number
  
-- Count the number of order_number's for each group
COUNT(order_number) AS order_count
  
ORDER BY 
  -- Order the groups in descending order based on their counts
  order_count DESC
  
-- Limit the output to only the first row, which corresponds to the customer with the most orders
LIMIT 1;
```

## Interview Tips

* Make sure you understand what the question is asking for.
* Don't be afraid to use GROUP BY and ORDER BY clauses - they're powerful tools!
* Practice, practice, practice! The more SQL queries you write, the easier it gets.

## Revision Checklist
- [ ] Understand the problem clearly
- [ ] Use GROUP BY and COUNT correctly
- [ ] Order in descending order
- [ ] Limit output to first row

## Similar Problems
* Top N Queries (LeetCode 1818)
* Longest Continuous Subarray With No Zeroes (LeetCode 850)

## Tags
`SQL` `GROUP BY` `COUNT`
