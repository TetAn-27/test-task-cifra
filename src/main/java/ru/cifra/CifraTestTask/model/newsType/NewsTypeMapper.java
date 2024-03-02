package ru.cifra.CifraTestTask.model.newsType;

import java.util.ArrayList;
import java.util.List;

public class NewsTypeMapper {

    public static NewsTypeDto toNewsTypeDto(NewsType newsType) {
        return new NewsTypeDto(
                newsType.getName(),
                newsType.getColor()
        );
    }

    public static NewsType toNewsType(NewsTypeDto newsTypeDto) {
        return new NewsType(
                0,
                newsTypeDto.getName(),
                newsTypeDto.getColor()
        );
    }

    public static List<NewsTypeDto> toListNewsTypeDto(List<NewsType> newsTypeList) {
        List<NewsTypeDto> newsTypeDtoList = new ArrayList<>();
        for (NewsType newsType : newsTypeList) {
            newsTypeDtoList.add(toNewsTypeDto(newsType));
        }
        return newsTypeDtoList;
    }
}
