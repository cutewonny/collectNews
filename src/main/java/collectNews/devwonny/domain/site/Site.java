package collectNews.devwonny.domain.site;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Site {
    private Long siteId;
    private String siteName;
    private String siteUrl;
    private Boolean usingSite;
}
