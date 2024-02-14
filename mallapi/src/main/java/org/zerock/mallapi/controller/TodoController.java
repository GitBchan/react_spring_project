package org.zerock.mallapi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.zerock.mallapi.dto.PageRequestDTO;
import org.zerock.mallapi.dto.PageResponseDTO;
import org.zerock.mallapi.dto.TodoDTO;
import org.zerock.mallapi.service.TodoService;

import java.util.Map;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class TodoController {
    private final TodoService todoService;

    @GetMapping("/{tno}")
    public TodoDTO get(@PathVariable(name = "tno") Long tno) {
        return todoService.get(tno);
    }

    @GetMapping("/list")
    public PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO) {
        log.info(pageRequestDTO);

        return todoService.list(pageRequestDTO);
    }

    @PostMapping("/")
    public Map<String, Long> register(@RequestBody TodoDTO todoDTO) {
        log.info("TodoDTO", todoDTO);

        Long tno = todoService.register(todoDTO);

        return Map.of("TNO", tno);
    }

    @PutMapping("/{tno}")
    public Map<String, String> modify(@RequestBody TodoDTO todoDTO, @PathVariable("tno")Long tno) {
        todoDTO.setTno(tno);

        log.info("Modify : " + todoDTO);

        todoService.modify(todoDTO);

        return Map.of("RESULT", "SUCCESS");
    }

    @DeleteMapping("/{tno}")
    public Map<String, String> remove(@PathVariable("tno") Long tno) {
        log.info("Remove : " + tno);

        todoService.remove(tno);

        return Map.of("RESULT", "SUCCESS");
    }
}
