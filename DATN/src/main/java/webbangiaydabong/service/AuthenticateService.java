package webbangiaydabong.service;


import webbangiaydabong.dto.AccountDTO;
import webbangiaydabong.entity.Account;

public interface AuthenticateService {
    public Account signup(AccountDTO dto);
}
