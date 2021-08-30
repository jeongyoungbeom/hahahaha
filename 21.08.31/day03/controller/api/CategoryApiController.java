package com.koreait.day03.controller.api;

import com.koreait.day03.controller.CrudController;
import com.koreait.day03.model.entity.Category;
import com.koreait.day03.model.network.Header;
import com.koreait.day03.model.network.request.CategoryApiRequest;
import com.koreait.day03.model.network.response.CategoryApiResponse;
import com.koreait.day03.service.CategoryApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryApiController extends CrudController<CategoryApiRequest, CategoryApiResponse, Category> {
    private final CategoryApiLogicService categoryApiLogicService;
    @Override
    @PostMapping("")
    public Header<CategoryApiResponse> create(@RequestBody Header<CategoryApiRequest> request) {
        return categoryApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<CategoryApiResponse> read(@PathVariable(name="id") Long id) {
        return categoryApiLogicService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<CategoryApiResponse> update(@RequestBody Header<CategoryApiRequest> request) {
        return categoryApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header<CategoryApiResponse> delete(@PathVariable Long id) {
        return categoryApiLogicService.delete(id);
    }

    @GetMapping("")
    public Header<List<CategoryApiResponse>> findAll(@PageableDefault(sort={"id"}, direction = Sort.Direction.DESC)Pageable pageable){
        return categoryApiLogicService.search(pageable);
    }
}
