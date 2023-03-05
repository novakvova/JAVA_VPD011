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
import program.interfaces.CategoryService;
import program.mapper.CategoryMapper;
import program.repositories.CategoryRepository;
import program.storage.StorageService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/categories")
public class CategoryController {
    private CategoryService categoryService;
    @GetMapping
    public ResponseEntity<List<CategoryItemDTO>> index() {
        var model = categoryService.get();
        return new ResponseEntity<>(model, HttpStatus.OK);
    }
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CategoryItemDTO> create(@ModelAttribute CategoryCreateDTO model) {
        var result = categoryService.create(model);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<CategoryItemDTO> get(@PathVariable("id") Integer categoryId) {
        var cat = categoryService.get(categoryId);
        return new ResponseEntity<>(cat, HttpStatus.OK);
    }
    @PutMapping(value = "{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CategoryItemDTO> update(@PathVariable("id") Integer categoryId,
                                                  @ModelAttribute CategoryUpdateDTO model) {
        var result = categoryService.update(categoryId, model);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer categoryId) {
       categoryService.delete(categoryId);
        return new ResponseEntity<>("Category Delete", HttpStatus.OK);
    }
}
