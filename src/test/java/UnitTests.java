package test.java;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;

// Пометка и фильтрация тестов https://www.baeldung.com/junit-filtering-tests
// https://junit.org/junit5/docs/current/user-guide/index.html#running-tests-junit-platform-runner-test-suite
// https://howtodoinjava.com/junit5/junit-5-tag-annotation-example/
@RunWith(JUnitPlatform.class)
@SuiteDisplayName("Набор Unit-тестов")
@SelectPackages("test.java")
@IncludeTags("UnitTest")
public class UnitTests {
}
