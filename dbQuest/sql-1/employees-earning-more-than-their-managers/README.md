# Employees Earning More Than Their Managers

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Database`  
**Time:** O(N^2)  
**Space:** O(1)

---

## Solution (java)

```java
-- Write your PostgreSQL query statement below
select e.name as Employee
from Employee e
join Employee i 
on e.managerId = i.id
where e.salary > i.salary;
```

---

---
## Quick Revision
Find employees whose salary is greater than their direct manager's salary.
This is solved by joining the Employee table with itself to compare employee salaries with their manager's salaries.

## Intuition
The core of this problem is comparing an employee's salary with their manager's salary. Since both pieces of information (employee details and manager details) are in the same `Employee` table, we need a way to access both simultaneously. A self-join is the natural way to achieve this: we treat the `Employee` table as two separate entities, one representing the employee and the other representing the manager, and link them based on the `managerId` relationship.

## Algorithm
1.  **Self-Join**: Join the `Employee` table with itself. Let's alias the first instance as `e` (for employee) and the second instance as `i` (for individual/manager).
2.  **Join Condition**: The join condition should link an employee to their manager. This is done by matching `e.managerId` with `i.id`. This ensures that for each row in `e`, we find the corresponding manager's row in `i`.
3.  **Filtering**: Filter the results to include only those rows where the employee's salary (`e.salary`) is strictly greater than their manager's salary (`i.salary`).
4.  **Selection**: Select the `name` of the employee from the filtered results.

## Concept to Remember
*   **SQL Self-Join**: Joining a table to itself to compare rows within the same table.
*   **Relational Database Joins**: Understanding how to combine data from different (or the same) tables based on related columns.
*   **Alias Usage**: Using table aliases to distinguish between multiple instances of the same table in a query.

## Common Mistakes
*   **Incorrect Join Condition**: Forgetting to use `e.managerId = i.id` and instead using something like `e.id = i.managerId` (which would find managers of managers) or `e.id = i.id` (which would compare an employee to themselves).
*   **Missing `WHERE` Clause**: Not filtering the results to only include employees earning more than their managers.
*   **Comparing Wrong Columns**: Accidentally comparing `e.salary` to `e.salary` or `i.salary` to `i.salary` instead of `e.salary` to `i.salary`.
*   **Not Handling NULL `managerId`**: While this specific problem doesn't explicitly require it due to the join condition, in other scenarios, employees without managers (`managerId` is NULL) might need special handling. The `JOIN` implicitly excludes them here.

## Complexity Analysis
*   **Time**: O(N^2) in the worst case for a naive database implementation, where N is the number of employees. However, most database systems optimize joins, and with proper indexing on `id` and `managerId`, it can approach O(N log N) or even O(N) on average. The query itself performs a single join and filter.
*   **Space**: O(1) for the query execution itself, as it doesn't store intermediate results beyond what's needed for the output. The database might use temporary space for the join operation.

## Commented Code
```sql
-- Select the name of the employee
select e.name as Employee
-- From the Employee table, aliased as 'e' to represent employees
from Employee e
-- Join the Employee table with itself, aliased as 'i' to represent managers
join Employee i
-- The join condition: link an employee 'e' to their manager 'i'
-- where the employee's managerId matches the manager's id
on e.managerId = i.id
-- Filter the results to include only rows where the employee's salary
-- is strictly greater than their manager's salary
where e.salary > i.salary;
```

## Interview Tips
*   **Explain the Self-Join**: Clearly articulate why a self-join is necessary to compare an employee's record with their manager's record from the same table.
*   **Clarify Relationships**: Discuss the `managerId` column and how it establishes a hierarchical relationship within the `Employee` table.
*   **Edge Cases (if applicable)**: Although not critical for this specific problem's constraints, be prepared to discuss how you'd handle employees without managers (NULL `managerId`) if the problem were slightly different.
*   **Database Performance**: Briefly mention how indexing on `id` and `managerId` would significantly improve the performance of this query in a real-world database.

## Revision Checklist
- [ ] Understand the problem: identify employees earning more than their managers.
- [ ] Recognize the need for a self-join.
- [ ] Correctly define the join condition (`e.managerId = i.id`).
- [ ] Implement the salary comparison filter (`e.salary > i.salary`).
- [ ] Select the correct output column (`e.name`).
- [ ] Consider time and space complexity.

## Similar Problems
*   [181. Employees Earning More Than Their Managers](https://leetcode.com/problems/employees-earning-more-than-their-managers/) (This is the exact problem)
*   [178. Rank Scores by Factory](https://leetcode.com/problems/rank-scores-by-factory/) (Involves ranking, often uses window functions or self-joins)
*   [570. Managers with at Least 5 Direct Reports](https://leetcode.com/problems/managers-with-at-least-5-direct-reports/) (Another self-join problem, focusing on aggregation)

## Tags
`Database` `SQL`
