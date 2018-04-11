import spock.lang.*

import static checker.TypeChecker.whenTypeOf

class TypeCheckerTest extends Specification {

    def "TypeChecker should check type and map correctly"() {
        when: "map string '123' to it's length"
        def length = whenTypeOf("123")
                .is(String.class).thenReturn({ s -> s.length() })
                .execute()
        then: "length should be 3"
        length == 3
    }

    def "TypeChecker should chose first match"() {
        when: "has more than one possible match"
        def v = whenTypeOf("123")
                .is(String.class).thenReturn({ s -> "FIRST" + s })
                .is(String.class).thenReturn({ s -> "SECOND" + s })
                .execute()
        then: "should chose first match"
        v == "FIRST123"
    }

    def "TypeChecker execute operations lazy"() {
        when: "map without class match"
        def oper = whenTypeOf("123")
                .is(Integer.class).thenReturn({ i -> i + 1 })
                .is(StringBuffer.class).thenReturn({ s -> s.length() })
        then: "operation should pass"
        when: "map and execute without class match"
        oper.execute()
        then: "operation should fail"
        thrown Throwable
    }

    class A {}

    class B extends A {}

    def "TypeChecker should match parent class types"() {
        when: "base class type is checked"
        def className = whenTypeOf(new B())
                .is(A.class).thenReturn({ c -> "A" })
                .execute()
        then: "should match base class"
        className == "A"
    }
}