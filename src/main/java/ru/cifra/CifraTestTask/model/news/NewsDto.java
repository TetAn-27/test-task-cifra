package ru.cifra.CifraTestTask.model.news;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsDto {
    @NotNull(message = "поле name является обязательным")
    @NotBlank(message = "поле name не может состоять из пробелов")
    @NotEmpty(message = "поле name не может быть пустым")
    @Size(min = 2, max = 250)
    private String name;
    @NotNull(message = "поле shortDescription является обязательным")
    @NotBlank(message = "поле shortDescription не может состоять из пробелов")
    @NotEmpty(message = "поле shortDescription не может быть пустым")
    @Size(min = 20, max = 1000)
    private String shortDescription;
    @NotNull(message = "поле description является обязательным")
    @NotBlank(message = "поле description не может состоять из пробелов")
    @NotEmpty(message = "поле description не может быть пустым")
    @Size(min = 100, max = 7000)
    private String description;
    @NotNull(message = "поле newsTypeId является обязательным")
    private Long newsTypeId;

    @Data
    @AllArgsConstructor
    public static class NewsDtoUpdate {
        @Size(min = 2, max = 250)
        private String name;
        @Size(min = 20, max = 1000)
        private String shortDescription;
        @Size(min = 100, max = 7000)
        private String description;
        private Long newsTypeId;
    }
}
