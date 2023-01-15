package webbangiaydabong.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import webbangiaydabong.dto.functiondto.AdministrativeUnitImportExcel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ImportExportExcelUtil {
    public static List<AdministrativeUnitImportExcel> importAdministrativeUnitFromInputStream(InputStream is) throws IOException {
        List<AdministrativeUnitImportExcel> listData = new ArrayList<AdministrativeUnitImportExcel>();
        try {
            // cảnh báo
            @SuppressWarnings("resource")
            Workbook workbook = new XSSFWorkbook(is);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            int rowIndex = 1;
            int falseIndex = 1;

            int num = datatypeSheet.getLastRowNum();
            while (rowIndex < num) {
                Row currentRow = datatypeSheet.getRow(rowIndex);
                Cell currentCell = null;
                if (currentRow != null) {
                    AdministrativeUnitImportExcel dto = new AdministrativeUnitImportExcel();
                    //Tên tỉnh
                    Integer index = 0;
                    currentCell = currentRow.getCell(index);
                    if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        String tenTinh = String.valueOf((int)currentCell.getNumericCellValue());
                        dto.setTenTinh(tenTinh);
                    } else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
                            && currentCell.getStringCellValue() != null) {
                        String tenTinh = currentCell.getStringCellValue().trim();
                        dto.setTenTinh(tenTinh);
                    }
                    //mã Tỉnh
                    index = 1;
                    currentCell = currentRow.getCell(index);
                    if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        String idTinh = String.valueOf((int)currentCell.getNumericCellValue());
                        dto.setIdTinh(idTinh);
                    } else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
                            && currentCell.getStringCellValue() != null) {
                        String idTinh = currentCell.getStringCellValue().trim();
                        dto.setIdTinh(idTinh);
                    }
                    //Tên quận huyện
                    index = 2;
                    currentCell = currentRow.getCell(index);
                    if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        String tenHuyen = String.valueOf((int)currentCell.getNumericCellValue());
                        dto.setTenHuyen(tenHuyen);
                    } else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
                            && currentCell.getStringCellValue() != null) {
                        String tenHuyen = currentCell.getStringCellValue().trim();
                        dto.setTenHuyen(tenHuyen);
                    }
                    //id quận huyện
                    index = 3;
                    currentCell = currentRow.getCell(index);
                    if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        String idHuyen = String.valueOf((int)currentCell.getNumericCellValue());
                        dto.setIdHuyen(idHuyen);
                    } else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
                            && currentCell.getStringCellValue() != null) {
                        String idHuyen = currentCell.getStringCellValue().trim();
                        dto.setIdHuyen(idHuyen);
                    }
                    //Tên phường, xã
                    index = 4;
                    currentCell = currentRow.getCell(index);
                    if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        String tenXa	= String.valueOf((int)currentCell.getNumericCellValue());
                        dto.setTenXa(tenXa);
                    } else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
                            && currentCell.getStringCellValue() != null) {
                        String tenXa = currentCell.getStringCellValue().trim();
                        dto.setTenXa(tenXa);
                    }
                    //id phường, xã
                    index = 5;
                    currentCell = currentRow.getCell(index);
                    if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        String idXa = String.valueOf((int)currentCell.getNumericCellValue());
                        dto.setIdXa(idXa);
                    } else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
                            && currentCell.getStringCellValue() != null) {
                        String idXa = currentCell.getStringCellValue().trim();
                        dto.setIdXa(idXa);
                    }
                    listData.add(dto);
                }
                rowIndex++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listData;
    }

}
