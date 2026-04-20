package com.employee.dao;

import com.employee.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    // ──────────────────────────────────────────────
    // CREATE
    // ──────────────────────────────────────────────
    public boolean addEmployee(Employee emp) {
        String sql = "INSERT INTO employees (first_name, last_name, email, phone, department, " +
                     "designation, salary, joining_date, gender, address) VALUES (?,?,?,?,?,?,?,?,?,?)";
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, emp.getFirstName());
            ps.setString(2, emp.getLastName());
            ps.setString(3, emp.getEmail());
            ps.setString(4, emp.getPhone());
            ps.setString(5, emp.getDepartment());
            ps.setString(6, emp.getDesignation());
            ps.setDouble(7, emp.getSalary());
            ps.setString(8, emp.getJoiningDate());
            ps.setString(9, emp.getGender());
            ps.setString(10, emp.getAddress());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBConnection.closeConnection(con);
        }
    }

    // ──────────────────────────────────────────────
    // READ ALL
    // ──────────────────────────────────────────────
    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employees ORDER BY id DESC";
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(con);
        }
        return list;
    }

    // ──────────────────────────────────────────────
    // READ ONE
    // ──────────────────────────────────────────────
    public Employee getEmployeeById(int id) {
        String sql = "SELECT * FROM employees WHERE id = ?";
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(con);
        }
        return null;
    }

    // ──────────────────────────────────────────────
    // UPDATE
    // ──────────────────────────────────────────────
    public boolean updateEmployee(Employee emp) {
        String sql = "UPDATE employees SET first_name=?, last_name=?, email=?, phone=?, department=?, " +
                     "designation=?, salary=?, joining_date=?, gender=?, address=? WHERE id=?";
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, emp.getFirstName());
            ps.setString(2, emp.getLastName());
            ps.setString(3, emp.getEmail());
            ps.setString(4, emp.getPhone());
            ps.setString(5, emp.getDepartment());
            ps.setString(6, emp.getDesignation());
            ps.setDouble(7, emp.getSalary());
            ps.setString(8, emp.getJoiningDate());
            ps.setString(9, emp.getGender());
            ps.setString(10, emp.getAddress());
            ps.setInt(11, emp.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBConnection.closeConnection(con);
        }
    }

    // ──────────────────────────────────────────────
    // DELETE
    // ──────────────────────────────────────────────
    public boolean deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBConnection.closeConnection(con);
        }
    }

    // ──────────────────────────────────────────────
    // HELPER
    // ──────────────────────────────────────────────
    private Employee mapRow(ResultSet rs) throws SQLException {
        Employee emp = new Employee();
        emp.setId(rs.getInt("id"));
        emp.setFirstName(rs.getString("first_name"));
        emp.setLastName(rs.getString("last_name"));
        emp.setEmail(rs.getString("email"));
        emp.setPhone(rs.getString("phone"));
        emp.setDepartment(rs.getString("department"));
        emp.setDesignation(rs.getString("designation"));
        emp.setSalary(rs.getDouble("salary"));
        emp.setJoiningDate(rs.getString("joining_date"));
        emp.setGender(rs.getString("gender"));
        emp.setAddress(rs.getString("address"));
        return emp;
    }
}
