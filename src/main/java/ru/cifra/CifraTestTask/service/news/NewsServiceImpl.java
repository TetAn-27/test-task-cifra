package ru.cifra.CifraTestTask.service.news;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.cifra.CifraTestTask.exception.NotFoundException;
import ru.cifra.CifraTestTask.model.news.News;
import ru.cifra.CifraTestTask.model.news.NewsDto;
import ru.cifra.CifraTestTask.model.news.NewsDtoGet;
import ru.cifra.CifraTestTask.model.news.NewsMapper;
import ru.cifra.CifraTestTask.model.newsType.NewsType;
import ru.cifra.CifraTestTask.repository.NewsRepository;
import ru.cifra.CifraTestTask.repository.NewsTypeRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final NewsTypeRepository newsTypeRepository;

    public NewsServiceImpl(NewsRepository newsRepository, NewsTypeRepository newsTypeRepository) {
        this.newsRepository = newsRepository;
        this.newsTypeRepository = newsTypeRepository;
    }

    @Override
    public Optional<NewsDto> createNews(NewsDto newsDto) {
        NewsType newsType;
        long newsTypeId = newsDto.getNewsTypeId();
        try {
            newsType = newsTypeRepository.getById(newsTypeId);
        } catch (EntityNotFoundException ex) {
            log.error("Тип новости с Id {} не найден", newsTypeId);
            throw new NotFoundException("Тип новости с таким Id не было найдено");
        }
        News news = NewsMapper.toNews(newsDto, newsType);
        log.debug("Новость создана: {}", news);
        return Optional.of(NewsMapper.toNewsDto(newsRepository.save(news)));
    }

    @Override
    public List<NewsDtoGet> getAllNews(PageRequest pageRequestMethod) {
        Pageable page = pageRequestMethod;
        do {
            Page<News> pageRequest = newsRepository.findAll(page);
            pageRequest.getContent().forEach(ItemRequest -> {
            });
            if (pageRequest.hasNext()) {
                page = PageRequest.of(pageRequest.getNumber() + 1, pageRequest.getSize(), pageRequest.getSort());
            } else {
                page = null;
            }
            return NewsMapper.toListNewsDto(pageRequest.getContent());
        } while (page != null);
    }

    @Override
    public List<NewsDtoGet> getAllNewsCertainType(long newsTypeId, PageRequest pageRequestMethod) {
        Pageable page = pageRequestMethod;
        do {
            Page<News> pageRequest = newsRepository.findAllByNewsTypeId(newsTypeId, page);
            pageRequest.getContent().forEach(ItemRequest -> {
            });
            if (pageRequest.hasNext()) {
                page = PageRequest.of(pageRequest.getNumber() + 1, pageRequest.getSize(), pageRequest.getSort());
            } else {
                page = null;
            }
            return NewsMapper.toListNewsDto(pageRequest.getContent());
        } while (page != null);
    }

    @Override
    public Optional<NewsDto> updateNews(long newsId, NewsDto.NewsDtoUpdate newsDto) {
        News newsOld = newsRepository.getById(newsId);
        News news = getUpdateNews(newsOld, newsDto);
        return Optional.of(NewsMapper.toNewsDto(newsRepository.save(news)));
    }

    @Override
    public void deleteNews(long newsId) {
        try {
            newsRepository.deleteById(newsId);
        } catch (DataAccessException ex) {
            log.error("News с ID {} не была найдена", newsId);
            throw new NotFoundException("News с таким Id не был найден");
        }
    }

    private News getUpdateNews(News news, NewsDto.NewsDtoUpdate newsUpdate) {
        news.setName(newsUpdate.getName() != null ? newsUpdate.getName() : news.getName());
        news.setShortDescription(newsUpdate.getName() != null ? newsUpdate.getShortDescription() : news.getShortDescription());
        news.setDescription(newsUpdate.getDescription() != null ? newsUpdate.getDescription() : news.getDescription());
        news.setNewsType(newsUpdate.getNewsTypeId() != null ? newsTypeRepository.getById(newsUpdate.getNewsTypeId()) : news.getNewsType());
        return news;
    }
}
