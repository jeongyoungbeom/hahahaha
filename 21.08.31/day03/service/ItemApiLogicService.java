package com.koreait.day03.service;

import com.koreait.day03.ifs.CrudInterface;
import com.koreait.day03.model.entity.Category;
import com.koreait.day03.model.entity.Item;
import com.koreait.day03.model.enumclass.ItemStatus;
import com.koreait.day03.model.network.Header;
import com.koreait.day03.model.network.Pagination;
import com.koreait.day03.model.network.request.ItemApiRequest;
import com.koreait.day03.model.network.response.ItemApiResponse;
import com.koreait.day03.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemApiLogicService extends BaseService<ItemApiRequest, ItemApiResponse, Item> {

    @Override
    public Header<ItemApiResponse> create(Header<ItemApiRequest> request) {
        ItemApiRequest itemApiRequest = request.getData();
        Item item = Item.builder()
                .name(itemApiRequest.getName())
                .status(ItemStatus.REGISTERED)
                .title(itemApiRequest.getTitle())
                .content(itemApiRequest.getContent())
                .price(itemApiRequest.getPrice())
                .regDate(itemApiRequest.getRegDate())
                .createBy(itemApiRequest.getCreateBy())
                .updateDate(itemApiRequest.getUpdateDate())
                .updateBy(itemApiRequest.getUpdateBy())
                .build();
        Item items = baseRepository.save(item);
        return Header.OK(response(items));
    }

    @Override
    public Header<ItemApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(item -> response(item))
                .map(Header::OK)
                .orElseGet(
                        () -> Header.ERROR("데이터없음")
                );
    }

    @Override
    public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {
        ItemApiRequest itemApiRequest = request.getData();
        Optional<Item> optional =baseRepository.findById(itemApiRequest.getId());
        return optional.map(item ->{
            item.setName(itemApiRequest.getName());
            item.setStatus(ItemStatus.REGISTERED);
            item.setTitle(itemApiRequest.getTitle());
            item.setContent(itemApiRequest.getContent());
            item.setPrice(itemApiRequest.getPrice());

            return item;
        }).map(item -> baseRepository.save(item))
                .map(item -> response(item))
                .map(Header::OK)
                .orElseGet(()->Header.ERROR("ㄴㄴ"));
    }

    @Override
    public Header delete(Long id) {
        Optional<Item> optional = baseRepository.findById(id);
        return optional.map(item -> {
            baseRepository.delete(item);
            return Header.OK();
        }).orElseGet(()->Header.ERROR("노데이터"));
    }

    public ItemApiResponse response(Item item){
        ItemApiResponse itemApiResponse = ItemApiResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .status(item.getStatus())
                .title(item.getTitle())
                .content(item.getContent())
                .price(item.getPrice())
                .regDate(item.getRegDate())
                .build();
        return itemApiResponse;
    }

    public Header<List<ItemApiResponse>> search(Pageable pageable){
        Page<Item> item = baseRepository.findAll(pageable);
        List<ItemApiResponse> itemApiResponseList = item.stream()
                .map(items -> response(items))
                .collect(Collectors.toList());
        Pagination pagination = Pagination.builder()
                .totalPages(item.getTotalPages())
                .totalElements(item.getTotalElements())
                .currentPage(item.getNumber())
                .currentElements(item.getNumberOfElements())
                .build();
        return Header.OK(itemApiResponseList, pagination);
    }


}
