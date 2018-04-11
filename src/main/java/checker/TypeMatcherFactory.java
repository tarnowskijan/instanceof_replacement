package checker;

public interface TypeMatcherFactory<RESULT> {
    <TYPE> TypeMatcher<TYPE, RESULT> is(Class<TYPE> clazz);

    RESULT execute() throws NoApplicableMatcherException;
}