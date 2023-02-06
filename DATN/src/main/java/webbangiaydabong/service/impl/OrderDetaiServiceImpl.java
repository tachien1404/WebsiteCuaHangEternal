package webbangiaydabong.service.impl;

import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webbangiaydabong.dto.OrderDetailDTO;
import webbangiaydabong.entity.Order;
import webbangiaydabong.entity.OrderDetail;
import webbangiaydabong.entity.Product;
import webbangiaydabong.entity.S_C_Details;
import webbangiaydabong.repository.OrderDetailRepository;
import webbangiaydabong.repository.OrderRepository;
import webbangiaydabong.repository.ProductRepository;
import webbangiaydabong.repository.S_C_Repository;
import webbangiaydabong.service.OrderDetaiService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Service
public class OrderDetaiServiceImpl implements OrderDetaiService {
    @PersistenceContext
    EntityManager manager;
    @Autowired
    OrderDetailRepository orderDetailRepo;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    S_C_Repository s_c_repository;

    @Override
    public List<OrderDetail> findByIDOrder(Long id) {
        return orderDetailRepo.findOrderDetailByOrder(id);
    }

    @Override
    public List<OrderDetail> saveAll(List<OrderDetail> listDetails) {
        return orderDetailRepo.saveAll(listDetails);
    }

    @Override
    public OrderDetail create(OrderDetail orderDetail) {
        return orderDetailRepo.save(orderDetail);
    }

    @Override
    public List<OrderDetail> findByOder(Long id) {
        return orderDetailRepo.findOrderDetailByOrder(id);
    }

    @Override
    public void xoa(Long id) {
        if (id != null) {
            Optional<OrderDetail> optionalOrderDetail = orderDetailRepo.findById(id);
            if (optionalOrderDetail.isPresent()) {
                OrderDetail o = optionalOrderDetail.get();
                orderDetailRepo.delete(o);
            }
        }
    }

    @Override
    public OrderDetailDTO saveOrEdit(OrderDetailDTO dto) {
        OrderDetail entity = null;
        if (dto.getDetail_id() != null) {
            Optional<OrderDetail> optionalOrderDetail = orderDetailRepo.findById(dto.getDetail_id());
            if (optionalOrderDetail.isPresent()) {
                entity = optionalOrderDetail.get();
            }
        }
        //quantity khi thêm trùng sp
        if (dto.getSizeId() != null && dto.getColorId() != null && dto.getProductId() != null) {
            S_C_Details sc = s_c_repository.findBySizeColor1(dto.getProductId(), dto.getSizeId(), dto.getColorId());
            Order order = null;
            if (dto.getOrderId() != null) {
                Optional<Order> optionalOrder = orderRepository.findById(dto.getOrderId());
                if (optionalOrder.isPresent()) {
                    order = optionalOrder.get();
                }
            }
            if (sc != null && order != null) {
                entity = orderDetailRepo.getByProductIdAndSC(dto.getProductId(), sc.getId(), order.getId());
                if (entity == null) {

                } else {
                    entity.setQuantity(entity.getQuantity() + dto.getQuantity());
                    orderDetailRepo.save(entity);
                    return new OrderDetailDTO();
                }
            }
        }
        if (entity == null) {
            entity = new OrderDetail();
        }
        if (dto.getOrderId() != null) {
            Optional<Order> optionalOrder = orderRepository.findById(dto.getOrderId());
            if (optionalOrder.isPresent()) {
                Order o = optionalOrder.get();
                entity.setOrder(o);
            }
        }
        if (dto.getPrice() > 0) {
            entity.setPrice(dto.getPrice());
        }
        if (dto.getProductId() != null) {
            Optional<Product> optionalProduct = productRepository.findById(dto.getProductId());
            if (optionalProduct.isPresent()) {
                Product p = optionalProduct.get();
                entity.setProduct(p);
            }
        }
        if (dto.getSizeId() != null && dto.getColorId() != null && dto.getProductId() != null) {
            S_C_Details sc = s_c_repository.findBySizeColor1(dto.getProductId(), dto.getSizeId(), dto.getColorId());
            if (sc == null) {
                return null;
            } else {
                entity.setSaimau(sc);
            }

        }
        if (dto.getQuantity() > 0) {
            entity.setQuantity(dto.getQuantity());
        }
        orderDetailRepo.save(entity);
        return new OrderDetailDTO();
    }

    @Override
    public List<OrderDetailDTO> findByOderId(Long id) {
        List<OrderDetailDTO> lstOrderDetailDTOS = orderDetailRepo.getByOrderId(id);
        if (lstOrderDetailDTOS != null && lstOrderDetailDTOS.size() > 0) {
            return lstOrderDetailDTOS;
        }
        return null;
    }

    @Override
    public OrderDetailDTO sumgia(Long orderID) {

        OrderDetailDTO dto = new OrderDetailDTO();
        long sumquantity = orderDetailRepo.sumquantity(orderID);
        float sumprice = orderDetailRepo.sumgia(orderID);

            dto.setPrice(sumprice);
            dto.setQuantity((int) sumquantity);
        return dto;
    }
}
