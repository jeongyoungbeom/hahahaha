package com.koreait.day03.controller.api;

import com.koreait.day03.controller.CrudController;
import com.koreait.day03.ifs.CrudInterface;
import com.koreait.day03.model.entity.Users;
import com.koreait.day03.model.network.Header;
import com.koreait.day03.model.network.request.UserApiRequest;
import com.koreait.day03.model.network.response.UserApiResponse;
import com.koreait.day03.model.network.response.UserOrderInfoApiResponse;
import com.koreait.day03.service.UserApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController extends CrudController<UserApiRequest, UserApiResponse, Users> {
    private final UserApiLogicService userApiLogicService;

    @Override
    @PostMapping("") // /api/user
    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {
        System.out.println(request);
        return userApiLogicService.create(request);
    }
/*
{
    "transaction_time":"2021-08-23",
    "resultCode" : "OK",
    "description" : "OK",
    "data" : {
        "userid" : "kakao",
        "userpw" : "1234",
        "email" : "kakao@kakao.con",
        "hp" : "010-1234-1234"
        }
}
*/

    @Override
    @GetMapping("{id}") // /api/user/{id} (get)
    public Header<UserApiResponse> read(@PathVariable(name="id") Long id) {

        return userApiLogicService.read(id);
    }


    /*
    {
    "transaction_time":"2021-08-23",
    "resultCode" : "OK",
    "description" : "OK",
    "data" : {
        "id" = 64,
        "userid" : "kakao",
        "userpw" : "9999",
        "email" : "kakao@naver.com",
        "hp" : "010-9999-1234"
        }
}
    */
    @Override
    @PutMapping("") // api/user (put)
    public Header<UserApiResponse> update(@RequestBody Header<UserApiRequest> request) {
        return userApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")  // api/user/{id}
    public Header<UserApiResponse> delete(@PathVariable(name="id") Long id) {
        return userApiLogicService.delete(id);
    }

    /*
    @GetMapping("{id}/orderInfo") // /api/user/1/orderInfo
    public Header<UserOrderInfoApiResponse> orderInfo(@PathVariable Long id){
        return userApiLogicService.orderInfo(id);
    }
    */
    @GetMapping("")
    public Header<List<UserApiResponse>> findAll(@PageableDefault(sort={"id"}, direction= Sort.Direction.DESC) Pageable pageable){
        return userApiLogicService.search(pageable);
    }

}
