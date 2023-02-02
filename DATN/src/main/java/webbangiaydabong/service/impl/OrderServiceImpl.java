package webbangiaydabong.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webbangiaydabong.dto.OrderDTO;
import webbangiaydabong.dto.OrderDetailDTO;
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
    @Autowired
    CustomerRepository customerRepository;

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
    public List<OrderDTO> timkiem(SearchDto dto) {
        if (dto == null) {
            return null;
        }

        String whereClause = "where (1=1)";

        String orderBy = " ORDER BY o.id desc ";


        String sql = "select new webbangiaydabong.dto.OrderDTO(o,true) from Order o  ";

        if (dto.getKeyword() != null) {
            whereClause += " AND o.diaChi.sdt like :keyword  ";
        }
        if (dto.getStatus() != null) {
            whereClause += " AND o.status=:status";
        }

        if(dto.getEnd()!=null&&dto.getStart()!=null){
            whereClause+=" AND create_date BETWEEN :start AND :end";
        }
        whereClause += " AND o.status NOT in(6) ";
        sql += whereClause + orderBy;

        Query q = manager.createQuery(sql, OrderDTO.class);

        if (dto.getKeyword() != null) {
            q.setParameter("keyword", '%' + dto.getKeyword().trim() + '%');
        }
        if (dto.getStatus() != null) {
            q.setParameter("status", dto.getStatus());
        }
        if(dto.getStart()!=null){
            q.setParameter("start", dto.getStart());
        }
        if(dto.getEnd()!=null){
            q.setParameter("end", dto.getEnd());
        }
        List<OrderDTO> entities = q.getResultList();

        return entities;
    }

    @Override
    public List<OrderDTO> searchByPage(SearchDto dto) {
        if (dto == null) {
            return null;
        }

        String whereClause = "where (1=1)";

        String orderBy = " ORDER BY o.id desc ";


        String sql = "select new webbangiaydabong.dto.OrderDTO(o,true) from Order o  ";


        if (dto.getStatus() != null) {
            whereClause += " AND o.status=:status";
        }
        whereClause += " AND o.status NOT in(6) ";
        sql += whereClause + orderBy;

        Query q = manager.createQuery(sql, OrderDTO.class);


        if (dto.getStatus() != null) {
            q.setParameter("status", dto.getStatus());
        }

        List<OrderDTO> entities = q.getResultList();

        return entities;

    }

    @Override
    public List<OrderDetailDTO> getByOrderId(Long id) {
        String sql = "SELECT `order`.id as order_id,orderdetail.id as id,product.name as productName,orderdetail.quantity as quantity,orderdetail.price as price,product.photo as photo,category.name as category_name,brand.name as brand_name,`order`.status as status\n" +
                "FROM category JOIN product ON category.id=product.category_id JOIN brand ON brand.id=product.hang_id  JOIN orderdetail ON product.id=orderdetail.product_id JOIN `order` ON orderdetail.order_id=`order`.id\n" +
                "WHERE `order`.id=:order_id\n" +
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
    public OrderDTO getOrderbyid(Long id) {
        OrderDTO dto = orderRepo.getOrderbyid(id);
        if (dto != null) {
            return dto;
        }
        return null;
    }

    public List<Order> getByStatus(String userName, Integer status) {
        return orderRepo.findAllByStatusAndUser(userName, status);
    }

    @Override
    public List<Order> report(Integer status) {
        return orderRepo.findAllByStatus(status);
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            orderRepo.deleteById(id);
        }
    }

    @Override
    public List<Integer> statistical(Date createDate, Date endDate) {
        return orderRepo.statistical(createDate, endDate);
    }

    public OrderDTO save(Long id, OrderDTO dto) {
        Order order = null;
        if (dto.getId() != null) {
            Optional<Order> optionalCustomer = orderRepo.findById(dto.getId());
            if (optionalCustomer.isPresent()) {
                order = optionalCustomer.get();
            }
        }
        if (order == null) {
            order = new Order();
        }
        if (dto.getPrice() != null) {
            order.setPrice(dto.getPrice());

        }
        if (dto.getNote() != null) {
            order.setNote(dto.getNote());
        }

        if (dto.getStatus() >= 0) {
            order.setStatus(dto.getStatus());
        }
        if (dto.getKenh() >= 0) {
            order.setKenh(dto.getKenh());
        }
        if(dto.getGiamgia()!=null){
            order.setGiamgia(dto.getGiamgia());
        }
        if(dto.getShip()!=null){
            order.setShippingFee(dto.getShip());
        }
        if (dto.getAccount_id() != null) {
            Account a = accountRepository.findById(dto.getAccount_id()).get();
            order.setAccount(a);
        }
        if (dto.getCustomer_id() != null) {
            Customer c = customerRepository.findById(dto.getCustomer_id()).get();
            order.setCustomer(c);
        }
        order = orderRepo.save(order);
        return new OrderDTO(order, true);

    }
}
