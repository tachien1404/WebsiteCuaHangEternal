package webbangiaydabong.service;

import webbangiaydabong.dto.OrderDetailDTO;
import webbangiaydabong.dto.functiondto.DatHangDto;

public interface OrderDetaiService {
	OrderDetailDTO save(DatHangDto dto);
}
