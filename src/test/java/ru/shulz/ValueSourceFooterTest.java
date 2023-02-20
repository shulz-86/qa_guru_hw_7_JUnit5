package ru.shulz;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class ValueSourceFooterTest {
    @BeforeEach
    void menuOpen() {
        open("https://trudvsem.ru/");
        Configuration.holdBrowserOpen = true;
    }

    @ValueSource(strings = {
            "Работодатели",
            "Соискатели",
            "Полезные ресурсы",
            "Общая информация"
    })

    @ParameterizedTest(name = "Поиск раздела {0} на сайте trudvsem.ru")
    @Tag("BLOCKER")
    void footerValueTest(String value) {
        $$("footer__wrapper").find(text(value));
    }
}
