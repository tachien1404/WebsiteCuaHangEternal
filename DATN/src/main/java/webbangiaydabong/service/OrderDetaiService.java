package webbangiaydabong.service;

import webbangiaydabong.dto.OrderDetailDTO;
import webbangiaydabong.dto.functiondto.DatHangDto;
import webbangiaydabong.entity.OrderDetail;

import java.util.List;

public interface OrderDetaiService {
//	OrderDetailDTO save(DatHangDto dto);
	List<OrderDetail> findByIDOrder(Long id);
	List<OrderDetail> saveAll(List<OrderDetail> listDetails);
	OrderDetail create(OrderDetail orderDetail);
	List<OrderDetail> findByOder(Long id);
	void xoa(Long id);
	OrderDetailDTO saveOrEdit(OrderDetailDTO dto);
	List<OrderDetailDTO> findByOderId(Long id);
OrderDetailDTO sumgia(Long orderID);
Long sumsoluongmathang(long id);
}
