package webbangiaydabong.service;

import webbangiaydabong.dto.OrderDetailDTO;
import webbangiaydabong.dto.functiondto.DatHangDto;
import webbangiaydabong.entity.OrderDetail;

import java.util.List;

public interface OrderDetaiService {
//	OrderDetailDTO save(DatHangDto dto);
	List<OrderDetail> findByIDOrder(Integer id);
}
