package test.java;

import main.java.Calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import javax.swing.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static java.lang.Thread.sleep;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.junit.jupiter.api.condition.OS.WINDOWS;

// Создание JUnit теста см. https://www.youtube.com/watch?v=VFDi6AA6fY4
// p.s. уже создан: ctrl+shift+f CalculatorTest
// запуск осуществляет при вставании на папку test.java и выбора через контекстное меню Run (Ctrl+Shift+F10)
// запускаются все тесты этой папки
// для запуска конкретного, нужно выбирать только его

// Объявляет настраиваемое отображаемое имя для класса теста или метода теста.
// Если не обявлять, то будет отражаться английское наименованик класса, метода теста, например ignoredTest
@DisplayName("Тестирование калькулятора")

// https://junit.org/junit5/docs/current/user-guide/index.html#writing-tests-test-execution-order
// Чтобы контролировать порядок выполнения тестовых методов,
// пометьте свой тестовый класс или интерфейс тестирования с помощью
// OrderAnnotation: сортирует методы испытаний численно на основе значений, указанных в @Orderаннотации.
@org.junit.jupiter.api.TestMethodOrder(MethodOrderer.OrderAnnotation.class)

// https://www.baeldung.com/junit-testinstance-annotation
// https://junit.org/junit5/docs/current/user-guide/index.html#writing-tests-test-instance-lifecycle
// https://qarchive.ru/16521430_kakaja_pol_za_ot_annotatsii__testinstance_v_junit_5_ комментарий 2dyVeloper [2018-09-28 13:30:00]
//@org.junit.jupiter.api.TestInstance(TestInstance.Lifecycle.PER_CLASS)

//    ОПИСАНИЕ КЛАССА
class CalculatorTest {

    private Calculator calculator;

//  метод вызывающийся один раз для класса перед выполнением тестовых методов;
//  здесь можно разместить инициализацию которую нужно выполнять только один раз,
//  например, прочитать данные, которые будут использоваться в тестовых методах или создать соединение с базой данных
//  BeforeAll и tearDownAll должны быть static если тестовый класс не помечен @TestInstance(Lifecycle.PER_CLASS)
//  Если BeforeAll static, то переменные, инциированные в нем, не будут видным тестовым методам и их
//  нужно инициализировать либо в самом начале тестового класса
//  либо в BeforeEach, потому что он не static
    @org.junit.jupiter.api.BeforeAll
    static void initAll () {
        System.out.println("BeforeAll");
    }

//  метод, вызывающийся перед каждым тестовым методом в тестовом классе;
//  здесь можно выполнить необходимую инициализацию,
//  например, выставить начальные параметры
    @org.junit.jupiter.api.BeforeEach
    void init() {
        calculator = new Calculator();
        System.out.println("BeforeEach");
    }

//  метод является тестовым
    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Тест с одним assertEquals")
    // Пометка и фильтрация тестов, см. описание в классах UnitTests, IntegrationTests
    @Tag("UnitTest")
    @Order(2)
    void addTestOneAssertEquals() {
        double actual = calculator.divide(3,1);
//      если expected сделать 8, то будет ошибка
        double excpected = 3.0;
        assertEquals(excpected, actual, "Test message");
        System.out.println("Completed addTestOneAssertEquals");
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Тест с несколькими assertEquals")
    @Tag("IntegrationTest")
    @Order(1)
    void addTestSomeAssertEquals() {
        assertEquals(3.0, calculator.divide(3,1));
//      если не отработает предыдущий, последующие не вызываются
        assertEquals(-3.0, calculator.divide(-3,1));
        assertEquals(-3.0, calculator.divide(3,-1));
        assertEquals(0.0, calculator.divide(0,3));
        System.out.println("Completed addTestSomeAssertEquals");
    }

    @TestFactory
    @DisplayName("TestFactory with some assertEquals")
    Collection<DynamicTest> dynamicTestsFromCollection() {
        return Arrays.asList(
                dynamicTest("1st dynamic test", () -> assertEquals(3.0, calculator.divide(3,1))),
                dynamicTest("2nd dynamic test", () -> assertEquals(-3.0, calculator.divide(-3,1))),
                dynamicTest("3nd dynamic test", () -> assertEquals(-3.0, calculator.divide(3,-1))),
                dynamicTest("4nd dynamic test", () -> assertEquals(0.0, calculator.divide(0,3)))
        );
    }

//    пример https://www.petrikainulainen.net/programming/testing/junit-5-tutorial-writing-parameterized-tests/
    @ParameterizedTest
    @DisplayName("ParameterizedTest with CsvSource")
    @CsvSource({
            "3, 1, 3.0",
            "-3, 1, -3.0",
            "3, -1, -3.0",
            "0, 3, 0.0"
    })
    void TestParameterizedTestCsvSource(int a, int b, double expected) {
        assertEquals(expected, calculator.divide(a,b));
    }

    @ParameterizedTest
    @DisplayName("ParameterizedTest with CsvFileSource")
    @CsvFileSource(resources = "/DataTestParameterizedTestCsvFileSource.csv")
    void TestParameterizedTestCsvFileSource(int a, int b, double expected) {
        assertEquals(expected, calculator.divide(a,b));
    }

    @org.junit.jupiter.api.Test
    // можно указывать количество повторений. практический пример не нашел
    @org.junit.jupiter.api.RepeatedTest(2)
    @org.junit.jupiter.api.DisplayName("Тест с тайм-аутом")
//    https://junit.org/junit5/docs/current/user-guide/index.html#writing-tests-declarative-timeouts
    @org.junit.jupiter.api.Timeout(value = 10, unit = MILLISECONDS)
//    Если не прописать throws, то при компиляции возникает ошибка
//    Error:(74, 14) java: unreported exception java.lang.InterruptedException;
//    must be caught or declared to be thrown
    void TestTimeOut() throws InterruptedException {
//      Если sleep сделать 20, то будет ошибка
        sleep(1);
        System.out.println("Completed addTestTimeOut");
    }

//    @org.junit.jupiter.api.Test
//    @org.junit.jupiter.api.DisplayName("Тест проверки отработки исключения")
//    void FailingTest() {
//        calculator.divide(1, 0);
//        System.out.println("Completed FailingTest");
//    }

    @Test
    @DisplayName("Тест проверки отработки исключения")
    void ExceptionTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                calculator.divide(1, 0));
        assertEquals("Ошибка: Деление на 0.", exception.getMessage());
        System.out.println("Completed ExceptionTest");
    }

//  можно выполнять/не выполнять Enable/Disable тестовый класс/метод в зависимости от различных условий
//    https://junit.org/junit5/docs/current/user-guide/index.html#writing-tests-conditional-execution
//    https://www.baeldung.com/junit-5-conditional-test-execution
    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.condition.DisabledOnOs(WINDOWS)
    @org.junit.jupiter.api.DisplayName("Тест проверки отработки @DisabledOnOs")
    void DisabledOnOsTest() {
        System.out.println("Completed DisabledOnOs");
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.condition.DisabledOnJre(JRE.JAVA_13)
    @org.junit.jupiter.api.DisplayName("Тест проверки отработки @DisabledOnJre")
    void DisabledOnJreTest() {
        System.out.println("Completed DisabledOnJre");
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.condition.DisabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
    @org.junit.jupiter.api.DisplayName("Тест проверки отработки @DisabledIfSystemProperty")
    void DisabledIfSystemPropertyTest() {
        System.out.println("Completed DisabledIfSystemProperty");
    }

//  метод, вызывающийся после каждого тестового метода в тестовом классе;
//  здесь можно выполнить необходимую деинициализацию,
//  например, удалить данные,которые больше не нужны
    @org.junit.jupiter.api.AfterEach
    void tearDown () {
        calculator = null;
        System.out.println("AfterEach");
    }

//   метод вызывающийся один раз для класса после выполнения тестовых методов;
//   здесь можно разместить деинициализацию которую нужно выполнять только один раз,
//   например, закрыть соединение с базой данных или удалить данные, которые больше не нужны
    @org.junit.jupiter.api.AfterAll
    static void tearDownAll () {
        System.out.println("AfterAll");
    }

//  игнорировать тестовый метод, например, если нет времени писать или исправлять, но не забыть на будущее
    @org.junit.jupiter.api.Disabled("Message for ignored test")
    @org.junit.jupiter.api.Test
    public void ignoredTest() {
        System.out.println("will not print it");
    }
}