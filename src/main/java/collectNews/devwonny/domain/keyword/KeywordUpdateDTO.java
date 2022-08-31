package collectNews.devwonny.domain.keyword;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeywordUpdateDTO {
    private String keywordName;
    private boolean usingKeyword;
}
