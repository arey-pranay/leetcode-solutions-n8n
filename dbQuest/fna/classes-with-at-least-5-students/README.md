# Classes With At Least 5 Students

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Database`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
-- Write your PostgreSQL query statement below
select class 
from Courses
group by class
having count(student)>=5
```

---

---
## Quick Revision
The problem requires writing a PostgreSQL query to find the classes with at least 5 students. We can solve this by using the `GROUP BY` clause and the `HAVING` keyword.

## Intuition
The key insight here is that we need to group the data by class and then apply a condition to filter out classes with less than 5 students. The `HAVING` keyword is used for filtering after grouping, whereas `WHERE` would be used before grouping.

## Algorithm

1. First, write the basic `SELECT` statement to retrieve the `class` column.
2. Use the `FROM` clause to specify the `Courses` table as the data source.
3. Apply the `GROUP BY` clause to group the rows by the `class` column.
4. Finally, use the `HAVING` keyword with the condition `count(student) >= 5` to filter out classes with less than 5 students.

## Concept to Remember
*   Grouping data: This problem requires grouping the data by class using the `GROUP BY` clause.
*   Filtering after grouping: The `HAVING` keyword is used for filtering after applying a group-by operation.
*   SQL aggregate functions: We use the `count()` function to count the number of students in each class.

## Common Mistakes
*   Confusing `WHERE` and `HAVING`: It's easy to get these two keywords mixed up, but they serve different purposes. `WHERE` is used for filtering before grouping, while `HAVING` is used after grouping.
*   Not using the correct aggregate function: In this case, we need to use the `count()` function to count the number of students in each class.
*   Not testing for edge cases: Make sure to test your query with different scenarios, such as a class with exactly 5 students.

## Complexity Analysis
- Time: O(n) - The time complexity is linear because we're scanning the data once to group and filter it.
- Space: O(1) - We're not using any additional space that scales with input size, so the space complexity is constant.

## Commented Code

```sql
-- Select the class column from the Courses table
select class 
  -- Group the rows by the class column
  from Courses 
  group by class
  -- Filter out classes with less than 5 students using HAVING
  having count(student) >= 5;
```

## Interview Tips

*   Practice writing SQL queries for different scenarios to improve your skills.
*   Pay attention to the difference between `WHERE` and `HAVING`.
*   Test your query thoroughly to ensure it produces the correct results.

## Revision Checklist
- [ ] Understand the problem statement and requirements.
- [ ] Write a basic SQL query to retrieve the class column.
- [ ] Apply the `GROUP BY` clause to group the rows by the class column.
- [ ] Use the `HAVING` keyword with the condition `count(student) >= 5`.

## Similar Problems
*   Find the titles of movies that have won at least one award (`LeetCode #1816`)
*   Write a SQL query to find all customers who have placed at least two orders (`LeetCode #1118`)
