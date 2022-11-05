package webbangiaydabong.service;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import webbangiaydabong.entity.SaleCode;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@EnableScheduling

public class AutoSaleService {
//            @Scheduled(fixedRate = 5000)
//    //@Scheduled(cron = "0 0 9 * * ?")
//    public void AutoCreatePromotion()   {
//        System.out.println("abc");
//
//                SaleCode sale = new SaleCode();
//                Date date = new Date();
//                sale.setCreateDate(date);
//
//                Random random = new Random();
//                int otp = random.nextInt(35) + 10;
//                sale.setValue(otp);
//                sale.setDelete(true);
//
//        }

}
