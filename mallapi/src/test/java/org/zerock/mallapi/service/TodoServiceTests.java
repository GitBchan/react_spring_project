package org.zerock.mallapi.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.mallapi.dto.PageRequestDTO;
import org.zerock.mallapi.dto.PageResponseDTO;
import org.zerock.mallapi.dto.TodoDTO;

import java.time.LocalDate;

@SpringBootTest
@Log4j2
public class TodoServiceTests {

    @Autowired
    private TodoService todoService;

    @Test
    public void testRegister() throws Exception{
        //given
        TodoDTO todoDTO = TodoDTO.builder()
                .title("service test")
                .writer("tester")
                .dueDate(LocalDate.of(2023,10,10))
                .build();

        //then
        Long tno = todoService.register(todoDTO);

        log.info(tno);
    }

    @Test
    public void testGet() throws Exception{
        //given
        Long tno = 100L;
        //then
        TodoDTO todoDTO = todoService.get(tno);

        log.info(todoDTO);
    }
    @Test
    public void testModify() throws Exception{
        //given
        TodoDTO todoDTO = TodoDTO.builder()
                .title("Modified title")
                .dueDate(LocalDate.of(2024,01,28))
                .complete(true)
                .tno(100L)
                .build();
        //then
        todoService.modify(todoDTO);
    }

    @Test
    public void testDelete() throws Exception{
        //given
        Long tno = 99L;
        //then
        todoService.remove(tno);
    }

    @Test
    public void testList() throws Exception{
        //given
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(2)
                .size(10)
                .build();
        //then
        PageResponseDTO<TodoDTO> response = todoService.list(pageRequestDTO);
        log.info(response);
    }
}
