package com.koreait.day03.service;

import com.koreait.day03.model.entity.Category;
import com.koreait.day03.model.network.Header;
import com.koreait.day03.model.network.Pagination;
import com.koreait.day03.model.network.request.CategoryApiRequest;
import com.koreait.day03.model.network.request.UserApiRequest;
import com.koreait.day03.model.network.response.CategoryApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryApiLogicService extends BaseService<CategoryApiRequest, CategoryApiResponse, Category> {

    @Override
    public Header<CategoryApiResponse> create(Header<CategoryApiRequest> request) {
        CategoryApiRequest categoryApiRequest = request.getData();
        Category category = Category.builder()
                .type(categoryApiRequest.getType())
                .title(categoryApiRequest.getTitle())
                .updateBy(categoryApiRequest.getUpdateBy())
                .build();
        Category newcate = baseRepository.save(category);
        return Header.OK(response(newcate));
    }

    @Override
    public Header<CategoryApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(category -> response(category))
                .map(Header::OK)
                .orElseGet(
                        () -> Header.ERROR("데이터없음")
                );
    }

    @Override
    public Header<CategoryApiResponse> update(Header<CategoryApiRequest> request) {
        CategoryApiRequest categoryApiRequest = request.getData();
        Optional<Category> optional = baseRepository.findById(categoryApiRequest.getId());
        return optional.map(category -> {
            category.setType(categoryApiRequest.getType());
            category.setTitle(categoryApiRequest.getTitle());
            category.setUpdateBy(categoryApiRequest.getUpdateBy());

            return category;
        }).map(category -> baseRepository.save(category))
                .map(category -> response(category))
                .map(Header::OK)
                .orElseGet(()->Header.ERROR("ㄴㄴ"));
    }

    @Override
    public Header delete(Long id) {
        Optional<Category> optional = baseRepository.findById(id);

        return optional.map(category -> {
            baseRepository.delete(category);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("노 데이터"));
    }

    public CategoryApiResponse response(Category category){
        CategoryApiResponse categoryApiResponse = CategoryApiResponse.builder()
                .id(category.getId())
                .type(category.getType())
                .title(category.getTitle())
                .updateBy(category.getUpdateBy())
                .regDate(category.getRegDate())
                .updateDate(category.getUpdateDate())
                .updateBy(category.getUpdateBy())
                .build();
        return categoryApiResponse;
    }

    public Header<List<CategoryApiResponse>> search(Pageable pageable){
        Page<Category> category = baseRepository.findAll(pageable);
        List<CategoryApiResponse> categoryApiResponseList = category.stream()
                .map(categorys -> response(categorys))
                .collect(Collectors.toList());
        Pagination pagination = Pagination.builder()
                .totalPages(category.getTotalPages())
                .totalElements(category.getTotalElements())
                .currentPage(category.getNumber())
                .currentElements(category.getNumberOfElements())
                .build();
        return Header.OK(categoryApiResponseList, pagination);
    }

}
