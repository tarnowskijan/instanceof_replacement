package checker;

public class TypeChecker {

    public static <T> TypeMatcherFactory<T> whenTypeOf(Object object) {
        return new TypeMatcherFactoryImpl<>(object);
    }
}