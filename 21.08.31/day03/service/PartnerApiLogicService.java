package com.koreait.day03.service;

import com.koreait.day03.model.entity.Partner;
import com.koreait.day03.model.network.Header;
import com.koreait.day03.model.network.Pagination;
import com.koreait.day03.model.network.request.PartnerApiRequest;
import com.koreait.day03.model.network.response.PartnerApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartnerApiLogicService extends BaseService<PartnerApiRequest, PartnerApiResponse, Partner> {

    @Override
    public Header<PartnerApiResponse> create(Header<PartnerApiRequest> request) {
        PartnerApiRequest partnerApiRequest = request.getData();
        Partner partner = Partner.builder()
                .name(partnerApiRequest.getName())
                .status(partnerApiRequest.getStatus())
                .address(partnerApiRequest.getAddress())
                .callCenter(partnerApiRequest.getCallCenter())
                .businessNumber(partnerApiRequest.getBusinessNumber())
                .ceoName(partnerApiRequest.getCeoName())
                .build();
        Partner newpartner = baseRepository.save(partner);
        return Header.OK(response(newpartner));
    }

        @Override
        public Header<PartnerApiResponse> read(Long id) {
            return baseRepository.findById(id)
                    .map(partner -> response(partner))
                    .map(Header::OK)
                    .orElseGet(
                            () -> Header.ERROR("데이터없음")
                    );
        }

        @Override
        public Header<PartnerApiResponse> update(Header<PartnerApiRequest> request) {
            PartnerApiRequest partnerApiRequest = request.getData();
            Optional<Partner> optional = baseRepository.findById(partnerApiRequest.getId());
            return optional.map(partner ->{
                        partner.setName(partnerApiRequest.getName());
                        partner.setStatus(partnerApiRequest.getStatus());
                        partner.setAddress(partnerApiRequest.getAddress());
                        partner.setCallCenter(partnerApiRequest.getCallCenter());
                        partner.setBusinessNumber(partnerApiRequest.getBusinessNumber());
                        partner.setCeoName(partnerApiRequest.getCeoName());
                        return partner;
                    }).map(partner -> baseRepository.save(partner))
                    .map(partner -> response(partner))
                    .map(Header::OK)
                    .orElseGet(() -> Header.ERROR("데이터없음"));
        }

        @Override
        public Header delete(Long id) {
            Optional<Partner> optional = baseRepository.findById(id);
            return optional.map(partner -> {
                baseRepository.delete(partner);
                return Header.OK();
            }).orElseGet(()->Header.ERROR("노 데이터"));
        }

        public PartnerApiResponse response(Partner partner){
            PartnerApiResponse partnerApiResponse = PartnerApiResponse.builder()
                    .id(partner.getId())
                    .name(partner.getName())
                    .status(partner.getStatus())
                    .address(partner.getAddress())
                    .callCenter(partner.getCallCenter())
                    .businessNumber(partner.getBusinessNumber())
                    .ceoName(partner.getCeoName())
                    .regDate(partner.getRegDate())
                    .build();
            return partnerApiResponse;
        }

        public Header<List<PartnerApiResponse>> search(Pageable pageable){
            Page<Partner> partner = baseRepository.findAll(pageable);
            List<PartnerApiResponse> partnerApiResponseList = partner.stream()
                    .map(partners -> response(partners))
                    .collect(Collectors.toList());
            Pagination pagination = Pagination.builder()
                    .totalPages(partner.getTotalPages())
                    .totalElements(partner.getTotalElements())
                    .currentPage(partner.getNumber())
                    .currentElements(partner.getNumberOfElements())
                    .build();
            return Header.OK(partnerApiResponseList, pagination);
        }
    }
