package com.koreait.day03.controller.api;

import com.koreait.day03.controller.CrudController;
import com.koreait.day03.model.entity.AdminUser;
import com.koreait.day03.model.network.Header;
import com.koreait.day03.model.network.request.AdminUserApiRequest;
import com.koreait.day03.model.network.response.AdminUserApiResponse;
import com.koreait.day03.model.network.response.CategoryApiResponse;
import com.koreait.day03.service.AdminUserApiLogicSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adminuser")
@RequiredArgsConstructor
public class AdminUserApiController extends CrudController<AdminUserApiRequest, AdminUserApiResponse, AdminUser> {
    private final AdminUserApiLogicSerivce adminUserApiLogicSerivce;
    @Override
    public Header<AdminUserApiResponse> create(@RequestBody Header<AdminUserApiRequest> request) {
        return adminUserApiLogicSerivce.create(request);
    }

    @Override
    public Header<AdminUserApiResponse> read(@PathVariable(name="id") Long id) {
        return adminUserApiLogicSerivce.read(id);
    }

    @Override
    public Header<AdminUserApiResponse> update(@RequestBody Header<AdminUserApiRequest> request) {
        return adminUserApiLogicSerivce.update(request);
    }

    @Override
    public Header<AdminUserApiResponse> delete(@PathVariable Long id) {
        return adminUserApiLogicSerivce.delete(id);
    }

    @GetMapping("")
    public Header<List<AdminUserApiResponse>> findAll(@PageableDefault(sort={"id"}, direction = Sort.Direction.DESC) Pageable pageable){
        return adminUserApiLogicSerivce.search(pageable);
    }
}
