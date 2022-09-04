package collectNews.devwonny.domain.keyword;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Keyword {

    private Long keywordId;
    private String keywordName;
    private Boolean usingKeyword;

}
