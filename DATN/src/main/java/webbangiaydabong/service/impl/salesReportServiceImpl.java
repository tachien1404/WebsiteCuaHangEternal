package webbangiaydabong.service.impl;


import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webbangiaydabong.dto.OrderDetailDTO;
import webbangiaydabong.dto.salesReportDto;
import webbangiaydabong.repository.OrderRepository;
import webbangiaydabong.service.salesReportService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Service
public class salesReportServiceImpl implements salesReportService {
    @PersistenceContext
    EntityManager manager;
    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<salesReportDto> gettat() {
        String sql = "Select Year(create_date) as 'nam', Sum(price) as 'revenue'\n" +
                "FROM `order`\n" +
                "WHERE `status`=3\n" +
                "Group by Year(create_date)\n" +
                "order BY Year(create_date) ";
        Query query = manager.createNativeQuery(sql).unwrap(org.hibernate.query.Query.class).setResultTransformer(new AliasToBeanResultTransformer(salesReportDto.class));

        List<salesReportDto> lsSalesReportDtos = query.getResultList();
        return lsSalesReportDtos;
    }

    @Override
    public List<salesReportDto> gettheoday(salesReportDto dto) {
        String sql = "Select create_date as 'ngay', sum(price) as 'revenue'\n" +
                "FROM `order`\n" +
                "WHERE create_date BETWEEN  :start AND :end AND `status`=3\n" +
                "Group by create_date\n" +
                "order BY create_date ";

        Query query = manager.createNativeQuery(sql).unwrap(org.hibernate.query.Query.class).setResultTransformer(new AliasToBeanResultTransformer(salesReportDto.class));
        if (dto.getStart() != null) {
            query.setParameter("start", dto.getStart());
        }
        if (dto.getEnd() != null) {
            query.setParameter("end", dto.getEnd());
        }
        List<salesReportDto> lsSalesReportDtos = query.getResultList();
        return lsSalesReportDtos;
    }

    @Override
    public List<salesReportDto> gettheothang(salesReportDto dto) {
        String sql = "Select month(create_date) as 'nam', sum(price) as 'revenue'\n" +
                " FROM `order`\n" +
                " WHERE create_date BETWEEN  :start AND :end AND `status`=3\n" +
                " Group by month(create_date)\n" +
                " order BY month(create_date)";

        Query query = manager.createNativeQuery(sql).unwrap(org.hibernate.query.Query.class).setResultTransformer(new AliasToBeanResultTransformer(salesReportDto.class));
        if (dto.getStart() != null) {
            query.setParameter("start", dto.getStart());
        }
        if (dto.getEnd() != null) {
            query.setParameter("end", dto.getEnd());
        }
        List<salesReportDto> lsSalesReportDtos = query.getResultList();
        return lsSalesReportDtos;
    }

    @Override
    public salesReportDto thongke() {


        String sql = "SELECT  SUM(price) AS doanhthuday,COUNT(id)AS sld\n" +
                "FROM `order`\n" +
                "WHERE create_date =:ngay AND `status` =3";
        Query query = manager.createNativeQuery(sql).unwrap(org.hibernate.query.Query.class).setResultTransformer(new AliasToBeanResultTransformer(salesReportDto.class));

        query.setParameter("ngay", java.time.LocalDate.now());

        salesReportDto dto = (salesReportDto) query.getSingleResult();
        return dto;
    }

    @Override
    public List<Long> getsld01() {
        List<Long> getsld01 = orderRepository.getsld01();
        return getsld01;
    }


}
