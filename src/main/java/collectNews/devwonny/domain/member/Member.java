package collectNews.devwonny.domain.member;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor //final, notnull 생성자 생성
public class Member {

    private Long memberid;

    @NotEmpty
    @NotNull
    private String loginId; //로그인 ID


    @NotEmpty
    @NotNull
    private String name; //사용자 이름

    @NotEmpty
    @NotNull
    private String password;


}
