package ru.cifra.CifraTestTask.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.cifra.CifraTestTask.model.news.News;

public interface NewsRepository extends JpaRepository<News, Long> {
    News save(News news);
    Page<News> findAll(Pageable page);
    Page<News> findAllByNewsTypeId(long newsTypeId, Pageable page);
    void deleteById(long newsId);
    News getById(long newsId);
    News findFirstByNewsTypeId(long typeId);
}
