package com.employee.servlet;

import com.employee.dao.EmployeeDAO;
import com.employee.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private EmployeeDAO dao = new EmployeeDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                // Show empty registration form
                req.getRequestDispatcher("/WEB-INF/views/employee-form.jsp").forward(req, resp);
                break;

            case "edit":
                int editId = Integer.parseInt(req.getParameter("id"));
                Employee toEdit = dao.getEmployeeById(editId);
                req.setAttribute("employee", toEdit);
                req.getRequestDispatcher("/WEB-INF/views/employee-form.jsp").forward(req, resp);
                break;

            case "delete":
                int delId = Integer.parseInt(req.getParameter("id"));
                boolean deleted = dao.deleteEmployee(delId);
                req.getSession().setAttribute("message", deleted
                        ? "Employee deleted successfully!"
                        : "Failed to delete employee.");
                resp.sendRedirect(req.getContextPath() + "/employee?action=list");
                break;

            case "view":
                int viewId = Integer.parseInt(req.getParameter("id"));
                Employee toView = dao.getEmployeeById(viewId);
                req.setAttribute("employee", toView);
                req.getRequestDispatcher("/WEB-INF/views/employee-view.jsp").forward(req, resp);
                break;

            default: // list
                List<Employee> employees = dao.getAllEmployees();
                req.setAttribute("employees", employees);
                req.getRequestDispatcher("/WEB-INF/views/employee-list.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        Employee emp = new Employee();
        String idParam = req.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            emp.setId(Integer.parseInt(idParam));
        }
        emp.setFirstName(req.getParameter("firstName"));
        emp.setLastName(req.getParameter("lastName"));
        emp.setEmail(req.getParameter("email"));
        emp.setPhone(req.getParameter("phone"));
        emp.setDepartment(req.getParameter("department"));
        emp.setDesignation(req.getParameter("designation"));
        emp.setSalary(Double.parseDouble(req.getParameter("salary")));
        emp.setJoiningDate(req.getParameter("joiningDate"));
        emp.setGender(req.getParameter("gender"));
        emp.setAddress(req.getParameter("address"));

        boolean success;
        if ("update".equals(action)) {
            success = dao.updateEmployee(emp);
            req.getSession().setAttribute("message", success
                    ? "Employee updated successfully!"
                    : "Failed to update employee.");
        } else {
            success = dao.addEmployee(emp);
            req.getSession().setAttribute("message", success
                    ? "Employee registered successfully!"
                    : "Failed to register employee.");
        }

        resp.sendRedirect(req.getContextPath() + "/employee?action=list");
    }
}
