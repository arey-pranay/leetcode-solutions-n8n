# Students And Examinations

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Database`  
**Time:** O(S * Sub * E)  
**Space:** O(S * Sub)

---

## Solution (java)

```java

Select s.student_id, s.student_name, sb.subject_name, count(e.student_id) as attended_exams
from Students as s CROSS JOIN Subjects as sb LEFT JOIN Examinations as e
ON s.student_id = e.student_id AND sb.subject_name = e.subject_name
GROUP BY s.student_id, s.student_name, sb.subject_name
ORDER BY s.student_id, sb.subject_name



-- select x.student_id ,x.student_name, x.subject_name  as attended_exams from (
-- select s.student_id  ,s.student_name, sb.subject_name
-- from Students s 
-- cross join Subjects sb) as x 
-- left join Examinations e
-- on x.subject_name = e.subject_name
-- group by student_id, student_name,subject_name
-- order by student_id , subject_name
-- 1 Alice M
--         P
--         Pr
-- 2 Bob   M
--         P
--         Pr
-- 13      
-- 6

```

---

---
## Quick Revision
This problem asks to count the number of exams each student has attended for each subject.
We solve this by joining students, subjects, and examinations, ensuring all student-subject combinations are present and then counting the matched examinations.

## Intuition
The core idea is to generate all possible combinations of students and subjects first. This ensures that even if a student hasn't taken any exams for a particular subject, that combination still appears in our result set. Then, we can use a `LEFT JOIN` with the `Examinations` table to count how many exams (if any) were actually taken for each student-subject pair. The `COUNT(e.student_id)` will correctly return 0 for combinations where no exams were taken.

## Algorithm
1.  Start with the `Students` table (aliased as `s`).
2.  Perform a `CROSS JOIN` with the `Subjects` table (aliased as `sb`). This creates a temporary table containing every possible combination of a student and a subject.
3.  Perform a `LEFT JOIN` from this combined table to the `Examinations` table (aliased as `e`). The join condition should match both `student_id` and `subject_name` from the `Students` and `Subjects` tables to the corresponding columns in the `Examinations` table.
4.  Group the results by `s.student_id`, `s.student_name`, and `sb.subject_name`. This is crucial to aggregate the counts for each unique student-subject pair.
5.  Use the `COUNT(e.student_id)` aggregate function. For rows where there was no matching examination (due to the `LEFT JOIN`), `e.student_id` will be `NULL`, and `COUNT(NULL)` correctly evaluates to 0.
6.  Select the `student_id`, `student_name`, `subject_name`, and the calculated `attended_exams` count.
7.  Order the final result by `s.student_id` and `sb.subject_name` as requested.

## Concept to Remember
*   **`CROSS JOIN`**: Used to generate all possible pairings between rows of two tables. Essential for ensuring all student-subject combinations are considered.
*   **`LEFT JOIN`**: Crucial for including all rows from the "left" table (in this case, the generated student-subject combinations) and matching them with rows from the "right" table (`Examinations`). If no match is found, `NULL` values are returned for the right table's columns.
*   **`GROUP BY` and Aggregate Functions (`COUNT`)**: Used to summarize data by grouping rows that have the same values in specified columns and then applying an aggregate function (like `COUNT`) to each group.

## Common Mistakes
*   **Forgetting `CROSS JOIN`**: If you start with a `LEFT JOIN` directly from `Students` to `Examinations` and then try to join `Subjects`, you might miss student-subject combinations where no exams were taken.
*   **Incorrect `JOIN` condition**: Not including both `student_id` and `subject_name` in the `LEFT JOIN` condition to `Examinations` will lead to incorrect counts.
*   **Not grouping correctly**: Failing to `GROUP BY` all the non-aggregated columns (`student_id`, `student_name`, `subject_name`) will result in an error or incorrect aggregation.
*   **Using `COUNT(*)` instead of `COUNT(e.student_id)`**: `COUNT(*)` would count the rows from the left side of the `LEFT JOIN` even if there's no match in `Examinations`, incorrectly reporting 1 instead of 0 for students who haven't taken an exam for a subject.

## Complexity Analysis
*   Time: O(S * Sub * E) in the worst case, where S is the number of students, Sub is the number of subjects, and E is the number of examinations. The `CROSS JOIN` creates S * Sub rows. The `LEFT JOIN` and subsequent `GROUP BY` operation on these rows, combined with the `Examinations` table, can be proportional to the product of the sizes of the intermediate and final tables. However, if the database optimizes joins and grouping, it can be closer to O(S * Sub + E) or O(S * Sub * log(S * Sub)) depending on the implementation of `GROUP BY`. A more practical view is often O(N log N) where N is the total number of rows involved in the join and group by, considering sorting for grouping.
*   Space: O(S * Sub) for the intermediate result set generated by the `CROSS JOIN` before the `LEFT JOIN` and `GROUP BY` operations. This is the space required to hold all student-subject combinations.

## Commented Code
```java
-- Select the student's ID, name, subject name, and the count of attended exams.
SELECT
    s.student_id, -- Select the unique identifier for the student.
    s.student_name, -- Select the name of the student.
    sb.subject_name, -- Select the name of the subject.
    COUNT(e.student_id) AS attended_exams -- Count the number of examinations taken by the student for this subject.
                                          -- COUNT(e.student_id) will be 0 if no exams were taken for this student-subject pair due to LEFT JOIN.
FROM
    Students AS s -- Start with the Students table, aliased as 's'.
CROSS JOIN
    Subjects AS sb -- Perform a CROSS JOIN with the Subjects table, aliased as 'sb'.
                   -- This generates all possible combinations of students and subjects.
LEFT JOIN
    Examinations AS e -- Perform a LEFT JOIN with the Examinations table, aliased as 'e'.
                      -- This ensures all student-subject combinations are kept, even if no exams were taken.
ON
    s.student_id = e.student_id -- Match rows based on the student's ID.
    AND sb.subject_name = e.subject_name -- And also match based on the subject's name.
                                         -- This links examinations to the correct student and subject.
GROUP BY
    s.student_id, -- Group the results by student ID to aggregate counts per student.
    s.student_name, -- Include student name in GROUP BY as it's in the SELECT list and not aggregated.
    sb.subject_name -- Group by subject name to aggregate counts per subject for each student.
ORDER BY
    s.student_id, -- Order the final results by student ID for logical presentation.
    sb.subject_name; -- Then order by subject name within each student's results.
```

## Interview Tips
*   **Explain the `CROSS JOIN` first**: Emphasize that the `CROSS JOIN` is key to generating all potential student-subject pairings, which is the foundation for counting exams, including zero counts.
*   **Justify the `LEFT JOIN`**: Clearly articulate why a `LEFT JOIN` is necessary to retain all student-subject combinations from the `CROSS JOIN` and how `COUNT(e.student_id)` handles `NULL` values correctly to produce a count of 0.
*   **Clarify `COUNT(*)` vs. `COUNT(column)`**: Be prepared to explain the difference and why `COUNT(e.student_id)` is the correct choice here to avoid overcounting when no exams exist for a given pair.
*   **Discuss edge cases**: Mention scenarios like students with no exams at all, subjects no one has taken, or students who have taken exams for only a subset of subjects.

## Revision Checklist
- [ ] Understand the goal: count exams per student per subject.
- [ ] Recognize the need for all student-subject combinations.
- [ ] Implement `CROSS JOIN` for all combinations.
- [ ] Use `LEFT JOIN` to include combinations with zero exams.
- [ ] Ensure correct join conditions (`student_id` AND `subject_name`).
- [ ] Apply `GROUP BY` to aggregate results per student-subject pair.
- [ ] Use `COUNT(e.student_id)` to correctly count attended exams (0 for no exams).
- [ ] Verify `ORDER BY` clause matches requirements.

## Similar Problems
*   [1321. Restaurant Growth](https://leetcode.com/problems/restaurant-growth/) (Uses window functions, but related to cumulative/sequential analysis)
*   [1158. Market Analysis I](https://leetcode.com/problems/market-analysis-i/) (Involves joining multiple tables and counting related events)
*   [1070. Product Sales Analysis III](https://leetcode.com/problems/product-sales-analysis-iii/) (Requires careful joining and aggregation)

## Tags
`Database` `SQL` `Joins` `Aggregation` `Window Functions`
