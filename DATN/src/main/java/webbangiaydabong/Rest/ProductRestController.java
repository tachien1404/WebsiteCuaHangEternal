package webbangiaydabong.Rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import webbangiaydabong.dto.ProductDTO;
import webbangiaydabong.dto.ProductSearchDTO;
import webbangiaydabong.dto.functiondto.SortByValue;
import webbangiaydabong.entity.*;
import webbangiaydabong.service.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/public/products")
public class ProductRestController {
    @Autowired
    UploadService uploadService;
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    BrandService brandService;

    @Autowired
    SizeService sizeService;

    @Autowired
    ColorService colorService;

    @Autowired
    SoleService soleService;

    @Autowired
    ShoeLineService shoeLineService;


    @GetMapping()
    public List<Product> getAll() {
        return productService.findAll();
    }

    @GetMapping("{id}")
    public Product getOne(@PathVariable("id") Long id) {
        return productService.findById(id);
    }
    @GetMapping("/getbyid/{id}")
    public Product getbyid(@PathVariable("id") Long id) {
        return productService.findById(id);
    }

    @PostMapping()
    public ResponseEntity<ResponseObject> create(@RequestBody Product product) {
        Date date = new Date();
        product.setCreateDate(date);
        product.setDelete(true);
        productService.create(product);
        return ResponseEntity.ok().body(new ResponseObject(HttpStatus.OK, "Thêm mới sản phẩm thành công", ""));
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseObject> update(@PathVariable("id") Long id, @RequestBody Product product) {
        Product productdb = productService.findById(id);
        try {
            Date date = new Date();
            product.setUpdatedate(date);
            if (product.getPhoto().equals("null.png")) {
                product.setPhoto(productdb.getPhoto());
            }
            productService.update(product);
            return ResponseEntity.ok().body(new ResponseObject(HttpStatus.OK, "Cập nhật sản phẩm thành công", ""));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(HttpStatus.BAD_REQUEST, "Cập nhật thất bại",
                            ""));
        }

    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<ResponseObject> delete(@PathVariable("id") Long id) {
//        productService.delete(id);
        try {
            Product p = productService.findById(id);
            p.setDelete(false);
            productService.update(p);
            return ResponseEntity.ok().body(new ResponseObject(HttpStatus.OK, "Xóa thành công", ""));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(HttpStatus.BAD_REQUEST, "Xóa thất bại",
                            ""));
        }

    }

    //Import Excel
    @PostMapping("/importproduct")
    public ResponseEntity<?> importSinhVien(@RequestParam("folder") MultipartFile uploadfile) {
        try {

            List<ProductDTO> list = uploadService.importProducttoExcel(uploadfile);
            uploadService.savelist(list);
            return new ResponseEntity<>("Import success!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Import failed!", HttpStatus.BAD_REQUEST);
        }
    }

    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }

    @PutMapping("/sortByKey")
    public ResponseEntity<ResponseObject> sortByKey(@RequestParam int page,
                                                    @RequestParam int size,
                                                    @RequestBody ProductSearchDTO dto) {
        try {
            page = page < 0 ? 0 : page;
            Pageable pageable;
            List<Sort.Order> orders = new ArrayList<>();
            List<SortByValue> sortByValueList = dto.getSortByValues();
            System.out.println(sortByValueList);
            if (sortByValueList.isEmpty()) {
                orders.add(new Sort.Order(Sort.Direction.ASC, "id") {
                });
            } else {
                sortByValueList.forEach(value -> {
                    orders.add(new Sort.Order(getSortDirection(value.getType()), value.getName()));
                });
            }

            pageable = PageRequest.of(page, size, Sort.by(orders));
            Page<Product> productPage;
            productPage = productService.findByKey(pageable, dto.getName(), dto.getOutputprice(),
                    dto.getCategory(), dto.getHang(), dto.getSole(), dto.getShoeLine());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(HttpStatus.OK, "Tìm thấy thành công", productPage)
            );

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(HttpStatus.BAD_REQUEST, "Không tìm thấy",
                            ""));
        }
    }
    @PostMapping("/checktrung")
    public Product check(@RequestBody Product p){
        return productService.checktrung(p);
    }


    @GetMapping("/getAllCategory")
    public List<Category> getAllCategory() {
        return categoryService.getAll();
    }

    @GetMapping("/getAllBrand")
    public List<Brand> getAllBrand() {
        return brandService.getAll();
    }

    @GetMapping("/getAllSize")
    public List<Size> getAllSize() {
        return sizeService.findAll();
    }

    @GetMapping("/getColor")
    public List<Color> getColor() {
        return colorService.findAll();
    }

    @GetMapping("/getAllSole")
    public List<Sole> getAllSoleActive() {
        return soleService.findAllActice();
    }

    @GetMapping("/getAllShoeLine")
    public List<ShoeLine> getAllShoeLineActive() {
        return shoeLineService.findAllActivce();
    }

    @GetMapping("/top/{top}")
    public List<Product> findTop(@PathVariable("top") Integer top) {
        List<Product> productsTop = new ArrayList<>();
        Date date = new Date();
        List<Product> productsDb = productService.findTop(date);
        for (int i = 0; i < top; i++) {
            productsTop.add(productsDb.get(i));
        }
        return productsTop;
    }

    @PostMapping("/image")
    public HttpStatus upload(@RequestParam("file") MultipartFile multipartFile) {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        try {
            System.out.println("oke");
            uploadService.saveProduct("image", fileName, multipartFile);
            return HttpStatus.OK;
        } catch (IOException e) {
            e.printStackTrace();
            return HttpStatus.CONFLICT;
        }
    }

    @PostMapping("/serchName")
    public List<ProductDTO> serch(@RequestBody ProductDTO dto) {
        return productService.serchName(dto);
    }
    @PostMapping("/bolocproductadmin")
    public List<ProductDTO> bolocproductadmin(@RequestBody ProductDTO dto) {
        return productService.locproductadmin(dto);
    }
    @GetMapping("/topbanchay")
    public List<Object> topbanchay() {
        List<Object> listBanChay = productService.topbanchay();
        List<Object> top5BanChay = new ArrayList<>();
        if (listBanChay.size() >= 5) {
            for (int i = 0; i < 5; i++) {
                top5BanChay.add(listBanChay.get(i));
            }
        } else {
            for (int i = 0; i < listBanChay.size(); i++) {
                top5BanChay.add(listBanChay.get(i));
            }
        }
        return top5BanChay;
    }

    @PutMapping("/sortByKey2")
    public ResponseEntity<ResponseObject> sortByKey2(@RequestParam int page,
                                                    @RequestParam int size,
                                                    @RequestBody ProductSearchDTO dto) {
        try {
            page = page < 0 ? 0 : page;
            Pageable pageable;
            List<Sort.Order> orders = new ArrayList<>();
            List<SortByValue> sortByValueList = dto.getSortByValues();
            System.out.println(sortByValueList);
            if (sortByValueList.isEmpty()) {
                orders.add(new Sort.Order(Sort.Direction.DESC, "createDate") {
                });
            } else {
                sortByValueList.forEach(value -> {
                    orders.add(new Sort.Order(getSortDirection(value.getType()), value.getName()));
                });
            }

            pageable = PageRequest.of(page, size, Sort.by(orders));
            Page<Product> productPage;
            productPage = productService.findByKey2(pageable, dto.getName(), dto.getPriceStart(), dto.getPriceEnd(),
                    dto.getCategory(), dto.getHang(), dto.getSole(), dto.getShoeLine());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(HttpStatus.OK, "Tìm thấy thành công", productPage)
            );

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(HttpStatus.BAD_REQUEST, "Không tìm thấy",
                            ""));
        }
    };

    @GetMapping("/hotTrend/{idProduct}")
    public List<Object> hotTrend(@PathVariable("idProduct") String idProduct) {
        List<Object> listHotTrend = productService.hotTrend(Long.parseLong(idProduct));
        List<Object> top3 = new ArrayList<>();
        if (listHotTrend.size() >= 5) {
            for (int i = 0; i < 3; i++) {
                top3.add(listHotTrend.get(i));
            }
        } else {
            for (int i = 0; i < listHotTrend.size(); i++) {
                top3.add(listHotTrend.get(i));
            }
        }
        return top3;
    }
}





