package pl.training.search.sages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Training implements Comparable<Training> {

    private String _id;
    private List<String> authors;
    private List<String> questionnaireBeforeTraining;
    private List<String> installationRequirements;
    private String textForCertificate;

    @Override
    public String toString() {
        return _id + ";"
                + String.join(", ", authors) + ";"
                + (textForCertificate.isBlank() ? "brak" : "ok")  + ";"
                + (questionnaireBeforeTraining.isEmpty() ? "brak" : "ok") + ";"
                + (installationRequirements.isEmpty() ? "brak" : "ok")
                + "\n";
    }

    @Override
    public int compareTo(Training training) {
        return _id.compareTo(training.get_id());
    }

}
