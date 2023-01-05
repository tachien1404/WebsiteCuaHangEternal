package webbangiaydabong.service.impl;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import webbangiaydabong.dto.ProductDTO;
import webbangiaydabong.entity.Brand;
import webbangiaydabong.entity.Category;
import webbangiaydabong.entity.Product;
import webbangiaydabong.repository.BrandRepository;
import webbangiaydabong.repository.CategoryRepositoty;
import webbangiaydabong.repository.ProductRepository;
import webbangiaydabong.service.UploadService;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;


@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    ServletContext app;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepositoty categoryRepositoty;
    @Autowired
    BrandRepository brandRepository;

    public File save(MultipartFile file, String folder) {
        File dir = new File(app.getRealPath("/assets/") + folder);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String s = System.currentTimeMillis() + file.getOriginalFilename();
        String name = Integer.toHexString(s.hashCode()) + s.substring(s.lastIndexOf("."));
        try {
            File savedFile = new File(dir, name);
            file.transferTo(savedFile);
            System.out.println(savedFile.getAbsolutePath());
            return savedFile;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<ProductDTO> importProducttoExcel(MultipartFile file) {
        List<ProductDTO> lstProductDTOs = new ArrayList<ProductDTO>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());// đại diện cho một file Excel ,
            // HSSFWorkbook và XSSFWorkbook tương ứng cho định dạng .xls và .xlsx .
            XSSFSheet worksheet = workbook.getSheetAt(0);
            for (int i = 0; i < worksheet.getPhysicalNumberOfRows(); i++) {
                if (i > 0) {
                    ProductDTO dto = new ProductDTO();

                    DataFormatter formatter = new DataFormatter();
                    XSSFRow row = worksheet.getRow(i);
                    dto.setName(formatter.formatCellValue(row.getCell(0)));



                    dto.setOutputprice(((float) row.getCell(2).getNumericCellValue()));

                    dto.setCategoryName(formatter.formatCellValue(row.getCell(3)));
                    dto.setBrandName(formatter.formatCellValue(row.getCell(4)));
                    Long category_id = (long) row.getCell(5).getNumericCellValue();
                    Long hang_id = (long) row.getCell(6).getNumericCellValue();

                    dto.setStatus((int) row.getCell(7).getNumericCellValue());
                    dto.setPhoto(formatter.formatCellValue(row.getCell(8)));
                    dto.setCategory_id(category_id.longValue());
                    dto.setHang_id(hang_id.longValue());

                    lstProductDTOs.add(dto);
                }
            }
            return lstProductDTOs;

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e + "dd");
            return null;

        }

    }

    @Override
    public List<ProductDTO> savelist(List<ProductDTO> list) {
        List<Product> lstProducts = new ArrayList<Product>();
        for (ProductDTO x : list) {
            Brand brand = null;
            Category category = null;
            Product entity = new Product();
            // theem category
            if (x.getCategory_id() != null) {
                Optional<Category> categoryOptional = categoryRepositoty.findById(x.getCategory_id());
                if (categoryOptional.isPresent()) {
                   category = categoryOptional.get();

                }
            }
            if (category == null) {
                category = new Category();

            }
            category.setId(x.getCategory_id());
            category.setDelete(true);
            if (x.getCategoryName() != null) {
                category.setName(x.getCategoryName());

            }

          category=  categoryRepositoty.save(category);

            // theem brand
            if (x.getHang_id() != null) {

                Optional<Brand> brandOptional = brandRepository.findById(x.getHang_id());
                if (brandOptional.isPresent()) {
                    brand = brandOptional.get();
                }
            }
            if (brand == null) {
                brand = new Brand();


            }
            brand.setId(x.getHang_id());
            brand.setDelete(true);
            if (x.getBrandName() != null) {
                brand.setName(x.getBrandName());
            }

           brand= brandRepository.save(brand);

            if (x.getOutputprice() >= 0) {
                entity.setOutputprice(x.getOutputprice());
            }
            if (x.getName() != null) {
                entity.setName(x.getName());
            }

            if (x.getStatus() >= 0) {
                entity.setStatus(x.getStatus());
            }

            if (x.getPhoto() != null) {
                entity.setPhoto(x.getPhoto());
            }
            entity.setDelete(true);
            entity.setCategory(category);
            entity.setHang(brand);
            lstProducts.add(entity);
        }
        Iterable<Product> lstIterable = lstProducts;
        lstProducts = productRepository.saveAll(lstIterable);
        return list;

    }




    @Override
	public void save(String folder,String fileName, MultipartFile multipartFile) throws IllegalStateException, IOException {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "productenternal",
                "api_key", "552995514935254",
                "api_secret", "SC_gB7XSr0p9zsP8LaBsisPpjtU"));
    	if(multipartFile!=null){
			fileName = multipartFile.getOriginalFilename();
            File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+"imageTmp");
            multipartFile.transferTo(convFile);
            cloudinary.uploader().upload(convFile,
            ObjectUtils.asMap(
                "public_id", fileName.substring(0, fileName.lastIndexOf('.')),
                "folder", "IMAGE/"+folder+"/"));
		}
	}

	 AWSCredentials credentials =
			 new BasicAWSCredentials("AKIAUTOYM6LR433GWXZ3", "lL2v08CtG53LgfC0gFt2zDMdq0yO5YLXy+G8TRxz");

	 AmazonS3 s3client = AmazonS3ClientBuilder
	 .standard()
	 .withRegion(Regions.AP_SOUTHEAST_1)
	 .withCredentials(new AWSStaticCredentialsProvider(credentials))
	 .build();

	@Override
	public void saveProduct(String folder, String fileName, MultipartFile multipartFile)
			throws IllegalStateException, IOException {

		 File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+"fileTmp");
	        multipartFile.transferTo(convFile);

	        s3client.putObject(new PutObjectRequest("enternalproduct", multipartFile
	                .getOriginalFilename().replaceAll("\\s+",""), convFile)
	                .withCannedAcl(CannedAccessControlList.PublicRead));
	}
	
	
}
