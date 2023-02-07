package webbangiaydabong.service.impl;

import java.math.BigInteger;
import java.util.*;

import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import webbangiaydabong.dto.*;
import webbangiaydabong.entity.*;
import webbangiaydabong.repository.ProductRepository;
import webbangiaydabong.repository.S_C_Repository;
import webbangiaydabong.service.ProductService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Service
public class ProductServiceImpl implements ProductService {
	@PersistenceContext
	EntityManager manager;
	@Autowired
	ProductRepository productRepo;
    @Autowired
    S_C_Repository s_c_repository;
	@Override
	public List<Product> findAll() {
		return productRepo.findByStatus();
	}

	@Override
	public List<Product> findByCategoryId(String categoryId) {
		return productRepo.finByCategoryId(categoryId);
	}

	@Override
	public Product findById(Long id) {
		return productRepo.findById(id).get();
	}

	@Override
	public Product create(Product product) {
		return productRepo.save(product);
	}

	@Override
	public Product update(Product product) {
		return productRepo.save(product);
	}

	@Override
	public void delete(Long id) {
		productRepo.deleteById(id);
	}

	@Override
	public Page<Product> findByKey(Pageable pageable, String name, Double outputprice, Category category, Brand hang, Sole sole, ShoeLine shoeLine) {
		return productRepo.findByKey(pageable, name, outputprice, category, hang, sole, shoeLine);
	}

	@Override
	public List<Product> findByStatus() {
		return productRepo.findByStatus();
	}

	@Override
	public List<Product> findTop(Date date) {
		return productRepo.findTop(date);
	}



	@Override
	public List<Object> topbanchay() {
		List<Object>topbanchay=productRepo.topbanchay();
		return topbanchay;
	}

	@Override
	public Page<Product> findByKey2(Pageable pageable, String name, Float priceStart, Float priceEnd, Category category, Brand hang, Sole sole, ShoeLine shoeLine) {
		return productRepo.findByKey2(pageable, name, priceStart, priceEnd, category, hang, sole, shoeLine);
	}

	@Override
	public List<Object> hotTrend(Long idProduct) {
		return productRepo.hotTrend(idProduct);
	}






    @Override
    public List<ProductDTO> serchName(ProductDTO dto) {
        String sql = "select new webbangiaydabong.dto.ProductDTO(o) from Product o ";
        String whereClause = "where (1=1) AND o.status=1 AND o.delete=1 ";
        if (dto.getName() != null) {
            whereClause += " AND o.name like :name ";
        }
        if (dto.getHang_id() != null) {
            whereClause += " AND o.hang.id=:hang_id";
        }
        if (dto.getCategory_id() != null) {
            whereClause += " AND o.category.id=:category_id";
        }
        if (dto.getShoeLine_id() != null) {
            whereClause += " AND o.shoeLine.id=:shoeline_id";
        }
        if (dto.getSole_id() != null) {
            whereClause += " AND o.sole.id=:sole_id";
        }
        if (dto.getStartgia() != null && dto.getEndgia() != null) {
            whereClause += " AND o.outputprice BETWEEN  :start AND :end";
        }
        sql += whereClause;
        Query q = manager.createQuery(sql, ProductDTO.class);
        if (dto.getStartgia() != null) {
            q.setParameter("start", dto.getStartgia());
        }
        if (dto.getEndgia() != null) {
            q.setParameter("end", dto.getEndgia());
        }
        if (dto.getName() != null) {
            q.setParameter("name", '%' + dto.getName().trim() + '%');
        }
        if (dto.getCategory_id() != null) {
            q.setParameter("category_id", dto.getCategory_id());
        }
        if (dto.getSole_id() != null) {
            q.setParameter("sole_id", dto.getSole_id());
        }
        if (dto.getHang_id() != null) {
            q.setParameter("hang_id", dto.getHang_id());
        }
        if (dto.getShoeLine_id() != null) {
            q.setParameter("shoeline_id", dto.getShoeLine_id());
        }
        List<ProductDTO> lstProductDTOS = q.getResultList();
        for (ProductDTO x:lstProductDTOS) {
            List<SizeDTO>litsai=s_c_repository.size(x.getId());
            List<ColorDTO>litcolor=s_c_repository.color(x.getId());
            if(litsai!=null&&litsai.size()>0){
                x.setLitsai(litsai);
            }
            if(litcolor!=null&&litcolor.size()>0){
                x.setLitcolor(litcolor);
            }
        }
        return lstProductDTOS;
    }

    @Override
    public List<ProductDTO> locproductadmin(ProductDTO dto) {
        String sql = "select new webbangiaydabong.dto.ProductDTO(o) from Product o ";
String oderby=" order by o.id desc";
        String whereClause = "where (1=1) AND o.delete=1 ";
        if (dto.getName() != null) {
            whereClause += " AND o.name like :name ";
        }
        if (dto.getHang_id() != null) {
            whereClause += " AND o.hang.id=:hang_id";
        }
        if (dto.getCategory_id() != null) {
            whereClause += " AND o.category.id=:category_id";
        }
        if (dto.getShoeLine_id() != null) {
            whereClause += " AND o.shoeLine.id=:shoeline_id";
        }
        if (dto.getSole_id() != null) {
            whereClause += " AND o.sole.id=:sole_id";
        }
        if (dto.getStartgia() != null && dto.getEndgia() != null) {
            whereClause += " AND o.outputprice BETWEEN  :start AND :end";
        }
        if(dto.getStatus()!=null){
            whereClause +=" AND o.status =:status";
        }
        sql += whereClause+oderby;
        Query q = manager.createQuery(sql, ProductDTO.class);

        if (dto.getStartgia() != null) {
            q.setParameter("start", dto.getStartgia());
        }
        if (dto.getEndgia() != null) {
            q.setParameter("end", dto.getEndgia());
        }
        if (dto.getName() != null) {
            q.setParameter("name", '%' + dto.getName().trim() + '%');
        }
        if (dto.getCategory_id() != null) {
            q.setParameter("category_id", dto.getCategory_id());
        }
        if (dto.getSole_id() != null) {
            q.setParameter("sole_id", dto.getSole_id());
        }
        if (dto.getHang_id() != null) {
            q.setParameter("hang_id", dto.getHang_id());
        }
        if (dto.getShoeLine_id() != null) {
            q.setParameter("shoeline_id", dto.getShoeLine_id());
        }
        if (dto.getStatus() != null) {
            q.setParameter("status", dto.getStatus());
        }

        List<ProductDTO> lstProductDTOS = q.getResultList();
        List<Object[]> lstcount = productRepo.adminproduct();
        Map<Long, Long> map = new HashMap<>();
        for (Object[] x : lstcount) {
            map.put((Long) x[0], (Long) x[1]);
        }
        Set<Long> set = map.keySet();
        List<ProductDTO> litcuoi = new ArrayList<>();
        for (ProductDTO x : lstProductDTOS) {
            for (Long y : set) {
                if (x.getId() == y) {
                    x.setSumquantity((Long) map.get(y));

                }

            }

        }
        return lstProductDTOS;
    }


}
