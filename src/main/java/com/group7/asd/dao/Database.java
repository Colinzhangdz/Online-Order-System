package com.group7.asd.dao;

import com.group7.asd.model.Check;
import com.group7.asd.model.Customer;
import com.group7.asd.model.Product;
import com.group7.asd.model.Staff;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    private static Database _instance = null;

    private Database() {

    }

    public static Database instance() {
        if (_instance == null) {
            _instance = new Database();
        }
        return _instance;
    }

    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn = DriverManager.getConnection("jdbc:mysql://asd-database.mysql.database.azure.com/demo?characterEncoding=utf8&useSSL=true", "asd", "bzsg.+CiGd2f");

        return conn;
    }

    public ArrayList<Staff> getStaffs() {
        ArrayList<Staff> staffs = new ArrayList<Staff>();
        try (Connection conn = getConnection()) {
            String sql = "select id,name, gender, age, phone, username, password from staff ";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Staff staff = new Staff();
                int i = 1;
                staff.setId(rs.getString(i++));
                staff.setName(rs.getString(i++));
                staff.setGender(rs.getString(i++));
                staff.setAge(rs.getString(i++));
                staff.setPhone(rs.getString(i++));
                staff.setUsername(rs.getString(i++));
                staff.setPassword(rs.getString(i++));
                staffs.add(staff);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return staffs;
    }

    public int addStaff(Staff staff) {
        try (Connection conn = getConnection()) {
            String sql = "insert into staff (name, gender, age, phone, username, password) values (?,?,?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(sql);
            int i = 1;
            st.setString(i++, staff.getName());
            st.setString(i++, staff.getGender());
            st.setString(i++, staff.getAge());
            st.setString(i++, staff.getPhone());
            st.setString(i++, staff.getUsername());
            st.setString(i++, staff.getPassword());
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void editStaff(Staff staff) {
        try (Connection conn = getConnection()) {
            String sql = "update staff set name=?, gender=?, age=?, phone=?, username=?, password=? where id=?";
            PreparedStatement st = conn.prepareStatement(sql);
            int i = 1;
            st.setString(i++, staff.getName());
            st.setString(i++, staff.getGender());
            st.setString(i++, staff.getAge());
            st.setString(i++, staff.getPhone());
            st.setString(i++, staff.getUsername());
            st.setString(i++, staff.getPassword());
            st.setString(i++, staff.getId());
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteStaff(String id) {
        try (Connection conn = getConnection()) {
            String sql = "delete from staff where id=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, id);
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Customer> getCustomers() {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        try (Connection conn = getConnection()) {
            String sql = "select id,name, phone, address, comment from customer ";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                int i = 1;
                customer.setId(rs.getString(i++));
                customer.setName(rs.getString(i++));
                customer.setPhone(rs.getString(i++));
                customer.setAddress(rs.getString(i++));
                customer.setComment(rs.getString(i++));
                customers.add(customer);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }

    public int addCustomer(Customer customer) {
        try (Connection conn = getConnection()) {
            String sql = "insert into customer (name, phone, address, comment) values (?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(sql);
            int i = 1;
            st.setString(i++, customer.getName());
            st.setString(i++, customer.getPhone());
            st.setString(i++, customer.getAddress());
            st.setString(i++, customer.getComment());
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void editCustomer(Customer customer) {
        try (Connection conn = getConnection()) {
            String sql = "update customer set name=?, phone=?, address=?, comment=? where id=?";
            PreparedStatement st = conn.prepareStatement(sql);
            int i = 1;
            st.setString(i++, customer.getName());
            st.setString(i++, customer.getPhone());
            st.setString(i++, customer.getAddress());
            st.setString(i++, customer.getComment());
            st.setString(i++, customer.getId());
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCustomer(String id) {
        try (Connection conn = getConnection()) {
            String sql = "delete from customer where id=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, id);
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<Product>();
        try (Connection conn = getConnection()) {
            String sql = "select id,name, price from product ";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                int i = 1;
                product.setId(rs.getString(i++));
                product.setName(rs.getString(i++));
                product.setPrice(rs.getString(i++));
                products.add(product);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public void addProduct(Product product) {
        try (Connection conn = getConnection()) {
            String sql = "insert into product (name, price) values (?,?)";
            PreparedStatement st = conn.prepareStatement(sql);
            int i = 1;
            st.setString(i++, product.getName());
            st.setString(i++, product.getPrice());
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void editProduct(Product product) {
        try (Connection conn = getConnection()) {
            String sql = "update product set name=?, price=?";
            PreparedStatement st = conn.prepareStatement(sql);
            int i = 1;
            st.setString(i++, product.getName());
            st.setString(i++, product.getPrice());
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(String id) {
        try (Connection conn = getConnection()) {
            String sql = "delete from product where id=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, id);
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public int addCheck(Check check) {
        try (Connection conn = getConnection()) {
            String sql = "insert into `checklist` (order_id, gest, miss, wrong) values (?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(sql);
            int i = 1;
            st.setString(i++, check.getOrderId());
            st.setString(i++, check.getGest());
            st.setString(i++, check.getMiss());
            st.setString(i++, check.getWrong());
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void editCheck(Check check) {
        try (Connection conn = getConnection()) {
            String sql = "update `checklist` set order_id=?,gest=?, miss=?, wrong=? where id=?";
            PreparedStatement st = conn.prepareStatement(sql);
            int i = 1;
            st.setString(i++, check.getOrderId());
            st.setString(i++, check.getGest());
            st.setString(i++, check.getMiss());
            st.setString(i++, check.getWrong());
            st.setString(i++, check.getId());
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Check> getChecks() {
        ArrayList<Check> checks = new ArrayList<Check>();
        try (Connection conn = getConnection()) {
            String sql = "select c.id,o.id,o.connectionString, c.gest, c.miss, c.wrong from `ordertest` o left join `checklist` c  on c.order_id=o.id";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Check check = new Check();
                int i = 1;
                check.setId(rs.getString(i++));
                check.setOrderId(rs.getString(i++));
                check.setConnectionString(rs.getString(i++));
                check.setGest(rs.getString(i++));
                check.setMiss(rs.getString(i++));
                check.setWrong(rs.getString(i++));
                checks.add(check);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return checks;
    }
}
