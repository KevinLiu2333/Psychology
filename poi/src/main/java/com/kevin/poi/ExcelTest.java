package com.kevin.poi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2017/9/20
 * Time: 13:30
 */
public class ExcelTest {

    private Workbook workBook;

//    @Before
//    public void setUp() throws IOException, InvalidFormatException {
////        加载excel文件,自动判断是HSSF还是XSSF
//        workBook = WorkbookFactory.create(new File("D://test.xlsx"));
//
//    }

    @Test
    public void testReadExcel() {
//        获取第一个工作目录
        Sheet sheet = workBook.getSheetAt(0);
        System.out.println(sheet.getSheetName());
//        获取该工作目录最后一行的行数
        int lastRowNum = sheet.getLastRowNum();

        for (int i = 0; i < lastRowNum; i++) {

            // 获取下标为i的行
            Row row = sheet.getRow(i);

            // 获取该行单元格个数
            int lastCellNum = row.getLastCellNum();

            for (int j = 0; j < lastCellNum; j++) {

                // 获取下标为j的单元格
                Cell cell = row.getCell(j);

                // 调用获取方法
                String cellValue = this.getCellValue(cell);
                System.out.println(cellValue);
            }
        }

    }

    private String getCellValue(Cell cell) {
        String str = null;
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BLANK:
                str = "";
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                str = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA:
                str = String.valueOf(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_NUMERIC:
                str = String.valueOf(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_STRING:
                str = String.valueOf(cell.getStringCellValue());
                break;
            default:
                str = null;
                break;
        }
        return str;
    }
}
