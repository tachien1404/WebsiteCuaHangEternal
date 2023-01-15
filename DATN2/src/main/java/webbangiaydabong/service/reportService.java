package webbangiaydabong.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import webbangiaydabong.entity.*;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@EnableScheduling
public class reportService {

    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    OrderService orderService;

    @Autowired
    AccountService accountService;

    @Autowired
    PromotionDetaitlsService promotionDetaitlsService;

    @Autowired
    PromotionService promotionService;

    @Autowired
    ProductService productService;

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

   @Scheduled(cron = "0 0 0 * * ?")
// @Scheduled(fixedRate = 5000)
    public void promotion()   {
      Date date = new Date();
     SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
     String isDate = formatter.format(date);
     System.out.println(isDate);
     List<Promotion> find =  promotionService.findAll();
     List<Product>  productList = new ArrayList<>();
     for ( Promotion p : find) {
         if(formatter.format(p.getStarttime()).equals(isDate) ==true){
             List<PromotionDetails> promotionDetailsList = promotionDetaitlsService.findByPromotionId(p.getId());
             for (PromotionDetails pd : promotionDetailsList) {
                 Product product = pd.getProduct();
                 product.setOutputprice(product.getOutputprice() - ((product.getOutputprice() * pd.getPromotion().getValue()) / 100));
                 productService.create(product);
                 System.out.println("đã giảm giá");
             }
         }
     }
    }


}
