package program.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import program.dto.category.CategoryCreateDTO;
import program.dto.category.CategoryItemDTO;
import program.dto.category.CategoryUpdateDTO;
import program.entities.CategoryEntity;
import program.mapper.CategoryMapper;
import program.repositories.CategoryRepository;
import program.storage.StorageService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/categories")
public class CategoryController {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final StorageService storageService;

    @GetMapping
    public ResponseEntity<List<CategoryItemDTO>> index() {
        var list = categoryRepository.findAll();
        var model = categoryMapper.CategoryItemsByCategories(list);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CategoryEntity> create(@ModelAttribute CategoryCreateDTO model) {
        var image = storageService.saveMultipartFile(model.getFile());
        //storageService.save(model.getBase64());
        CategoryEntity category = categoryMapper.CategoryByCreateCategoryDTO(model);
        category.setImage(image);
        categoryRepository.save(category);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<CategoryEntity> get(@PathVariable("id") Integer categoryId) {
        var extCategory = categoryRepository.findById(categoryId);
        return new ResponseEntity<>(extCategory.get(), HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<CategoryEntity> update(@PathVariable("id") Integer categoryId,
                                                 CategoryUpdateDTO model) {
        CategoryEntity category = categoryRepository.findById(categoryId).get();
        category.setName(model.getName());
        var upatedCategory = categoryRepository.save(category);
        return new ResponseEntity<>(upatedCategory, HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer categoryId) {
       categoryRepository.deleteById(categoryId);
        return new ResponseEntity<>("Cateogory Delete", HttpStatus.OK);
    }
}
