package ru.cifra.CifraTestTask.service.newsType;

import org.springframework.data.domain.PageRequest;
import ru.cifra.CifraTestTask.model.newsType.NewsTypeDto;

import java.util.List;
import java.util.Optional;

public interface NewsTypeService {
    Optional<NewsTypeDto> createNewsType(NewsTypeDto newsTypeDto);
    List<NewsTypeDto>  getAllNewsType(PageRequest pageRequest);
    Optional<NewsTypeDto> updateNewsType(long typeId, NewsTypeDto.NewsTypeDtoUpdate newsTypeDto);
    void deleteNewsType(long typeId);
}
