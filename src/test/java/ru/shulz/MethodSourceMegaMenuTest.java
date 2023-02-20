package ru.shulz;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public class MethodSourceMegaMenuTest {
    @BeforeEach
    void menuOpen() {
        open("https://trudvsem.ru/");
        Configuration.holdBrowserOpen = true;
    }

    static Stream<Arguments> menuSearch() {
        return Stream.of(
                Arguments.of("Поиск работы", List.of("Поиск вакансий")),
                Arguments.of("Поиск работников", List.of("Поисковая выдача резюме"))
        );
    }

    @MethodSource
    @ParameterizedTest(name = "Проверка наличия кнопок из списка {1} " + ", на странице {0}")
    @Tag("BLOCKER")
    void menuSearch(String locale, List<String> buttons){
        $$(".mega-menu__link").find(text(locale)).click();
        $$(".breadcrumbs__item-link_active").filter(visible)
                .shouldHave(CollectionCondition.texts(buttons));
    }
}
