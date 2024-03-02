package ru.cifra.CifraTestTask.service.newsType;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.cifra.CifraTestTask.exception.ConflictException;
import ru.cifra.CifraTestTask.exception.NotFoundException;
import ru.cifra.CifraTestTask.model.news.News;
import ru.cifra.CifraTestTask.model.newsType.NewsType;
import ru.cifra.CifraTestTask.model.newsType.NewsTypeDto;
import ru.cifra.CifraTestTask.model.newsType.NewsTypeMapper;
import ru.cifra.CifraTestTask.repository.NewsRepository;
import ru.cifra.CifraTestTask.repository.NewsTypeRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class NewsTypeServiceImpl implements NewsTypeService {

    private final NewsTypeRepository newsTypeRepository;
    private final NewsRepository newsRepository;

    public NewsTypeServiceImpl(NewsTypeRepository newsTypeRepository, NewsRepository newsRepository) {
        this.newsTypeRepository = newsTypeRepository;
        this.newsRepository = newsRepository;
    }

    @Override
    public Optional<NewsTypeDto> createNewsType(NewsTypeDto newsTypeDto) {
        NewsType newsType = NewsTypeMapper.toNewsType(newsTypeDto);
        log.debug("Тип новости создан: {}", newsType);
        return Optional.of(NewsTypeMapper.toNewsTypeDto(newsTypeRepository.save(newsType)));
    }

    @Override
    public List<NewsTypeDto> getAllNewsType(PageRequest pageRequestMethod) {
        Pageable page = pageRequestMethod;
        do {
            Page<NewsType> pageRequest = newsTypeRepository.findAll(page);
            pageRequest.getContent().forEach(ItemRequest -> {
            });
            if (pageRequest.hasNext()) {
                page = PageRequest.of(pageRequest.getNumber() + 1, pageRequest.getSize(), pageRequest.getSort());
            } else {
                page = null;
            }
            return NewsTypeMapper.toListNewsTypeDto(pageRequest.getContent());
        } while (page != null);
    }

    @Override
    public Optional<NewsTypeDto> updateNewsType(long typeId, NewsTypeDto.NewsTypeDtoUpdate newsTypeDto) {
        NewsType newsTypeOld = newsTypeRepository.getById(typeId);
        NewsType newsType = getUpdateNewsType(newsTypeOld, newsTypeDto);
        return Optional.of(NewsTypeMapper.toNewsTypeDto(newsTypeRepository.save(newsType)));
    }

    @Override
    public void deleteNewsType(long typeId) {
        try {
            News news = newsRepository.findFirstByNewsTypeId(typeId);
            if (news != null) {
                throw new ConflictException("Тип новости не может быть удален");
            }
            newsTypeRepository.deleteById(typeId);
        } catch (DataAccessException ex) {
            log.error("Типа новости с Id {} не найдено", typeId);
            throw new NotFoundException("Типа новости с таким Id не было найдено");
        }
    }

    private NewsType getUpdateNewsType(NewsType newsType, NewsTypeDto.NewsTypeDtoUpdate newsTypeUpdate) {
        newsType.setName(newsTypeUpdate.getName() != null ? newsTypeUpdate.getName() : newsType.getName());
        newsType.setColor(newsTypeUpdate.getColor() != null ? newsTypeUpdate.getColor() : newsType.getColor());
        return newsType;
    }
}
