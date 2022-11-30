package webbangiaydabong.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import webbangiaydabong.entity.Account;
import webbangiaydabong.entity.Order;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@EnableScheduling
public class reportService {

    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    OrderService orderService;

    @Autowired
    AccountService accountService;

        // @Scheduled(fixedRate = 5000)
   @Scheduled(cron = "0 0 9 * * ?")
    public void auToSendMailCheckPoint()   {
       List<Account> accountList = accountService.findAll();
      List<Account> listAccountEmail = new ArrayList<>();
        for (Account account: accountList) {
            if(account.getAuthorities().stream().anyMatch(
                    role -> (role.getRole().getId()==1 || role.getRole().getId()==2))==true){
              listAccountEmail.add(account);
            }
        }
             List<Order> orders = orderService.report(0);

        for(int i=0;i<listAccountEmail.size();i++){
            emailSenderService.sendSimpleEmail(listAccountEmail.get(i).getEmail(),
                    "Thông báo công việc","Hiện tại đang có" +orders.size()+"đơn hàng chưa được xác nhận. Vui lòng xem xét");
        }
        }


}
