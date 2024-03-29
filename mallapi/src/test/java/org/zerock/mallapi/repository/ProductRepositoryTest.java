package org.zerock.mallapi.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mallapi.domain.Product;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testInsert() throws Exception{
        //given
        for (int i = 0; i < 10; i++) {
            Product product = Product.builder()
                    .pname("상품" + i)
                    .price(100 * i)
                    .pdesc("상품설명" + i)
                    .build();

            product.addImageString(UUID.randomUUID().toString() + "_" + "IMAGE1.jpg");
            product.addImageString(UUID.randomUUID().toString() + "_" + "IMAGE2.jpg");
            product.addImageString(UUID.randomUUID().toString() + "_" + "IMAGE3.jpg");

            productRepository.save(product);

            log.info("-------------------------------------");
        }
    }

    @Test
    @Transactional
    public void testRead() throws Exception{
        //given
        Long pno = 1L;
        //when

        //then
        Product product = productRepository.findById(pno).orElseThrow();

        log.info(product);

        log.info(product.getImageList());
    }

    @Test
    public void testRead2() throws Exception{
        //given
        Long pno = 1L;
        //when

        //then
        Product product = productRepository.selectOne(pno).orElseThrow();

        log.info(product);
        log.info(product.getImageList());
    }

    @Test
    @Transactional
    @Commit
    public void testDelete() throws Exception{
        //given
        Long pno = 2L;
        //when

        //then
        productRepository.updateToDelete(pno, true);
    }

    @Test
    public void testUpdate() throws Exception{
        //given
        Long pno = 10L;
        //when
        Product product = productRepository.selectOne(pno).get();

        product.changeName("10번 상품");
        product.changeDesc("10번 상품 설명입니다.");
        product.changePrice(5000);

        product.clearList();

        product.addImageString(UUID.randomUUID().toString() + "_" + "NEWIMAGE1.jpg");
        product.addImageString(UUID.randomUUID().toString() + "_" + "NEWIMAGE2.jpg");
        product.addImageString(UUID.randomUUID().toString() + "_" + "NEWIMAGE3.jpg");
        //then
        productRepository.save(product);
    }

    @Test
    public void testList() throws Exception{
        //given
        Pageable pageable = PageRequest.of(0, 10, Sort.by("pno").descending());

        Page<Object[]> result = productRepository.selectList(pageable);

        //then
        result.getContent().forEach(arr -> log.info(Arrays.toString(arr)));
    }
}