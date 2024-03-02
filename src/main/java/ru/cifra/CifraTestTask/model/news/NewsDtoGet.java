package ru.cifra.CifraTestTask.model.news;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.cifra.CifraTestTask.model.newsType.NewsType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsDtoGet {
    private String name;
    private String shortDescription;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private NewsType newsType;
}
