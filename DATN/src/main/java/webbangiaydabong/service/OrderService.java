package webbangiaydabong.service;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import io.swagger.models.auth.In;
import org.hibernate.validator.cfg.defs.NormalizedDef;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import webbangiaydabong.dto.OrderDTO;
import webbangiaydabong.dto.OrderDetailDTO;
import webbangiaydabong.dto.Report;
import webbangiaydabong.dto.functiondto.DatHangDto;
import webbangiaydabong.dto.functiondto.SearchDto;
import webbangiaydabong.entity.Order;
import webbangiaydabong.entity.OrderDetail;

@Service

public interface OrderService {


	Object finById(Long id);
	List<Order> findByUserId(Long id);

List<OrderDTO>timkiem(SearchDto dto);
	List<OrderDTO>getAllByStatus(Integer status);
	OrderDTO getOrderbyid(Long id);
	void updatetrangthai(Long id, OrderDTO dto);
	List<OrderDTO>searchByPage(SearchDto dto);
    List<OrderDetailDTO> getByOrderId(Long id);
    Order saveOder(Order order);
    List<Order> showHistoryByAccount(String userName);
    Order getById(Long id);
    List<Order> getByStatus(String userName, Integer status);
    List<Order> report(Integer status);
    OrderDTO save(Long id,OrderDTO dto);
    void delete(Long id);
    List<Integer> statistical(Date createDate, Date endDate);
}
