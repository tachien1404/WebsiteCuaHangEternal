package webbangiaydabong.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webbangiaydabong.dto.salesReportDto;
import webbangiaydabong.service.salesReportService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/public/report")
public class ReportSalesRestController {
    @Autowired
    salesReportService salesReportService;
    @GetMapping("/revenue")
    public List<salesReportDto>gettat(){
       return salesReportService.gettat();
    }
    @PostMapping ("/theoday")
    public List<salesReportDto>gettheoday(@RequestBody salesReportDto dto){
        return salesReportService.gettheoday(dto);
    }
    @PostMapping ("/theothang")
    public List<salesReportDto>gettheothang(@RequestBody salesReportDto dto){
        return salesReportService.gettheothang(dto);
    }
}
