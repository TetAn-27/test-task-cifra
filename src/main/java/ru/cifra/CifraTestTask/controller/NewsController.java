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
import ru.cifra.CifraTestTask.model.news.NewsDto;
import ru.cifra.CifraTestTask.model.news.NewsDtoGet;
import ru.cifra.CifraTestTask.service.news.NewsService;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping(path = "/news")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @PostMapping()
    public ResponseEntity<NewsDto> createNews(@RequestBody @Valid NewsDto newsDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(newsService.createNews(newsDto).get());
    }

    @GetMapping()
    public List<NewsDtoGet> getAllNews(@PositiveOrZero @RequestParam(value = "from", defaultValue = "0",
                                             required = false) int page,
                                       @Positive @RequestParam(value = "size", defaultValue = "10",
                                             required = false) int size) {
        PageRequest pageRequest = PageRequest.of(page / size, size, Sort.Direction.ASC, "id");
        return newsService.getAllNews(pageRequest);
    }

    @GetMapping("/{newsTypeId}")
    public List<NewsDtoGet> getAllNewsCertainType(@PathVariable(value = "newsTypeId") Long newsTypeId,
                                       @PositiveOrZero @RequestParam(value = "from", defaultValue = "0",
            required = false) int page,
                                       @Positive @RequestParam(value = "size", defaultValue = "10",
                                               required = false) int size) {
        PageRequest pageRequest = PageRequest.of(page / size, size, Sort.Direction.ASC, "id");
        return newsService.getAllNewsCertainType(newsTypeId, pageRequest);
    }

    @PatchMapping("/{newsId}")
    @Valid
    public ResponseEntity<NewsDto> updateNews(@PathVariable(value = "newsId") Long newsId,
                                              @RequestBody @Valid NewsDto.NewsDtoUpdate newsDto) {
        return ResponseEntity.of(newsService.updateNews(newsId, newsDto));
    }

    @DeleteMapping("/{newsId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNews(@PathVariable("newsId") Long newsId) {
        newsService.deleteNews(newsId);
    }
}
