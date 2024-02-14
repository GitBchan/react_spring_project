package org.zerock.mallapi.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.mallapi.dto.PageRequestDTO;
import org.zerock.mallapi.dto.PageResponseDTO;
import org.zerock.mallapi.dto.ProductDTO;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class ProductServiceImplTest {
    @Autowired
    private ProductService productService;

    @Test
    public void testList() throws Exception{
        //given
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        PageResponseDTO<ProductDTO> result = productService.getList(pageRequestDTO);

        result.getDtoList().forEach(dto -> log.info(dto));
    }

    @Test
    public void testRegister() throws Exception{
        //given
        ProductDTO productDTO = ProductDTO.builder()
                .pname("새로운 상품")
                .pdesc("신규 추가 상품입니다.")
                .price(1000)
                .build();

        productDTO.setUploadFileNames(
                List.of(UUID.randomUUID() + "_" + "TEST1.jpg",
                UUID.randomUUID().toString() + "_" + "TEST2.jpg")
        );

        productService.register(productDTO);
    }

    @Test
    public void testRead() throws Exception{
        //given
        Long pno = 11L;

        ProductDTO productDTO = productService.get(pno);

        log.info(productDTO);
        log.info(productDTO.getUploadFileNames());
    }

}