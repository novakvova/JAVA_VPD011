package program.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import program.dto.category.CategoryCreateDTO;
import program.dto.category.CategoryItemDTO;
import program.dto.category.CategoryUpdateDTO;
import program.entities.CategoryEntity;
import program.interfaces.CategoryService;
import program.mapper.CategoryMapper;
import program.repositories.CategoryRepository;
import program.storage.StorageService;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImp implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final StorageService storageService;
    @Override
    public CategoryItemDTO create(CategoryCreateDTO model) {
        var image = storageService.saveMultipartFile(model.getFile());
        //storageService.save(model.getBase64());
        CategoryEntity category = categoryMapper.CategoryByCreateCategoryDTO(model);
        category.setImage(image);
        categoryRepository.save(category);
        var result = categoryMapper.CategoryItemByCategory(category);
        return result;
    }

    @Override
    public List<CategoryItemDTO> get() {
        var list = categoryRepository.findAll();
        return categoryMapper.CategoryItemsByCategories(list);
    }

    @Override
    public CategoryItemDTO update(int id, CategoryUpdateDTO model) {
        var opt = categoryRepository.findById(id);
        if(opt.isPresent()) {
            var cat = opt.get();
            if(model.getFile()!=null) {
                storageService.removeFile(cat.getImage());
                var image = storageService.saveMultipartFile(model.getFile());
                cat.setImage(image);
            }
            cat.setName(model.getName());
            cat.setDescription(model.getDescription());
            categoryRepository.save(cat);
            var result = categoryMapper.CategoryItemByCategory(cat);
            return result;
        }
        return null;
    }

    @Override
    public void delete(int id) {
        var cat = categoryRepository.findById(id).get();
        storageService.removeFile(cat.getImage());
        categoryRepository.deleteById(id);

    }

    @Override
    public CategoryItemDTO get(int id) {
        var opt = categoryRepository.findById(id);
        if(opt.isPresent())
            return categoryMapper.CategoryItemByCategory(opt.get());
        return null;
    }
}
