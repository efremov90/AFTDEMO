package test.java;

import main.java.GenderType;
import main.java.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    private final Person person = new Person("FirstName", "LastName", GenderType.MALE);

    @Test
    @DisplayName("Проверка методов getter и setter")
    void TestGetterAndSetter(TestInfo testInfo) {
        System.out.println(testInfo.toString());
        person.setFirstName("Александр");
        person.setLastName("Ефремов");
        assertAll("person", /*заголовок необязателен*/
                () -> assertEquals("Александр", person.getFirstName(),"сообщение при ошибке"),
                () -> assertEquals("Ефремов", person.getLastName())
        );
    }

    @Test
    @DisplayName("Пример dependentAssertions")
    void dependentAssertions() {
        // Within a code block, if an assertion fails the
        // subsequent code in the same block will be skipped.
        assertAll("properties",
                () -> {
                    String firstName = person.getFirstName();
                    assertNotNull(firstName);

                    // Executed only if the previous assertion is valid.
                    assertAll("first name",
                            () -> assertTrue(firstName.startsWith("F")),
                            () -> assertTrue(firstName.endsWith("e"))
                    );
                },
                () -> {
                    // Grouped assertion, so processed independently
                    // of results of first name assertions.
                    String lastName = person.getLastName();
                    assertNotNull(lastName);

                    // Executed only if the previous assertion is valid.
                    assertAll("last name",
                            () -> assertTrue(lastName.startsWith("L")),
                            () -> assertTrue(lastName.endsWith("e"))
                    );
                }
        );
    }

}