package br.com.dcsolution.billstopay.modules.category.rest;

import br.com.dcsolution.billstopay.modules.category.dto.CategoryDto;
import br.com.dcsolution.billstopay.modules.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(final CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<Page<CategoryDto>> findAll(@RequestParam final Integer page,
                                                     @RequestParam final Integer size,
                                                     @RequestParam final String searchTerm) {
        return new ResponseEntity<>(categoryService.findAll(page, size, searchTerm),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> findById(@PathVariable final Integer id) {
        return new ResponseEntity<>(categoryService.findById(id),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> post(@RequestBody @Valid CategoryDto categoryDto) {
        categoryService.create(categoryDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> put(@RequestBody @Valid CategoryDto categoryDto) {
        categoryService.update(categoryDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Integer id) {
        categoryService.delete(id);
        return ResponseEntity.ok().build();
    }
}
