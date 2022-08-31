package collectNews.devwonny.service;

import collectNews.devwonny.domain.member.Member;

public interface LoginService {
    Member login(String loginId, String password);
}
