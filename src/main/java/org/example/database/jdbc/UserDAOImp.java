package org.example.database.jdbc;

import org.example.database.IUserDAO;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Optional;

@Repository
public class UserDAOImp implements IUserDAO {


    @Autowired
    Connection connection;

    @Override
    public Optional<User> getUserByLogin(String login) {
        try {
            String sql = "SELECT * FROM tuser WHERE login = ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return Optional.of(new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("login"),
                        rs.getString("password"),
                        User.Role.valueOf(rs.getString("role"))
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public void persistUser(User user) {
        try {
            String sql = "INSERT INTO tuser (name, surname, login, password, role) " +
                    "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = this.connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getLogin());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getRole().toString());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            user.setId(rs.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
