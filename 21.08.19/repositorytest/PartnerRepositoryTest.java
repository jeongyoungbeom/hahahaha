package com.koreait.day02.repository;

import com.koreait.day02.Day02ApplicationTests;
import com.koreait.day02.model.entity.Partner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class PartnerRepositoryTest extends Day02ApplicationTests {
    @Autowired
    private PartnerRepository partnerRepository;

    @Test
    public void create(){
        Partner partner = Partner.builder()
                .name("호로로로로롤")
                .status("사용중")
                .address("서울시 금천구")
                .callCenter("070-4444-4444")
                .businessNumber("444-44-44444")
                .ceoName("장지환")
                .regDate(LocalDateTime.now())
                .categoryId(6L)
                .build();
        Partner newpartner = partnerRepository.save(partner);
    }

    @Test
    public void read(){
        Optional<Partner> partner = partnerRepository.findFirstByNameOrderByIdDesc("베스트샵");
        partner.ifPresent(selectpart ->{
            System.out.println(selectpart.getName());
            System.out.println(selectpart.getStatus());
            System.out.println(selectpart.getAddress());
            System.out.println(selectpart.getCallCenter());
            System.out.println(selectpart.getBusinessNumber());
            System.out.println(selectpart.getCeoName());
        });
    }

    @Test
    public void update(){
        Optional<Partner> partner = partnerRepository.findByName("베스트샵");
        partner.ifPresent(selectpart ->{
            selectpart.setStatus("사용중");
            selectpart.setAddress("서울시 금천구");
            selectpart.setCeoName("오지환");
            partnerRepository.save(selectpart);
        });
   }

   @Test
    public void delete(){
        Optional<Partner> partner = partnerRepository.findByCeoName("장지환");
        partner.ifPresent(selectpart ->{
            partnerRepository.delete(selectpart);
        });

        Optional<Partner> newpartner = partnerRepository.findByCeoName("장지환");
        if (newpartner.isPresent()){
            System.out.println("삭제실패");
        }else{
            System.out.println("삭제성공");
        }
   }

}
