package com.group7.asd.dao;

import com.group7.asd.model.*;

import java.sql.*;
import java.util.ArrayList;


// Database related operations
public class Database {
    private static Database _instance = null;

    // Private constructor
    private Database() {

    }

    // Get database operation instances
    public static Database instance() {
        if (_instance == null) {
            _instance = new Database();
        }
        return _instance;
    }

    // Get database connection
    private Connection getConnection() throws SQLException {
        try {
            // Set the database driver to mysql
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // Set the database address/account/password and get the database connection
        Connection conn = DriverManager.getConnection("jdbc:mysql://asd-database.mysql.database.azure.com/demo?characterEncoding=utf8&useSSL=true", "asd", "bzsg.+CiGd2f");

        return conn;
    }

    public ArrayList<Staff> getStaffs() {
        ArrayList<Staff> staffs = new ArrayList<Staff>();
        try (Connection conn = getConnection()) {
            String sql = "select userid,email, password, fullname, phone, usertype, isactive,manager from users where usertype='STAFF'";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Staff staff = new Staff();
                int i = 1;
                staff.setUserId(rs.getString(i++));
                staff.setEmail(rs.getString(i++));
                staff.setPassword(rs.getString(i++));
                staff.setFullName(rs.getString(i++));
                staff.setPhone(rs.getString(i++));
                staff.setUserType(rs.getString(i++));
                staff.setIsActive(rs.getString(i++));
                staff.setManager(rs.getString(i++));
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
            String sql = "insert into users (email, password, fullname, phone, usertype, isactive,manager) values (?,?,?,?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(sql);
            int i = 1;
            st.setString(i++, staff.getEmail());
            st.setString(i++, staff.getPassword());
            st.setString(i++, staff.getFullName());
            st.setString(i++, staff.getPhone());
            st.setString(i++, "STAFF");
            st.setString(i++, "1");
            st.setString(i++, staff.getManager());
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void editStaff(Staff staff) {
        try (Connection conn = getConnection()) {
            String sql = "update users set email=?, password=?, fullname=?, phone=?, isactive=?, manager=? where userid=?";
            PreparedStatement st = conn.prepareStatement(sql);
            int i = 1;
            st.setString(i++, staff.getEmail());
            st.setString(i++, staff.getPassword());
            st.setString(i++, staff.getFullName());
            st.setString(i++, staff.getPhone());
            st.setString(i++, staff.getIsActive());
            st.setString(i++, staff.getManager());
            st.setString(i++, staff.getUserId());
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteStaff(String id) {
        try (Connection conn = getConnection()) {
            String sql = "delete from users where userid=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, id);
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get a list of all Customers
    public ArrayList<Customer> getCustomers() {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        try (Connection conn = getConnection()) {
            String sql = "select id,name, phone, address, comment from customer ";
            PreparedStatement st = conn.prepareStatement(sql);
            // Get database execution results
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                // Encapsulate into a Customer object
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

    // Add a Customer
    public int addCustomer(Customer customer) {
        try (Connection conn = getConnection()) {
            String sql = "insert into customer (name, phone, address, comment) values (?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(sql);
            int i = 1;
            // Set SQL parameters
            st.setString(i++, customer.getName());
            st.setString(i++, customer.getPhone());
            st.setString(i++, customer.getAddress());
            st.setString(i++, customer.getComment());
            // Execute SQL
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Update a Customer
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

    // DeleteCustomer
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
    public Product getProductsById(Integer id){
        Product product = new Product();
        try (Connection conn = getConnection()) {

            String sql = "SELECT * FROM tb_brand WHERE id = '"+id+"';";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {


                product.setId(Integer.parseInt(rs.getString("id")));
                product.setBrand_name(rs.getString("brand_name"));
                product.setCompany_name(rs.getString("company_name"));
                product.setOrdered(Integer.parseInt(rs.getString("ordered")));
                product.setDescription(rs.getString("description"));
                product.setStatus(Integer.parseInt(rs.getString("status")));

            }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;

    }
    public ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<Product>();
        try (Connection conn = getConnection()) {
            String sql = "select id,brand_name,company_name,ordered,description,status from tb_brand ";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                int i = 1;
                product.setId(Integer.parseInt(rs.getString(i++)));
                product.setBrand_name(rs.getString(i++));
                product.setCompany_name(rs.getString(i++));
                product.setOrdered(Integer.parseInt(rs.getString(i++)));
                product.setDescription(rs.getString(i++));
                product.setStatus(Integer.parseInt(rs.getString(i++)));
                products.add(product);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
    public ArrayList<OrderDetail> getOrderDetailList() {
        ArrayList<OrderDetail> orderDetailArrayList = new ArrayList<OrderDetail>();
        try (Connection conn = getConnection()) {
            String sql = "select id,order_no,product_name,price,number from order_detail";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                OrderDetail orderDetail = new OrderDetail();
                int i = 1;
                if(rs.getString("id") != null && !rs.getString("id").equals("")) {
                    orderDetail.setId(Integer.valueOf(rs.getString("id")));
                }
                orderDetail.setOrder_no(rs.getString("order_no"));
                orderDetail.setProductName(rs.getString("product_name"));
                if(rs.getString("price") != null && !rs.getString("price").equals("")) {
                    orderDetail.setPrice(Double.parseDouble(rs.getString("price")));
                }
                if(rs.getString("number") != null && !rs.getString("number").equals("")) {
                    orderDetail.setNumber(Integer.valueOf(rs.getString("number")));
                }
                orderDetailArrayList.add(orderDetail);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderDetailArrayList;
    }

    public ArrayList<OrderInformation> getOrderList(String user_id) {
        ArrayList<OrderInformation> orderInformations = new ArrayList<OrderInformation>();
        try (Connection conn = getConnection()) {
            String sql = "select order_no,totalMoney,user_id,address,phone,idcard,password,paytype from order_information where user_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, user_id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                OrderInformation orderInformation = new OrderInformation();
                int i = 1;

                orderInformation.setOrderNo(rs.getString("order_no"));
                if(rs.getString("totalMoney") != null && !rs.getString("totalMoney").equals("")) {
                    orderInformation.setTotalMoney(Double.parseDouble(rs.getString("totalMoney")));
                }

                orderInformation.setUser_id(rs.getString("user_id"));

                orderInformation.setAddress(rs.getString("address"));
                orderInformation.setPhone(rs.getString("phone"));
                orderInformation.setIdcard(rs.getString("idcard"));

                orderInformation.setPassword(rs.getString("password"));
                if(rs.getString("paytype") != null && !rs.getString("paytype").equals("")) {
                    orderInformation.setPaytype(Integer.valueOf(rs.getString("paytype")));
                }
                orderInformations.add(orderInformation);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderInformations;
    }
    public void addProduct(Product product) {
        try (Connection conn = getConnection()) {
            String sql = "insert into tb_brand (brand_name, company_name,ordered, description, status) values (?,?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(sql);
            int i = 1;
            st.setString(i++, product.getBrand_name());
            st.setString(i++, product.getCompany_name());
            st.setString(i++, String.valueOf(product.getOrdered()));
            st.setString(i++, product.getDescription());
            st.setString(i++, String.valueOf(product.getStatus()));

            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    public void addProductTest(Product product) {
        try (Connection conn = getConnection()) {
            String sql = "insert into tb_brand (id, brand_name, company_name,ordered, description, status) values (?,?,?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(sql);
            int i = 1;
            st.setString(i++, String.valueOf(product.getId()));
            st.setString(i++, product.getBrand_name());
            st.setString(i++, product.getCompany_name());
            st.setString(i++, String.valueOf(product.getOrdered()));
            st.setString(i++, product.getDescription());
            st.setString(i++, String.valueOf(product.getStatus()));

            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void editProduct(Product product) {
        try (Connection conn = getConnection()) {
            String sql = "update tb_brand set brand_name=?, company_name=?,ordered=?,description=?,status=? where id=?";
            PreparedStatement st = conn.prepareStatement(sql);
            int i = 1;
            st.setString(i++, product.getBrand_name());
            st.setString(i++, product.getCompany_name());
            st.setString(i++, String.valueOf(product.getOrdered()));
            st.setString(i++, product.getDescription());
            st.setString(i++, String.valueOf(product.getStatus()));
            st.setString(i++, String.valueOf(product.getId()));

            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(int id) {
        try (Connection conn = getConnection()) {
            String sql = "delete from tb_brand where id=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
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
            String sql = "select c.id,o.id,o.brand_name,o.company_name,o.ordered,o.description,o.status, c.gest, c.miss, c.wrong from `sp_brand` o left join `checklist` c  on c.order_id=o.id";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Check check = new Check();
                int i = 1;
                check.setId(rs.getString(i++));
                check.setOrderId(rs.getString(i++));
                check.setBrandName(rs.getString(i++));
                check.setCompanyName(rs.getString(i++));
                check.setOrdered(rs.getString(i++));
                check.setDescription(rs.getString(i++));
                check.setStatus(rs.getString(i++));
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

    public String getManager(int userId){
        String manager = "";
        try (Connection conn = getConnection()) {
            String sql = "SELECT manager FROM USERS WHERE USERID=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1,userId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                manager=rs.getString(1);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return manager;
    }
}
