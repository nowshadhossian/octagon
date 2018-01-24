package com.kids.crm.exportdata;

import com.kids.crm.model.Question;
import com.kids.crm.model.Session;
import com.kids.crm.model.Subject;
import com.kids.crm.model.Topic;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExportQuestionData {
    private static final String FILE_NAME = "E:\\excelfile\\Question Information.xlsx";

    public static void main(String args[]){
        ExportQuestionData exportQuestionData = new ExportQuestionData();
        exportQuestionData.readQuestionExcel();
    }

    public List<Question> readQuestionExcel() {
        FileInputStream excelFile = null;
        List<Question> questionList = new ArrayList<>();
        try {
            excelFile = new FileInputStream(new File(FILE_NAME));
            XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                if (currentRow.getRowNum() == 0) {
                    continue;
                }
                Question question = Question.builder().build();
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {
                    Cell currentCell = cellIterator.next();
                    int columnIndex = currentCell.getColumnIndex();
                    switch (columnIndex) {
                        case 0:
                            question.setFileName((String) getCellValue(currentCell));
                            break;
                        case 1:
                            question.setAnswer((String) getCellValue(currentCell));
                            break;
                        case 2:
                            question.setSession(Session.builder().name((String) getCellValue(currentCell)).build());
                            break;
                        case 3:
                            question.setYear(new Double((double)getCellValue(currentCell)).intValue());
                            break;
                        case 4:
                            question.setPaper(new Double((double)getCellValue(currentCell)).intValue());
                            break;

                        case 5:
                            question.setQuestionNo(new Double((double)getCellValue(currentCell)).intValue());
                            break;

                        case 6:
                            question.setTopic(Topic.builder().name((String) getCellValue(currentCell)).build());
                            break;
                    }
                }
                question.setSubject(Subject.builder().name("Physics").build());
                questionList.add(question);

            }
            System.out.println(questionList.size());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return questionList;
    }

    private static Object getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();

            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue();

            case Cell.CELL_TYPE_NUMERIC:
                return cell.getNumericCellValue();
        }

        return null;
    }
}
