package com.cerner.dcm.audit.comparision;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFHyperlink;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ComparingDcwAndAudit {

    public static int numberOfColumnsOfDcw;
    public static int numberOfColumnsOfAudit;
    public static int primaryKeyColumnOfDcw;
    public static int primaryKeyColumnOfAudit;
    public static BufferedReader audit;
    public static BufferedReader partialMismatch;
    public static String row;
    public static String[] rowContentOfDcwInArray = new String[1000000];
    public static String[] rowContentOfAuditInArray = new String[1000000];
    public static String rowContent;
    public static HashMap<String, String> fileMap = new HashMap<>();
    public static HashMap<String, String> fileMap1 = new HashMap<>();
    public static PrintWriter printInComparisionFile;
    public static int rowCountInDcw;
    public static int okCount;
    public static int mismatchCount;
    public static int addToDomainCount;
    public static int inDomainCount;
    public static String okPercentage;
    public static String mismatchPercentage;
    public static String addToDomainPercentage;
    public static String inDomainPercentage;
    public static int dcwRowNumber,auditRowNumber;
    public static Row excelRow;
    public static Workbook workBook;
    public static CreationHelper creationHelper;
    public static Sheet comparisionSheet;
    public static Cell cell;
    public static CellStyle inDomainStyle;
    public static int totalNumberOfDcwRows;
    public static int totalNumberOfAuditRows;
    public static CellStyle auditHyperLinkStyle;
    public static int comparisionSheetRows;
    public static String headerLineInAudit;
    public static String[] headerLineOfAudit;
    public static String headerLineInDcw;
    public static String[] headerLineOfDcw;
    
    public static void Comparision(String numberOfColumnsInDcw,String numberOfColumnsInAudit,String primaryKeyColumnofDcw,String primaryKeyColumnofAudit,String dcwPath,String auditPath  ) throws IOException {
    
        numberOfColumnsOfDcw = Integer.parseInt(numberOfColumnsInDcw);
        numberOfColumnsOfAudit = Integer.parseInt(numberOfColumnsInAudit);
        primaryKeyColumnOfDcw = Integer.parseInt(primaryKeyColumnofDcw);
        primaryKeyColumnOfAudit = Integer.parseInt(primaryKeyColumnofAudit);
        
        File auditFilePath = new File(auditPath);
        File dcwFilePath = new File (dcwPath); 
        File partialMismatchFilePath = new File("partialMismatch.csv");
        File comparisionFilePath = new File("comparisionFile1.csv");
        new ComparingDcwAndAudit().generatingComparisionFile(auditFilePath,dcwFilePath,partialMismatchFilePath,comparisionFilePath);
    
        new ComparingDcwAndAudit().readingContentOfPartialMismatchFile(partialMismatchFilePath);
        File filteredAuditFilePath = new File("filteredAudit.csv");
        new ComparingDcwAndAudit().generatingFilteredAuditFile(auditFilePath,filteredAuditFilePath);
        new ComparingDcwAndAudit().generatingFinalMismatchFile(partialMismatchFilePath,filteredAuditFilePath,comparisionFilePath);
        
    }
    
    void generatingComparisionFile(File auditFilePath,File dcwFilePath,File partialMismatchFilePath,File comparisionFilePath) throws IOException
    {
        workBook = new HSSFWorkbook();
        creationHelper = workBook.getCreationHelper();
        comparisionSheet = workBook.createSheet("Comparision(DCW)");
        Sheet dcwSheet = workBook.createSheet("DCW");
        Sheet auditSheet = workBook.createSheet("AUDIT");
        
        CellStyle style = workBook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        
        CellStyle dcw_hlink_style=workBook.createCellStyle();
        Font hlink_font = workBook.createFont();
        hlink_font.setUnderline(Font.U_SINGLE);
        hlink_font.setColor(IndexedColors.BLUE.getIndex());
        dcw_hlink_style.setFont(hlink_font);
        
        Cell headerCell;
        CellStyle headerStyle=workBook.createCellStyle();
        Font header_font = workBook.createFont();
        header_font.setColor(IndexedColors.WHITE.getIndex());
        headerStyle.setFillForegroundColor(IndexedColors.DARK_GREEN.getIndex());
        
        headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        headerStyle.setFont(header_font);
        
        auditHyperLinkStyle=workBook.createCellStyle();
        auditHyperLinkStyle.setFont(hlink_font);
        
        inDomainStyle = workBook.createCellStyle();
        inDomainStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        inDomainStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        
        comparisionSheetRows=0;
        addToDomainCount=0;
        inDomainCount=0;
        totalNumberOfAuditRows=0;
        int r = 0;
        BufferedReader audit1 = new BufferedReader(new FileReader(auditFilePath));
        String row1;
        String[] rowContentInArray1 = new String[1000000];
        String rowContent1;
        HashMap<String, String> fileMap1 = new HashMap<>();
        
        headerLineInAudit = audit1.readLine();
        excelRow = auditSheet.createRow((short) r++);
        headerLineOfAudit = headerLineInAudit.split(",");
        for (int z = 0; z < headerLineOfAudit.length; z++)
        {
            headerCell=excelRow.createCell(z);
            headerCell.setCellValue(creationHelper.createRichTextString(headerLineOfAudit[z]));
            headerCell.setCellStyle(headerStyle);
        }

        while ((row1 = audit1.readLine()) != null)
        {  
            totalNumberOfAuditRows++;
            rowContentInArray1 = row1.split(",");
            excelRow = auditSheet.createRow((short) r++);
            
            for (int z = 0; z < rowContentInArray1.length; z++)
                excelRow.createCell(z)
                .setCellValue(creationHelper.createRichTextString(rowContentInArray1[z]));
            
            rowContent1=rowContentInArray1[primaryKeyColumnOfAudit];
            fileMap1.put(rowContent1, null);   
        }
        audit1.close();
        
        audit = new BufferedReader(new FileReader(auditFilePath));
        headerLineInAudit = audit.readLine();
        while ((row = audit.readLine()) != null) 
        {  
            rowContentOfAuditInArray = row.split(",");
            rowContent=null;
            int j=0;
            for(j=0;j<numberOfColumnsOfAudit;j++)
            {    
                rowContent=rowContent+rowContentOfAuditInArray[j];
            }
            fileMap.put(rowContent, null);
        }
        audit.close();
        
        int r1 = 0;
        BufferedReader dcw = new BufferedReader(new FileReader(dcwFilePath));
        printInComparisionFile = new PrintWriter(new FileWriter(comparisionFilePath));
        PrintWriter printInPartialMismatchFile = new PrintWriter(new FileWriter(partialMismatchFilePath));
        dcwRowNumber=1;
        rowCountInDcw=0;
        okCount=0;
        
        headerLineInDcw = dcw.readLine();
        Row excelRow1 = dcwSheet.createRow((short) r1++);
        excelRow = comparisionSheet.createRow((short) comparisionSheetRows++);
        headerCell=excelRow.createCell(0);
        headerCell.setCellValue(mismatchPercentage);
        headerCell.setCellStyle(headerStyle);
        headerLineOfDcw = headerLineInDcw.split(",");
        for (int z = 0; z < headerLineOfDcw.length; z++)
        {
            headerCell=excelRow1.createCell(z);
            headerCell.setCellValue(creationHelper.createRichTextString(headerLineOfDcw[z]));
            headerCell.setCellStyle(headerStyle);
            
            headerCell=excelRow.createCell(z+1);
            headerCell.setCellValue(creationHelper.createRichTextString(headerLineOfDcw[z]));
            headerCell.setCellStyle(headerStyle);
        }
        headerCell=excelRow.createCell(headerLineOfDcw.length+1);
        headerCell.setCellValue("Link to DCW Rows");
        headerCell.setCellStyle(headerStyle);
        
        headerCell=excelRow.createCell(headerLineOfDcw.length+2);
        headerCell.setCellValue("Link to AUDIT Rows");
        headerCell.setCellStyle(headerStyle);
        
        while ((row = dcw.readLine()) != null) 
        {
            
            rowCountInDcw++;
            dcwRowNumber++;
            rowContentOfDcwInArray = row.split(",");
            
            excelRow1 = dcwSheet.createRow((short) r1++);
            for (int z = 0; z < rowContentOfDcwInArray.length; z++)
            {
                excelRow1.createCell(z)
                .setCellValue(creationHelper.createRichTextString(rowContentOfDcwInArray[z]));
            }
            int j=0;
            rowContent=null;
            for(j=0;j<numberOfColumnsOfDcw;j++)
            {    
                rowContent=rowContent+rowContentOfDcwInArray[j];
            }
            
            
            if (fileMap.containsKey(rowContent)) 
            { 
                excelRow = comparisionSheet.createRow((short) comparisionSheetRows++);
                cell = excelRow.createCell(0);
                cell.setCellValue("Ok");
                
                okCount++;
                printInComparisionFile.write("Ok"+",");
                int i=0;
                for(i=0;i<numberOfColumnsOfDcw;i++)
                {
                    cell = excelRow.createCell(i+1);
                    cell.setCellValue(creationHelper.createRichTextString(rowContentOfDcwInArray[i]));
                    
                    printInComparisionFile.write(rowContentOfDcwInArray[i]+",");
                }
                cell = excelRow.createCell(i+1);
                cell.setCellValue(dcwRowNumber);
                printInComparisionFile.write(dcwRowNumber+","+"\n"); 
            }
            else 
            { 
                if (fileMap1.containsKey(rowContentOfDcwInArray[0])) 
                {
                    inDomainCount++;
                    
                    printInPartialMismatchFile.write("PartialMismatch"+",");
                    int i=0;
                    for(i=0;i<numberOfColumnsOfDcw;i++)
                    {
                        printInPartialMismatchFile.write(rowContentOfDcwInArray[i]+",");
                    }
                    printInPartialMismatchFile.write(dcwRowNumber+","+"\n"); 
                }
                else
                {
                    addToDomainCount++;
                    
                    excelRow = comparisionSheet.createRow((short) comparisionSheetRows++);
                    cell = excelRow.createCell(0);
                    cell.setCellValue("Add to Domain");
                    cell.setCellStyle(style);
                    
                    printInComparisionFile.write("Add to Domain"+",");
                    
                    int i=0;
                    for(i=0;i<numberOfColumnsOfDcw;i++)    
                    {
                        cell = excelRow.createCell(i+1);
                        cell.setCellValue(creationHelper.createRichTextString(rowContentOfDcwInArray[i]));
                        cell.setCellStyle(style);
                        printInComparisionFile.write(rowContentOfDcwInArray[i]+",");
                    }
                    cell = excelRow.createCell(i+1);
                    cell.setCellValue(dcwRowNumber);
                    HSSFHyperlink link2 = new HSSFHyperlink(HSSFHyperlink.LINK_DOCUMENT);
                    link2.setAddress("'dcw'!A"+dcwRowNumber);
                    cell.setHyperlink(link2);
                    cell.setCellStyle(dcw_hlink_style);
                    printInComparisionFile.write(dcwRowNumber+","+"\n");
                }
            }
        }
        
        for(int b=0;b<numberOfColumnsOfDcw;b++)
        {
            dcwSheet.autoSizeColumn(b);
            auditSheet.autoSizeColumn(b);
        }

        dcw.close();
        printInPartialMismatchFile.close();
        mismatchCount=rowCountInDcw-okCount;
        
        DecimalFormat decimal = new DecimalFormat("#.##");
        okPercentage=decimal.format((((float)okCount)/((float)rowCountInDcw))*100);
        mismatchPercentage=decimal.format((((float)mismatchCount/((float)rowCountInDcw)))*100);
        addToDomainPercentage=decimal.format((((float)addToDomainCount/((float)rowCountInDcw)))*100);
        inDomainPercentage=decimal.format((((float)inDomainCount/((float)rowCountInDcw)))*100);
        totalNumberOfDcwRows=dcwRowNumber-1;
        
        //fileOut = new FileOutputStream("result.xls",true);
        //wb.write(fileOut);
        //fileOut.close();
        //printInComparisionFile.close();
    }
    
    void readingContentOfPartialMismatchFile(File partialMismatchFilePath) throws IOException
    {
        partialMismatch = new BufferedReader(new FileReader(partialMismatchFilePath));
        while ((row = partialMismatch.readLine()) != null) 
        {
            rowContentOfDcwInArray = row.split(",");
            rowContent=rowContentOfDcwInArray[primaryKeyColumnOfDcw+1];
            fileMap1.put(rowContent, null);
        }
        partialMismatch.close();
    }
    void generatingFilteredAuditFile(File auditFilePath, File filteredAuditFilePath ) throws IOException
    {
        auditRowNumber=0;
        audit = new BufferedReader(new FileReader(auditFilePath)) ;
        PrintWriter printInFilteredAuditFile = new PrintWriter(new FileWriter(filteredAuditFilePath));
        while ((row = audit.readLine()) != null) 
        {
            auditRowNumber++;
            rowContentOfAuditInArray = row.split(",");
            rowContent=rowContentOfAuditInArray[primaryKeyColumnOfAudit];
            if (fileMap1.containsKey(rowContent)) 
            { 
                int i=0;
                for(i=0;i<numberOfColumnsOfAudit;i++)
                {
                    printInFilteredAuditFile.write(rowContentOfAuditInArray[i]+",");
                }
                printInFilteredAuditFile.write(","+auditRowNumber+","+"\n");
            }
          }
        printInFilteredAuditFile.close();
        audit.close(); 
    }
    void generatingFinalMismatchFile(File partialMismatchFilePath, File filteredAuditFilePath, File comparisionFilePath) throws IOException
    {
        
        partialMismatch = new BufferedReader(new FileReader(partialMismatchFilePath));
        BufferedReader filteredAudit = null;
        String row1;
        String[] rowContentInArray1 = new String[1000000];
        while ((row = partialMismatch.readLine()) != null) 
        {
            rowContentOfDcwInArray = row.split(",");
            int k=1;   
            
            filteredAudit = new BufferedReader(new FileReader(filteredAuditFilePath)) ;
            while ((row1 = filteredAudit.readLine())!= null)
            {
                rowContentInArray1= row1.split(",");   
                int g=0;            
                if((rowContentOfDcwInArray[k]).equals(rowContentInArray1[g]))   
                {
                    excelRow = comparisionSheet.createRow((short) comparisionSheetRows++);
                    cell = excelRow.createCell(0);
                    cell.setCellValue("In Domain As");
                    cell.setCellStyle(inDomainStyle);
                    
                    cell = excelRow.createCell(1);
                    cell.setCellValue(creationHelper.createRichTextString(rowContentOfDcwInArray[k]));
                    printInComparisionFile.write("In Domain As"+","+rowContentOfDcwInArray[k]+",");
                    int i=1;                   
                    for(i=1;i<numberOfColumnsOfDcw;i++)
                    {
                        if(!(rowContentOfDcwInArray[i+1]).equals(rowContentInArray1[i]))    
                        {
                            
                            cell = excelRow.createCell(i+1);
                            cell.setCellValue("in Audit as"+" "+"\""+creationHelper.createRichTextString(rowContentInArray1[i])+"\""+" "+"in DCW as"+" "+"\""+creationHelper.createRichTextString(rowContentOfDcwInArray[i+1])+"\"");
                            cell.setCellStyle(inDomainStyle);
                            
                            printInComparisionFile.write("in Audit as"+" "+"\""+rowContentInArray1[i]+"\""+" "+"in DCW as"+" "+"\""+rowContentOfDcwInArray[i+1]+"\""+",");
                        }
                        else
                        {
                            cell = excelRow.createCell(i+1);
                            cell.setCellValue(creationHelper.createRichTextString(rowContentInArray1[i]));
                            
                            printInComparisionFile.write(rowContentInArray1[i]+",");
                        }
                    }
                    cell = excelRow.createCell(i+1);
                    cell.setCellValue(creationHelper.createRichTextString(rowContentOfDcwInArray[numberOfColumnsOfDcw+1]));
                    HSSFHyperlink link1 = new HSSFHyperlink(HSSFHyperlink.LINK_DOCUMENT);
                    link1.setAddress("'dcw'!A"+rowContentOfDcwInArray[numberOfColumnsOfDcw+1]);
                    cell.setHyperlink(link1);
                    cell.setCellStyle(auditHyperLinkStyle);
                    comparisionSheet.autoSizeColumn(i+1);


                    cell = excelRow.createCell(i+2);
                    cell.setCellValue(creationHelper.createRichTextString(rowContentInArray1[numberOfColumnsOfDcw+1]));
                    HSSFHyperlink link3 = new HSSFHyperlink(HSSFHyperlink.LINK_DOCUMENT);
                    link3.setAddress("'audit'!A"+rowContentInArray1[numberOfColumnsOfDcw+1]);
                    cell.setHyperlink(link3);
                    cell.setCellStyle(auditHyperLinkStyle);
                    comparisionSheet.autoSizeColumn(i+2);
                    
                    printInComparisionFile.write(rowContentOfDcwInArray[numberOfColumnsOfDcw+1]+","+rowContentInArray1[numberOfColumnsOfDcw+1]);
                    printInComparisionFile.write("\n"); 
                    break;
                }
            }
        }
        
        for(int a=0;a<numberOfColumnsOfDcw+1;a++)
            comparisionSheet.autoSizeColumn(a);
        
        FileOutputStream fileOutputStream;
        fileOutputStream = new FileOutputStream("result.xls");
        workBook.write(fileOutputStream);
        fileOutputStream.close();
        partialMismatch.close();
        filteredAudit.close();
        printInComparisionFile.close();
    }
    
}