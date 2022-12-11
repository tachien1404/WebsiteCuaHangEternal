package webbangiaydabong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webbangiaydabong.dto.BrandDTO;
import webbangiaydabong.dto.CategoryDTO;
import webbangiaydabong.dto.ColorDTO;
import webbangiaydabong.dto.SizeDTO;
import webbangiaydabong.repository.CategoryRepositoty;
import webbangiaydabong.service.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class SetupdataServiceImpl implements SetUpdataService {
    @Autowired
    CategoryService categoryService;
    @Autowired
    BrandService brandService;
@Autowired
    ColorService colorService;
@Autowired
    SizeService sizeService;
    @Override
    public void Setupdata() {
        createCategory();
        createBrand();
        createColor();
        createSize();
    }
private void createColor(){
    ColorDTO dto=new ColorDTO();
    Integer[]a={1,2,3,4};
    for (Integer x :a){
        dto.setValue(x);
        if(colorService.checkvalue(dto.getValue())==false){
            colorService.save(dto);
        }
    }

}
    private void createSize(){
       SizeDTO dto=new SizeDTO();
        Integer[]a={38,39,40,41,42,43,44};
        for (Integer x :a){
            dto.setValue(x);
            if(sizeService.checkvalue(dto.getValue())==false){
                sizeService.save(dto);
            }
        }

    }
    private void createCategory() {
        CategoryDTO dto = new CategoryDTO();
        String[] a = {"Giay co nhan tao", "Giay co tu nhien", "Giay futSan"};
        for (String x : a) {
            dto.setName(x);
            if (categoryService.checkName(dto.getName()) == false) {
                categoryService.saveOrUpdate(dto);
            }

        }
    }

    private void createBrand() {
        BrandDTO dto = new BrandDTO();
        String[] a = {"Nike", "Addidass", "FUMA", "ASICS", "KAMITO", "MIZUNO", "THUONG DINH", "BA SOC"};
        for (String x : a) {
            dto.setName(x);
            if (brandService.checkName(dto.getName()) == false) {
                brandService.saveOrUpdate(dto);
            }
        }
    }
}
