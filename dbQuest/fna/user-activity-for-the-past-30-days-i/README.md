# User Activity For The Past 30 Days I

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Database`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
# Write your MySQL query statement below
Select activity_date as day , COUNT(DISTINCT user_id) as active_users 
from Activity
Where activity_date BETWEEN '2019-06-28' AND '2019-07-27'
Group by activity_date 

```

---

---
## Quick Revision
This problem requires writing a MySQL query to retrieve the number of active users for each day in the past 30 days. To solve this, we need to count the distinct user IDs for each activity date.

## Intuition
The key insight here is that we can use a simple GROUP BY clause with the activity_date column to group the data by day, and then use COUNT(DISTINCT) to count the unique users for each day.

## Algorithm
1. Write a SELECT statement to retrieve the required columns (activity_date and user_id).
2. Use a WHERE clause to filter the data to only include dates between '2019-06-28' and '2019-07-27'.
3. Use a GROUP BY clause with the activity_date column to group the data by day.
4. Use COUNT(DISTINCT) to count the unique user IDs for each day.

## Concept to Remember
* Using GROUP BY to group data by a specific column
* Using COUNT(DISTINCT) to count unique values in a column

## Common Mistakes
* Forgetting to use DISTINCT when counting unique users
* Using an incorrect date range or filtering condition
* Not grouping the data correctly, leading to incorrect counts

## Complexity Analysis
- Time: O(n), where n is the number of rows in the Activity table (because we're scanning each row once)
- Space: O(1), because we're not using any extra space that scales with the input size

## Commented Code
```sql
# Retrieve the required columns and filter the data by date range
SELECT 
  activity_date AS day, # alias column to make it more readable
  COUNT(DISTINCT user_id) AS active_users # count unique users for each day
  
FROM 
  Activity # table name is "Activity"
  
WHERE 
  activity_date BETWEEN '2019-06-28' AND '2019-07-27' # filter by date range
  
GROUP BY 
  activity_date; # group by day
```

## Interview Tips
* Make sure to carefully read and understand the problem statement before starting to write code.
* Use descriptive column aliases to make your query easier to understand.
* Double-check your filtering conditions and grouping clauses to ensure they match the problem requirements.

## Revision Checklist
- [ ] Read and understand the problem statement
- [ ] Write a clear and concise query with proper indentation and formatting
- [ ] Test the query with sample data to verify its correctness

## Similar Problems
* LeetCode 1822. Multiple Queries III
* HackerRank MySQL Query Optimization
* SQL Fiddle: User Activity For The Past 30 Days II
