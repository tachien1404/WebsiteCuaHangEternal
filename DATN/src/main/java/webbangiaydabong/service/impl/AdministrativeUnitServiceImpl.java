package webbangiaydabong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webbangiaydabong.dto.AdministrativeUnitDto;
import webbangiaydabong.dto.OrderDTO;
import webbangiaydabong.dto.functiondto.AdministrativeUnitImportExcel;
import webbangiaydabong.entity.AdministrativeUnit;
import webbangiaydabong.repository.AdministrativeUnitRepository;
import webbangiaydabong.service.AdministrativeUnitService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdministrativeUnitServiceImpl implements AdministrativeUnitService {
    @Autowired
    AdministrativeUnitRepository repository;
    @PersistenceContext
    EntityManager manager;
    @Override
    public List<AdministrativeUnitDto> importExcel(List<AdministrativeUnitImportExcel> dtos) {
        if (dtos != null && dtos.size() > 0) {
            List<AdministrativeUnitDto> listData = new ArrayList<AdministrativeUnitDto>();
            for (AdministrativeUnitImportExcel dto : dtos) {
                AdministrativeUnit adminUnit = null;
                AdministrativeUnit district = null;
                AdministrativeUnit commune = null;

                if (dto.getIdTinh() != null) {
                    List<AdministrativeUnit> listUnit = this.repository.findListByCode(dto.getIdTinh());
                    if (listUnit != null && listUnit.size() > 0) {
                        adminUnit = listUnit.get(0);
                    }
                    if (adminUnit == null) {
                        adminUnit = new AdministrativeUnit();
                        adminUnit.setCode(dto.getIdTinh());
                        if (dto.getTenTinh() != null) {
                            adminUnit.setName(dto.getTenTinh());
                        }
                        adminUnit.setLevel(3);
                        adminUnit = this.repository.save(adminUnit);
                    }
                }

                if (adminUnit != null) {
                    if (dto.getIdHuyen() != null) {
                        List<AdministrativeUnit> listUnit = this.repository.findListByCode(dto.getIdHuyen());
                        if (listUnit != null && listUnit.size() > 0) {
                            district = listUnit.get(0);
                        }
                        if (district == null) {
                            district = new AdministrativeUnit();
                            district.setParent(adminUnit);
                            district.setCode(dto.getIdHuyen());
                            if (dto.getTenHuyen() != null) {
                                district.setName(dto.getTenHuyen());
                            }
                            district.setLevel(4);
                            district = this.repository.save(district);
                        }
                    }
                }

                if (district != null) {
                    if (dto.getIdXa() != null) {
                        List<AdministrativeUnit> listUnit = this.repository.findListByCode(dto.getIdXa());
                        if (listUnit != null && listUnit.size() > 0) {
                            commune = listUnit.get(0);
                        }
                        if (commune == null) {
                            commune = new AdministrativeUnit();
                            commune.setParent(district);
                            commune.setCode(dto.getIdXa());
                            if (dto.getTenXa() != null) {
                                commune.setName(dto.getTenXa());
                            }
                            commune.setLevel(5);
                            commune = this.repository.save(commune);
                        }
                    }
                }

            }
            return listData;
        }
        return null;

    }

    @Override
    public List<AdministrativeUnitDto> tinhhuyenxa(Long parent_id) {
        String whereClause = " where (1=1) ";

        String orderBy = " ORDER BY a.id desc ";
        String sql = "select new webbangiaydabong.dto.AdministrativeUnitDto(a) from AdministrativeUnit a ";
        if(parent_id==null){
            whereClause+=" AND parent.id=null ";
        }
        if(parent_id!=null){
            whereClause+=" AND parent.id =:parent_id";
        }
        sql+=whereClause;
        Query q = manager.createQuery(sql, AdministrativeUnitDto.class);
        if(parent_id!=null){
            q.setParameter("parent_id",parent_id);
        }
        List<AdministrativeUnitDto>result=q.getResultList();
        return result;
    }
}
