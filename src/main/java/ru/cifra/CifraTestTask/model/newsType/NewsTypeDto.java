package ru.cifra.CifraTestTask.model.newsType;

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
public class NewsTypeDto {
    @NotNull(message = "поле name является обязательным")
    @NotBlank(message = "поле name не может состоять из пробелов")
    @NotEmpty(message = "поле name не может быть пустым")
    @Size(min = 2, max = 100)
    private String name;
    @NotNull(message = "поле color является обязательным")
    @NotBlank(message = "поле color не может состоять из пробелов")
    @NotEmpty(message = "поле color не может быть пустым")
    @Size(min = 2, max = 50)
    private String color;

    @Data
    @AllArgsConstructor
    public static class NewsTypeDtoUpdate {
        @Size(min = 2, max = 100)
        private String name;
        @Size(min = 2, max = 50)
        private String color;
    }
}
