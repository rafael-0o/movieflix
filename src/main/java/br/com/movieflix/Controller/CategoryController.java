package br.com.movieflix.Controller;

import br.com.movieflix.Controller.Request.CategoryRequest;
import br.com.movieflix.Controller.Response.CategoryResponse;
import br.com.movieflix.Entity.Category;
import br.com.movieflix.Mapper.CategoryMapper;
import br.com.movieflix.Service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/movieflix/category")
@Tag(name = "Category", description = "Resource responsible for managing categories")
public class CategoryController {
    private final CategoryService categoryService;
    @Operation(summary = "get all categories", description = "show all saved categories",
    security=@SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "caught all categories",
    content = @Content(schema=@Schema(implementation = CategoryResponse.class)))
    @GetMapping("/get")
    public ResponseEntity<List<CategoryResponse>> getAllcategories(){
        List<CategoryResponse> categories = categoryService.findAll()
                .stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();
        return ResponseEntity.ok(categories);
    }
    @Operation(summary = "create category", description = "create a new category",
    security=@SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "category created",
    content = @Content(schema=@Schema(implementation = CategoryResponse.class)))
    @PostMapping("/create")
    public ResponseEntity<CategoryResponse> saveCategory(@Valid @RequestBody CategoryRequest categoryRequest){
        Category newCategory = CategoryMapper.toCategory(categoryRequest);
        Category savedCategory = categoryService.saveCategory(newCategory);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CategoryMapper.toCategoryResponse(savedCategory));
    }
    @Operation(summary = "find category", description = "find category by id",
    security=@SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "found category",
            content = @Content(schema = @Schema(implementation = CategoryResponse.class))
        )
    @GetMapping("/findbyid/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id){
        return categoryService.findById(id)
                .map(category -> ResponseEntity.ok(CategoryMapper.toCategoryResponse(category)))
                .orElse(ResponseEntity.notFound().build());
    }
    @Operation(summary="delete category", description = "delete category by id",
    security=@SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "204", description = "category was deleted")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        categoryService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();

    }
}
