package com.koreait.day03.service;

import com.koreait.day03.model.front.AdminMenu;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AdminMenuService {
    public List<AdminMenu> getAdminMenu(){
        return Arrays.asList(
                AdminMenu.builder().title("Dash Board").url("/pages").img("settings").code("board").build(),
                AdminMenu.builder().title("고객관리").url("/pages/user").img("delete").code("user").build(),
                AdminMenu.builder().title("관리자관리").url("/pages/adminuser").img("fingerprint").code("adminuser").build(),
                AdminMenu.builder().title("카테고리관리").url("/pages/category").img("question_answer").code("category").build(),
                AdminMenu.builder().title("아이템관리").url("/pages/item").img("credit_card").code("item").build(),
                AdminMenu.builder().title("구매정보관리").url("/pages/ordergroup").img("fact_check").code("OrderGroup").build(),
                AdminMenu.builder().title("업체관리").url("/pages/partner").img("verified_user").code("partner").build()
        );
    }
}
