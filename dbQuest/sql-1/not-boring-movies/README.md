# Not Boring Movies

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Database`  
**Time:** O(N log N)  
**Space:** O(1)

---

## Solution (java)

```java
# Write your MySQL query statement below
select * from Cinema
where id%2 <> 0 && description <> 'boring'
order by rating desc
```

---

---
## Quick Revision
Select movies from a cinema table that have an odd ID and a description that is not 'boring', ordered by rating in descending order.
This is solved using a SQL SELECT statement with WHERE and ORDER BY clauses.

## Intuition
The problem asks us to filter a table of movies based on two criteria: the movie's ID must be odd, and its description must not be "boring". We also need to present these filtered movies in a specific order, from highest rating to lowest. This directly translates to using SQL's `WHERE` clause for filtering and `ORDER BY` for sorting. The modulo operator (`%`) is perfect for checking odd/even numbers, and a simple inequality (`<>` or `!=`) works for filtering out the "boring" descriptions.

## Algorithm
1. Select all columns (`*`) from the `Cinema` table.
2. Filter the rows using the `WHERE` clause.
3. The first condition in the `WHERE` clause checks if the `id` is odd. This can be done using the modulo operator: `id % 2 <> 0` (or `id % 2 = 1`).
4. The second condition in the `WHERE` clause checks if the `description` is not equal to 'boring': `description <> 'boring'` (or `description != 'boring'`).
5. Combine these two conditions using the logical `AND` operator (`&&` or `AND`).
6. Sort the resulting rows in descending order of their `rating` using the `ORDER BY rating DESC` clause.

## Concept to Remember
*   SQL `SELECT` statement: Used to retrieve data from a database.
*   SQL `WHERE` clause: Used to filter records based on specified conditions.
*   SQL `ORDER BY` clause: Used to sort the result set in ascending or descending order.
*   Modulo operator (`%`): Useful for determining if a number is even or odd.

## Common Mistakes
*   Forgetting to use the `AND` operator to combine multiple filtering conditions.
*   Using the wrong comparison operator (e.g., `=` instead of `<>`) for the description.
*   Incorrectly applying the modulo operator for odd/even checks (e.g., `id % 2 = 0` for odd).
*   Not specifying `DESC` for descending order in the `ORDER BY` clause.
*   Typographical errors in table or column names, or string literals ('boring').

## Complexity Analysis
*   Time: O(N log N) - The dominant factor is sorting the results by rating. N is the number of rows in the `Cinema` table.
*   Space: O(1) - The query itself does not require significant additional memory beyond what's needed to store the result set.

## Commented Code
```sql
# Select all columns from the Cinema table
select *
# Specify the table to retrieve data from
from Cinema
# Filter the rows based on specific conditions
where
    # Condition 1: The movie ID must be odd (remainder when divided by 2 is not 0)
    id % 2 <> 0
    # Combine Condition 1 with Condition 2 using logical AND
    &&
    # Condition 2: The movie description must not be 'boring'
    description <> 'boring'
# Sort the filtered results by the 'rating' column in descending order
order by rating desc;
```

## Interview Tips
*   Clearly explain your understanding of the filtering criteria (odd ID, not boring) and the sorting requirement (descending rating).
*   Mention the specific SQL clauses you would use (`SELECT`, `FROM`, `WHERE`, `ORDER BY`) and why.
*   Be prepared to explain the logic behind `id % 2 <> 0` and `description <> 'boring'`.
*   If asked about performance, discuss the time complexity related to sorting.

## Revision Checklist
- [ ] Understand the problem statement: select specific movies.
- [ ] Identify filtering conditions: odd ID, non-boring description.
- [ ] Identify sorting requirement: descending rating.
- [ ] Recall SQL syntax for `SELECT`, `FROM`, `WHERE`, `ORDER BY`.
- [ ] Remember the modulo operator for odd/even checks.
- [ ] Practice writing the query from scratch.

## Similar Problems
*   (No direct LeetCode SQL problems are listed here as this is a single SQL query problem. In a real interview, you might be asked to translate this logic to a programming language or discuss database indexing for performance.)

## Tags
`SQL` `Database` `Math`
