package checker;

import java.util.ArrayList;
import java.util.List;

class TypeMatcherRegistry<RESULT> {
    private final List<TypeMatcherImpl<?, RESULT>> matchers = new ArrayList<>();

    void add(TypeMatcherImpl<?, RESULT> matcher) {
        matchers.add(matcher);
    }

    RESULT executeMatchers() {
        return matchers.stream()
                .filter(TypeMatcherImpl::matches)
                .findFirst()
                .map(TypeMatcherImpl::executeAction)
                .orElseThrow(NoApplicableMatcherException::new);
    }
}