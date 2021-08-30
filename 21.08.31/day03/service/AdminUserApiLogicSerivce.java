package com.koreait.day03.service;

import com.koreait.day03.model.entity.AdminUser;
import com.koreait.day03.model.entity.Category;
import com.koreait.day03.model.network.Header;
import com.koreait.day03.model.network.Pagination;
import com.koreait.day03.model.network.request.AdminUserApiRequest;
import com.koreait.day03.model.network.response.AdminUserApiResponse;
import com.koreait.day03.model.network.response.CategoryApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminUserApiLogicSerivce extends BaseService<AdminUserApiRequest, AdminUserApiResponse, AdminUser>{
    @Override
    public Header<AdminUserApiResponse> create(Header<AdminUserApiRequest> request) {
        return null;
    }

    @Override
    public Header<AdminUserApiResponse> read(Long id) {
        return null;
    }

    @Override
    public Header<AdminUserApiResponse> update(Header<AdminUserApiRequest> request) {
        return null;
    }

    @Override
    public Header<AdminUserApiResponse> delete(Long id) {
        return null;
    }

    public AdminUserApiResponse response(AdminUser adminUser){
        AdminUserApiResponse adminUserApiResponse = AdminUserApiResponse.builder()
                .id(adminUser.getId())
                .userid(adminUser.getUserid())
                .userpw(adminUser.getUserpw())
                .name(adminUser.getName())
                .status(adminUser.getStatus())
                .regDate(adminUser.getRegDate())
                .createBy(adminUser.getCreateBy())
                .lastLoginAt(adminUser.getLastLoginAt())
                .build();
        return adminUserApiResponse;
    }
    public Header<List<AdminUserApiResponse>> search(Pageable pageable){
        Page<AdminUser> adminUser = baseRepository.findAll(pageable);
        List<AdminUserApiResponse> adminUserApiResponseList = adminUser.stream()
                .map(adminUsers -> response(adminUsers))
                .collect(Collectors.toList());
        Pagination pagination = Pagination.builder()
                .totalPages(adminUser.getTotalPages())
                .totalElements(adminUser.getTotalElements())
                .currentPage(adminUser.getNumber())
                .currentElements(adminUser.getNumberOfElements())
                .build();
        return Header.OK(adminUserApiResponseList, pagination);
    }
}
