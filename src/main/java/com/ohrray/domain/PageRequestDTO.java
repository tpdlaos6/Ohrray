package com.ohrray.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {

    private int page,size;

    private String type;
    private String keyword="";

    public PageRequestDTO(){
        this.page =1;
        this.size= 24;
    }

    public Pageable getPageble(Sort sort){
        return PageRequest.of(page-1,size,sort);
    }
}
