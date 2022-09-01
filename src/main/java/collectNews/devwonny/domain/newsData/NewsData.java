package collectNews.devwonny.domain.newsData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsData {
    private Long newsDataCd;
    private Long keywordId;
    private Long siteId;
    private String mediaTitle;
    private String mediaUrl;
    private String mediaContent;
    private Date createDt;

}
