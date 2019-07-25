package com.gw.payroll;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CalPayRoll {
    private File file;
    private FileInputStream inputStream;
    private Workbook book;
    public CalPayRoll(File file) throws IOException, InvalidFormatException {
        this.file = file;
        this.inputStream = new FileInputStream(file);
        this.book = new XSSFWorkbook(file);
    }
    public void cal(){
        Sheet sheet = book.getSheetAt(0);
        CellReference cellReference = new CellReference("d2");
        List<Employee> empList = new ArrayList<>();
        for(Row row : sheet){
            Employee employee = new Employee();
            employee.setKey(String.valueOf(row.getRowNum()));
            for(Cell cell : row){
                if(cell.getColumnIndex()==cellReference.getCol()){
                    employee.setName(cell.getStringCellValue());
                }
            }
            empList.add(employee);
        }
        System.out.println(empList);
    }
}
