package webbangiaydabong.service;

import webbangiaydabong.dto.salesReportDto;

import java.util.List;

public interface salesReportService {
    List<salesReportDto> gettat();

    List<salesReportDto> gettheoday(salesReportDto dto);

    List<salesReportDto> gettheothang(salesReportDto dto);
    salesReportDto thongke();
    List<Long> getsld01();//sld chưa xác nhận và chuẩn bịnhangf

}
