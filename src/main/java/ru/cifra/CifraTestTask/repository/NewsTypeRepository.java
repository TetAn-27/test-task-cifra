package ru.cifra.CifraTestTask.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.cifra.CifraTestTask.model.newsType.NewsType;

public interface NewsTypeRepository extends JpaRepository<NewsType, Long> {
    NewsType save(NewsType newsType);
    Page<NewsType> findAll(Pageable page);
    void deleteById(long newsTypeId);
}
