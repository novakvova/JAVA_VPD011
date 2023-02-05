import dto.CategoryInsert;
import dto.CategoryItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryService {
    String conStr;
    String user;
    String password;

    public CategoryService(String conStr, String user, String password) {
        this.conStr = conStr;
        this.user = user;
        this.password = password;
    }

    public void insert(CategoryInsert category) {
        try (Connection con = DriverManager.getConnection(conStr, user, password)) {
            System.out.println("Coonection is good");
            String query = "INSERT INTO categories (name) VALUES (?)";
            PreparedStatement command = con.prepareStatement(query);
            command.setString(1, category.getName());
            command.executeUpdate();
        } catch(Exception ex) {
            System.out.println("Помилка роботи з БД - "+ ex.getMessage());
        }
    }
    public List<CategoryItem> getList() {
        List<CategoryItem> list = new ArrayList<>();
        String sql = "SELECT * FROM categories";
        try(Connection con = DriverManager.getConnection(conStr, user, password)) {
            PreparedStatement command = con.prepareStatement(sql);
            ResultSet resutltSet = command.executeQuery();
            while(resutltSet.next()){
                CategoryItem item = new CategoryItem();
                item.setId(resutltSet.getInt("id"));
                item.setName(resutltSet.getString("name"));
                list.add(item);
            }
        }catch (Exception ex) {
            System.out.println("Error working db "+ ex.getMessage());
        }
        return  list;
    }
}
