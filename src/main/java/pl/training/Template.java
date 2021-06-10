package pl.training;

import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

public class Template {

    private static final String EXPRESSION_START = "\\$\\{";
    private static final String EXPRESSION_END = "}";
    private static final Pattern EXPRESSION_REGEXP = Pattern.compile("\\$\\{\\w+}");
    private static final String INVALID_VALUE = ".*\\W+.*";

    private final String text;

    public Template(String text) {
        this.text = text;
        validateText();
    }

    private void validateText() {
        if (getExpressions().map(MatchResult::group).collect(toSet()).size() != getExpressions().count()) {
            throw new IllegalArgumentException();
        }
    }

    public String evaluate(Map<String, String> data) {
        validateData(data);
        return substituteData(data);
    }

    private void validateData(Map<String, String> data) {
        if (isDataIncomplete(data) || isDataInvalid(data)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isDataIncomplete(Map<String, String> data) {
        return data.size() != getExpressions().count();
    }

    private boolean isDataInvalid(Map<String, String> data) {
        return data.values().stream().anyMatch(value -> value.matches(INVALID_VALUE));
    }

    private Stream<MatchResult> getExpressions() {
        return  EXPRESSION_REGEXP.matcher(text).results();
    }

    private String substituteData(Map<String, String> data) {
        var result = text;
        for (Map.Entry<String, String> entry: data.entrySet()) {
            var expression = EXPRESSION_START + entry.getKey() + EXPRESSION_END;
            result = result.replaceAll(expression, entry.getValue());
        }
        return result;
    }

}
