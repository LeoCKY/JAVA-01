package com.l10.q6.dao.impl;

import com.l10.connect.HikariUtil;
import com.l10.q6.dao.BaseDAO;
import com.l10.q6.pojo.Q6Pojo;

import java.sql.*;

public class Q6DAO implements BaseDAO<Q6Pojo> {

    private static final String SQL_INSERT = "INSERT INTO tb_q6 values (null , ?)";

    private static final String SQL_UPDATE = "UPDATE tb_q6 SET name = ? WHERE id = ?";

    private static final String SQL_DELETE = "DELETE FROM tb_q6 WHERE id = ? ";

    private static final String SQL_QUERY= "SELECT  id, name FROM tb_q6 WHERE id = ?";

    @Override
    public Q6Pojo queryById(Integer id) {
        Connection c = HikariUtil.getConnection();
        try {
            PreparedStatement p = c.prepareStatement(SQL_QUERY);
            p.setInt(1, id);
            ResultSet r = p.executeQuery();
            if (r.next()) {
                Q6Pojo pojo = new Q6Pojo();
                String name = r.getString("name");
                pojo.setId(id);
                pojo.setName(name);
                return pojo;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            HikariUtil.connectionClose(c);
        }
        return null;
    }

    @Override
    public int insert(Q6Pojo q6Pojo) {
        Connection c = HikariUtil.getConnection();
        try {
            PreparedStatement p = c.prepareStatement(SQL_INSERT,
                    Statement.RETURN_GENERATED_KEYS);
            p.setString(1, q6Pojo.getName());
            p.executeUpdate();
            ResultSet generatedKeys = p.getGeneratedKeys();
            if (generatedKeys.next()) {
                q6Pojo.setId(generatedKeys.getInt(1));
            }
            return 1;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            HikariUtil.connectionClose(c);
        }
        return 0;
    }

    @Override
    public int update(Q6Pojo q6Pojo) {
        Connection c = HikariUtil.getConnection();
        try {
            PreparedStatement p = c.prepareStatement(SQL_UPDATE);
            p.setString(1, q6Pojo.getName());
            p.setInt(2, q6Pojo.getId());
            return p.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            HikariUtil.connectionClose(c);
        }
        return 0;
    }

    @Override
    public int deleteById(Integer id) {
        Connection c = HikariUtil.getConnection();
        try {
            PreparedStatement p = c.prepareStatement(SQL_DELETE);
            p.setInt(1, id);
            return p.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            HikariUtil.connectionClose(c);
        }
        return 0;
    }
}
