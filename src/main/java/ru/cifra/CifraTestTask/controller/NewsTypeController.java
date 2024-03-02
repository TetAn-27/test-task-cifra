package ru.cifra.CifraTestTask.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.cifra.CifraTestTask.model.newsType.NewsTypeDto;
import ru.cifra.CifraTestTask.service.newsType.NewsTypeService;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping(path = "/type")
public class NewsTypeController {

    private final NewsTypeService newsTypeService;

    public NewsTypeController(NewsTypeService newsTypeService) {
        this.newsTypeService = newsTypeService;
    }

    @PostMapping()
    public ResponseEntity<NewsTypeDto> createNewsType(@RequestBody @Valid NewsTypeDto newsTypeDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(newsTypeService.createNewsType(newsTypeDto).get());
    }

    @GetMapping()
    public List<NewsTypeDto> getAllNewsType(@PositiveOrZero @RequestParam(value = "from", defaultValue = "0",
                                             required = false) int page,
                                            @Positive @RequestParam(value = "size", defaultValue = "10",
                                             required = false) int size) {
        PageRequest pageRequest = PageRequest.of(page / size, size, Sort.Direction.ASC, "id");
        return newsTypeService.getAllNewsType(pageRequest);
    }

    @PatchMapping("/{typeId}")
    @Valid
    public ResponseEntity<NewsTypeDto> updateNewsType(@PathVariable(value = "typeId") Long typeId,
                                                      @RequestBody @Valid NewsTypeDto.NewsTypeDtoUpdate newsTypeDto) {
        return ResponseEntity.of(newsTypeService.updateNewsType(typeId, newsTypeDto));
    }

    @DeleteMapping("/{typeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNewsType(@PathVariable("typeId") Long typeId) {
        newsTypeService.deleteNewsType(typeId);
    }
}
