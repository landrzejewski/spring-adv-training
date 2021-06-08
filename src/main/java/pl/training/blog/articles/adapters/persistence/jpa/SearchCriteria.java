package pl.training.blog.articles.adapters.persistence.jpa;

import lombok.Value;

@Value
public class SearchCriteria {

    String propertyName;
    Object value;
    Operator operator;

}
