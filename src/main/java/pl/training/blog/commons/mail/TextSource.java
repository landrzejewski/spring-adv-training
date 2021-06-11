package pl.training.blog.commons.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TextSource {

    private static final String LANGUAGE_SEPARATOR = "_";

    private final TemplateEngine templateEngine;
    private final Set<String> availableLanguages;

    public String build(String baseTemplateName, Map<String, Object> data, String language) {
        var context = createContext(data);
        var templateName = getTemplateName(baseTemplateName, language);
        return templateEngine.process(templateName, context);
    }

    private Context createContext( Map<String, Object> data) {
        var context = new Context();
        context.setVariables(data);
        return context;
    }

    private String getTemplateName(String baseName, String language) {
        return availableLanguages.contains(language) ? baseName + LANGUAGE_SEPARATOR + language : baseName;
    }

}
