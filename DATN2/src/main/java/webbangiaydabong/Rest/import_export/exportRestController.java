package webbangiaydabong.Rest.import_export;

import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webbangiaydabong.dto.OrderDTO;
import webbangiaydabong.repository.OrderRepository;
import webbangiaydabong.service.OrderService;
import webbangiaydabong.service.export.PDFGenerator;

import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/public/export")
public class exportRestController {
    @Autowired
    OrderService orderService;

    @GetMapping("/pdf/phieugiaohang/{id}")
    public void generator(HttpServletResponse response, @PathVariable Long id) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerkey, headervalue);
        OrderDTO orderDTOList =  orderService.getOrderbyid(id);
        PDFGenerator generetor = new PDFGenerator();
        generetor.setOrderDTOList(orderDTOList);
        generetor.generate(response);

    }

}
