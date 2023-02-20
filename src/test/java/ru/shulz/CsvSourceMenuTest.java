package ru.shulz;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Selenide.*;

public class CsvSourceMenuTest {

    //@CsvSource @MethodSource и @ValueSource

    @BeforeEach
    void openPage() {
        Configuration.holdBrowserOpen = true;
        Configuration.timeout = 30000;
        open("https://onlinesemena.ru/");
    }

    @CsvSource({
            "Личный кабинет",
            "Заказы",
            "Избранное",
            "Корзина",
    })

    @ParameterizedTest(name = "Проверка наличия кнопок-ссылок в шапке меню")
    @Tag("Critical")
    void checkHeaderModuleLinks(String link) {
        $$(".nheader-content-bottom>.nheader-icons").shouldHave(CollectionCondition.texts(link));
    }
}
