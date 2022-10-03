package webbangiaydabong.service.impl;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import webbangiaydabong.dto.BrandDTO;
import webbangiaydabong.dto.CategoryDTO;
import webbangiaydabong.dto.ImageDTO;
import webbangiaydabong.dto.ProductDTO;
import webbangiaydabong.entity.Brand;
import webbangiaydabong.entity.Category;
import webbangiaydabong.entity.Image;
import webbangiaydabong.entity.Product;
import webbangiaydabong.repository.BrandRepository;
import webbangiaydabong.repository.CategoryRepositoty;
import webbangiaydabong.repository.ImageRepository;
import webbangiaydabong.repository.ProductRepository;
import webbangiaydabong.service.UploadService;

@Service
public class UploadServiceImpl implements UploadService {
	@Autowired
	ServletContext app;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	CategoryRepositoty categoryRepositoty;
	@Autowired
	ImageRepository imageRepository;
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
					//dto.setCreateDate(row.getCell(1).getDateCellValue());
					dto.setQuantity((int)row.getCell(2).getNumericCellValue());
					dto.setInportprice(row.getCell(3).getNumericCellValue());
					dto.setOutputprice(row.getCell(4).getNumericCellValue());
					//dto.setUpdatedate(row.getCell(5).getDateCellValue());
					Long category_id =  (long) row.getCell(6).getNumericCellValue();
					Long hang_id =  (long) row.getCell(7).getNumericCellValue();

					dto.setStatus((int)row.getCell(8).getNumericCellValue());
					dto.setCategory_id(category_id.longValue());
					dto.setHang_id(hang_id.longValue());

					lstProductDTOs.add(dto);
				}
			}
			return lstProductDTOs;

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e+"dd");
			return null;

		}

	}

	@Override
	public List<ProductDTO> savelist(List<ProductDTO> list) {
		List<Product> lstProducts = new ArrayList<Product>();
		for (ProductDTO x : list) {
			Product entity = new Product();
			entity.setInportprice(x.getInportprice());
			entity.setName(x.getName());
			entity.setOutputprice(x.getOutputprice());
			entity.setQuantity(x.getQuantity());
			entity.setStatus(x.getStatus());
			entity.setUpdatedate(x.getUpdatedate());
			entity.setCreateDate(x.getCreateDate());
			Category cate = categoryRepositoty.findById(x.getCategory_id()).get();
			Brand brand = brandRepository.findById(x.getHang_id()).get();
entity.setCategory(cate);
entity.setHang(brand);
			lstProducts.add(entity);
		}
		Iterable<Product> lstIterable = lstProducts;
		lstProducts = productRepository.saveAll(lstIterable);
		return list;

	}
}
