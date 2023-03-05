package program.interfaces;

import program.dto.category.CategoryCreateDTO;
import program.dto.category.CategoryItemDTO;
import program.dto.category.CategoryUpdateDTO;

import java.util.List;

public interface CategoryService {
    CategoryItemDTO create(CategoryCreateDTO model);
    List<CategoryItemDTO> get();
    CategoryItemDTO update(int id, CategoryUpdateDTO model);
    void delete(int id);
    CategoryItemDTO get(int id);
}
