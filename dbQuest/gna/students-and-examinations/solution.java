
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
