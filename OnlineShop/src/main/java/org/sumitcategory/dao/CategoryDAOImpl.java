package org.sumitcategory.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.sumit.pojo.Category;

public class CategoryDAOImpl implements CategoryDAO {
    Connection connection;
    PreparedStatement psAllCategories;

    public CategoryDAOImpl(Connection connection) {
        super();
        try {
            this.connection = connection;
            psAllCategories = connection.prepareStatement("select * from category_1234");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Category> allCategories() {
        ArrayList<Category> list = new ArrayList<>();
        try (ResultSet result = psAllCategories.executeQuery()) {
            while (result.next()) {
                int categoryId = result.getInt("categoryId");
                String categoryName = result.getString("categoryName");
                String categoryDescription = result.getString("categoryDescription");
                String categoryImageUrl = result.getString("categoryImageUrl");
                Category objCategory = new Category(categoryId, categoryName, categoryDescription, categoryImageUrl);
                list.add(objCategory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}