package pl.training.blog.articles.adapters.persistence.jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Table(name = "articles")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArticleEntity {

    @GeneratedValue
    @Id
    private Long id;
    private String title;
    private String category;
    private int rating;
    @Column(name = "PUBLICATION_DATE")
    private Instant publicationDate;
    @Lob
    @Column(length = 4096)
    private String text;

    @Override
    public boolean equals(Object otherArticle) {
        if (this == otherArticle) {
            return true;
        }
        if (!(otherArticle instanceof ArticleEntity)) {
            return false;
        }
        var article = (ArticleEntity) otherArticle;
        return Objects.equals(id, article.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
