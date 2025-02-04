package BinaryTree;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/discuss/interview-question/1244429/Google-or-Phone-Interview-or-Org-Structure-Modification
Google

Given a tree of the organizational structure, let's say a company like "Google" .
Also mentioned that the root is always Engineer.


class Employee {
int employeeId;
boolean isEngineer;
List reportees;
}


Given a tree


							E1
			________________|________________
			|               |               | 
		   E2              NE1             E3
		___|___             |               
		|     |             |               
		E4    NE2           E5 
Convert this graph to Engineer only graph.


							E1
			________________|________________
			|               |               | 
		   E2              E5               E3
		   |                                                       
		   E4               
In the above figure, E represents Engineers and NE represents Non-Engineers.
He also asked me to modify code at the end, also gave me an option to use a new graph or to modify the current graph.
I used a new graph, he seemed convinced .

 */
public class EmployeeHierarchy {
    class Employee {
        int employeeId;
        boolean isEngineer;
        List<Employee> reportees;

        Employee(int employeeId, boolean isEngineer) {
            this.employeeId = employeeId;
            this.isEngineer = isEngineer;
            this.reportees = new ArrayList<>();
        }
    }

    public Employee convertToEngineerOnlyGraph(Employee root) {
        if (root == null) {
            return null;
        }

        if (!root.isEngineer) {
            return null;
        }

        Employee newRoot = new Employee(root.employeeId, root.isEngineer);
        for (Employee reportee : root.reportees) {
            Employee newReportee = convertToEngineerOnlyGraph(reportee);
            if (newReportee != null) {
                newRoot.reportees.add(newReportee);
            }
        }

        return newRoot;
    }
}
