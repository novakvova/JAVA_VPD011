package program.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import program.dto.category.CategoryCreateDTO;
import program.dto.category.CategoryItemDTO;
import program.entities.CategoryEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryItemDTO CategoryItemByCategory(CategoryEntity category);
    List<CategoryItemDTO> CategoryItemsByCategories(List<CategoryEntity> categories);
    @Mapping(target = "image", ignore = true)
    CategoryEntity CategoryByCreateCategoryDTO(CategoryCreateDTO dto);

}
