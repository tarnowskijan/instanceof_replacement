package checker;

import java.util.function.Function;

public interface TypeMatcher<TYPE, RESULT> {
    TypeMatcherFactory<RESULT> thenReturn(Function<TYPE, RESULT> action);
}