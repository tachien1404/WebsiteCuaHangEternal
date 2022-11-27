package webbangiaydabong.service.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import webbangiaydabong.dto.OrderDTO;
import webbangiaydabong.dto.OrderDetailDTO;
import webbangiaydabong.dto.functiondto.DatHangDto;
import webbangiaydabong.dto.functiondto.SearchDto;
import webbangiaydabong.entity.*;
import webbangiaydabong.repository.*;
import webbangiaydabong.service.OrderService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Service
public class OrderServiceImpl implements OrderService {
    @PersistenceContext
    EntityManager manager;
    @Autowired
    OrderRepository orderRepo;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderDetailRepository orderDetailRepo;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    S_C_Repository s_c_repository;

    @Override
    public Object finById(Long id) {
        return orderRepo.findById(id).get();
    }

    @Override
    public List<Order> findByUserId(Long id) {
        return orderRepo.findByUserId(id);
    }


    @Override
    public List<OrderDTO> getAllByStatus(Integer status) {
        return orderRepo.getAllByStatus(status);

    }

//    @Override
//    public ResponseEntity add(List<DatHangDto> dto) {
//        System.out.println(dto.toString());
//
//        Order order = new Order();
//        if (!dto.isEmpty()) {
//            float total = 0;
//            for (DatHangDto tong : dto) {
//                total += tong.getTotal();
//            }
//            order.setPrice((double) total);
//            order.setStatus(0);
//
//            orderRepo.save(order);
//
//            for (DatHangDto c : dto) {
//                OrderDetail orderDetail = new OrderDetail();
//                orderDetail.setPrice(Float.parseFloat(String.valueOf(c.getOutputprice())));
//                orderDetail.setQuantity(c.getQuantity());
//                orderDetail.setOrder(order);
//                orderDetail.setProduct(productRepository.getById(c.getId()));
//                orderDetailRepo.save(orderDetail);
//            }
//        }
//        return new ResponseEntity<Order>(order, HttpStatus.OK);
//    }

    @Override
    public void updatetrangthai(Long id, OrderDTO dto) {
        if (id != null) {
            Order order = orderRepo.getById(id);
            List<OrderDetail> lstOrderDetails = orderDetailRepo.findOrderDetailByOrder(id);

              if (dto.getStatus() >= 0) {
                order.setStatus(dto.getStatus());
                orderRepo.save(order);
            }
return;

        } else {
            return;
        }


    }

    @Override
    public Page<OrderDTO> searchByPage(SearchDto dto) {
        if (dto == null) {
            return null;
        }
        int pageIndex = dto.getPageIndex();
        int pageSize = dto.getPageSize();

        if (pageIndex > 0) {
            pageIndex--;
        } else {
            pageIndex = 0;
        }

        String whereClause = "where (1=1)";

        String orderBy = " ORDER BY o.id ";

        String sqlCount = "select count(entity.id) from Order as entity where (1=1)   ";
        String sql = "select new webbangiaydabong.dto.OrderDTO(o,true) from Order o  ";

        if (dto.getKeyword() != null) {
            whereClause += " AND (o.account.fullname like :keyword ) ";
        }
        sql += whereClause + orderBy;

        Query q = manager.createQuery(sql, OrderDTO.class);
        Query qCount = manager.createQuery(sqlCount);
        if (dto.getKeyword() != null) {
            q.setParameter("keyword", dto.getKeyword().trim());
        }

        int startPosition = pageIndex * pageSize;
        q.setFirstResult(startPosition);
        q.setMaxResults(pageSize);
        List<OrderDTO> entities = q.getResultList();
        long count = (long) qCount.getSingleResult();
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        return new PageImpl<>(entities, pageable, count);

    }

    @Override
    public List<OrderDetailDTO> getByOrderId(Long id) {
        String sql = "SELECT product.name as productName,orderdetail.quantity as quantity,orderdetail.price as price,product.photo as photo,category.name as category_name,brand.name as brand_name,order.status as status \n" +
                "FROM category JOIN product ON category.id=product.category_id JOIN brand ON brand.id=product.hang_id  JOIN orderdetail ON product.id=orderdetail.product_id JOIN `order` ON orderdetail.order_id=`order`.id JOIN image ON image.product_id=product.id\n" +
                "WHERE order.id=:order_id\n" +
                "GROUP BY orderdetail.id";
        Query query = manager.createNativeQuery(sql).unwrap(org.hibernate.query.Query.class).setResultTransformer(new AliasToBeanResultTransformer(OrderDetailDTO.class));
        if (id != null) {
            query.setParameter("order_id", id);
        }
        List<OrderDetailDTO> dlstOrderDetailDTOS = query.getResultList();
        return dlstOrderDetailDTOS;
    }

    @Override
    public Order saveOder(Order order) {
        return orderRepo.save(order);
    }

    @Override
    public List<Order> showHistoryByAccount(String userName) {
        return orderRepo.findALlOrderByAccount(userName);
    }

    @Override
    public Order getById(Long id) {
        return orderRepo.findById(id).get();
    }

    @Override
    public List<Order> getByStatus(String userName,Integer status) {
        return orderRepo.findAllByStatusAndUser(userName,status);
    }

    @Override
    public List<Order> report(Integer status) {
        return orderRepo.findAllByStatus(status);
    }

}
