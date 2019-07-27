package com.gw.payroll;

import com.google.common.collect.Sets;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;

public class CalPayRoll {
    private File file;
    private FileInputStream inputStream;
    private Workbook book;
    private CalSetting setting;
    public CalPayRoll(File file,CalSetting setting) throws IOException, InvalidFormatException {
        this.file = file;
        this.inputStream = new FileInputStream(file);
        this.book = new XSSFWorkbook(file);
        this.setting = setting;
    }
    public void cal(){
        Sheet sheet = book.getSheetAt(0);
        CellReference nameReference = getCellReference(ColType.Name);
        CellReference genderReference = getCellReference(ColType.GENDER);
        Set<Employee> empSet = Sets.newHashSet();
        for(Row row : sheet){
            Employee employee = new Employee();
            employee.setKey(row.getRowNum());
            for(Cell cell : row){
                String value = cell.getStringCellValue();
                if(cell.getColumnIndex()==nameReference.getCol()){
                    employee.setName(value);
                }else if(cell.getColumnIndex()==genderReference.getCol()){
                    employee.setGender(value);
                }
            }
            empSet.add(employee);
        }
        System.out.println(empSet);
    }
    private CellReference getCellReference(ColType type){
        return new CellReference(this.setting.getColTypeMap().get(type));
    }
}
