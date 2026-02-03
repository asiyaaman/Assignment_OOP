package com.example.simpledbapi.dao;

import com.example.simpledbapi.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAO {
    private final JdbcTemplate jdbc;

    public ProductDAO(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    private final RowMapper<Product> mapper = (rs, rowNum) ->
            new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"));

    public List<Product> findAll() {
        return jdbc.query("SELECT id, name, price FROM product ORDER BY id", mapper);
    }
    public Integer add(String name, double price) {
        return jdbc.queryForObject(
                "INSERT INTO product(name, price) VALUES (?, ?) RETURNING id",
                Integer.class,
                name, price
        );
    }
    public int updatePrice(Integer id, double newPrice) {
        return jdbc.update(
                "UPDATE product SET price = ? WHERE id = ?",
                newPrice, id
        );
    }
}
