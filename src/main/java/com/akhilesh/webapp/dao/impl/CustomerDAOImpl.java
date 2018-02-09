package com.akhilesh.webapp.dao.impl;

import com.akhilesh.webapp.dao.CustomerDAO;
import com.akhilesh.webapp.entity.Customer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Akhilesh
 */
@Repository(value = "customerDAO")
public class CustomerDAOImpl implements CustomerDAO {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public List<Customer> getAll() throws ClassNotFoundException, SQLException {
        String sql = "select * from customers";
        return jdbcTemplate.query(sql, new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet rs, int i) throws SQLException {
                return customerMapper(rs);
            }
        });
    }
    
    @Override
    public int insert(Customer c) throws ClassNotFoundException, SQLException {
        String sql = "insert into customers"
                + "(first_name,last_name,email,contact_no,status)"
                + "values(?,?,?,?,?)";
        
        return jdbcTemplate.update(sql, new Object[]{
            c.getFirstName(), c.getLastName(), c.getEmail(),
            c.getContactNo(), c.isStatus()
        });
    }
    
    @Override
    public Customer getById(int id) throws ClassNotFoundException, SQLException {
        String sql = "select * from customers where id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet rs, int i) throws SQLException {
                return customerMapper(rs);
            }
        });
    }
    
    private Customer customerMapper(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setId(rs.getInt("id"));
        customer.setFirstName(rs.getString("first_name"));
        customer.setLastName(rs.getString("last_name"));
        customer.setEmail(rs.getString("email"));
        customer.setContactNo(rs.getString("contact_no"));
        customer.setStatus(rs.getBoolean("status"));
        return customer;
    }

    @Override
    public int update(Customer c) throws ClassNotFoundException, SQLException {
        String sql = "update customers set "
                + "first_name=?,last_name=?,email=?,contact_no=?,modified_date=curdate(),status=?"
                + " where id =?";
        
        return jdbcTemplate.update(sql, new Object[]{
            c.getFirstName(), c.getLastName(), c.getEmail(),
            c.getContactNo(), c.isStatus(),c.getId()
        });
    }

    @Override
    public int delete(int id) throws ClassNotFoundException, SQLException {
         String sql = "delete from customers where id =?";
        
        return jdbcTemplate.update(sql, new Object[]{id});
    }
    
}
