package Hashing;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
https://leetcode.com/problems/employee-importance/
Google

You have a data structure of employee information, including the employee's unique ID, 
importance value, and direct subordinates' IDs.

You are given an array of employees employees where:

employees[i].id is the ID of the ith employee.
employees[i].importance is the importance value of the ith employee.
employees[i].subordinates is a list of the IDs of the direct subordinates of the 
ith employee.
Given an integer id that represents an employee's ID, return the total importance 
value of this employee and all their direct and indirect subordinates

Input: employees = [[1,5,[2,3]],[2,3,[]],[3,3,[]]], id = 1
Output: 11
Explanation: Employee 1 has an importance value of 5 and has two direct subordinates:
employee 2 and employee 3.
They both have an importance value of 3.
Thus, the total importance value of employee 1 is 5 + 3 + 3 = 11.
 */

public class EmployeeImportance {
    
     public int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> emap = new HashMap<>();
        for (Employee e : employees) {
            emap.put(e.id, e);
        }
        Queue<Integer> q = new LinkedList<>();
        q.offer(id);
        int res = 0;

        while (!q.isEmpty()) {
            Employee cur = emap.get(q.poll());
            res += cur.importance;

            for (Integer sub : cur.subordinates) {
                q.offer(sub);
            }
        }
        return dfs(id, emap);
    }

    private int dfs(int id, Map<Integer, Employee> emap) {
        Employee cur = emap.get(id);
        int res = cur.importance;
        for (Integer sub : cur.subordinates) {
            res += dfs(sub, emap);
        }
        return res;
    }
}
