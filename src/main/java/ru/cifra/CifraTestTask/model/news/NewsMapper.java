package ru.cifra.CifraTestTask.model.news;

import ru.cifra.CifraTestTask.model.newsType.NewsType;

import java.util.ArrayList;
import java.util.List;

public class NewsMapper {

    public static NewsDto toNewsDto(News news) {
        return new NewsDto(
                news.getName(),
                news.getShortDescription(),
                news.getDescription(),
                news.getNewsType().getId()
        );
    }

    public static NewsDtoGet toNewsDtoGet(News news) {
        return new NewsDtoGet(
                news.getName(),
                news.getShortDescription(),
                news.getNewsType()
        );
    }

    public static News toNews(NewsDto newsDto, NewsType newsType) {
        return new News(
                0,
                newsDto.getName(),
                newsDto.getShortDescription(),
                newsDto.getDescription(),
                newsType
        );
    }

    public static List<NewsDtoGet> toListNewsDto(List<News> newsList) {
        List<NewsDtoGet> newsDtoList = new ArrayList<>();
        for (News news : newsList) {
            newsDtoList.add(toNewsDtoGet(news));
        }
        return newsDtoList;
    }
}
