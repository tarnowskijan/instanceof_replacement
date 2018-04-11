package checker;

class TypeMatcherFactoryImpl<RESULT> implements TypeMatcherFactory<RESULT> {
    private final Object initialObject;
    private final TypeMatcherRegistry<RESULT> registry;

    TypeMatcherFactoryImpl(Object initialObject) {
        this.initialObject = initialObject;
        this.registry = new TypeMatcherRegistry<>();
    }

    TypeMatcherFactoryImpl(Object initialObject, TypeMatcherRegistry<RESULT> registry) {
        this.initialObject = initialObject;
        this.registry = registry;
    }

    @Override
    public <T> TypeMatcherImpl<T, RESULT> is(Class<T> clazz) {
        TypeMatcherImpl<T, RESULT> matcher = new TypeMatcherImpl<>(clazz, initialObject, registry);
        registry.add(matcher);
        return matcher;
    }

    @Override
    public RESULT execute() {
        return registry.executeMatchers();
    }
}