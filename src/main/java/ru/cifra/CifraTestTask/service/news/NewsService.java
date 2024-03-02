package ru.cifra.CifraTestTask.service.news;

import org.springframework.data.domain.PageRequest;
import ru.cifra.CifraTestTask.model.news.NewsDto;
import ru.cifra.CifraTestTask.model.news.NewsDtoGet;

import java.util.List;
import java.util.Optional;

public interface NewsService {
    Optional<NewsDto> createNews(NewsDto newsDto);
    List<NewsDtoGet> getAllNews(PageRequest pageRequest);
    List<NewsDtoGet> getAllNewsCertainType(long newsTypeId, PageRequest pageRequest);
    Optional<NewsDto> updateNews(long newsId, NewsDto.NewsDtoUpdate newsDto);
    void deleteNews(long newsId);
}
