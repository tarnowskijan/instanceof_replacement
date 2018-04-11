package checker;

import java.util.Objects;
import java.util.function.Function;

class TypeMatcherImpl<TYPE, RESULT> implements TypeMatcher<TYPE, RESULT> {
    private final Class<TYPE> clazz;
    private final Object initialObject;
    private final TypeMatcherRegistry<RESULT> register;
    private Function<TYPE, RESULT> action;

    TypeMatcherImpl(Class<TYPE> clazz, Object initialObject, TypeMatcherRegistry<RESULT> register) {
        this.clazz = clazz;
        this.initialObject = initialObject;
        this.register = register;
    }

    @Override
    public TypeMatcherFactory<RESULT> thenReturn(Function<TYPE, RESULT> action) {
        this.action = Objects.requireNonNull(action);
        return new TypeMatcherFactoryImpl<>(initialObject, register);
    }

    boolean matches() {
        return this.clazz.isInstance(initialObject);
    }

    RESULT executeAction() {
        return action.apply(clazz.cast(initialObject));
    }
}