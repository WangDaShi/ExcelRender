package com.loatr.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.function.Consumer;

public class ExcelTools {
    public static File genExcel(String filePath, File template, int sheetNum, Consumer<Sheet> fill) throws IOException {
        File file = new File(filePath);
        boolean isXlsx = filePath.endsWith(".xlsx");
        FileOutputStream out = null;
        InputStream in = null;
        Workbook workbook = null;
        try
        {
            in = new FileInputStream(template);
            workbook = createWorkbook(in, isXlsx);
            out = new FileOutputStream(file);
            workbook.setSheetName(sheetNum, "test");
            Sheet sheet = workbook.getSheetAt(sheetNum);
            fill.accept(sheet);
            workbook.write(out);
            out.flush();
        }
        finally
        {
            closeQuietly(out);
            closeQuietly(in);
            closeQuietly(workbook);
        }
        return file;
    }

    private static Workbook createWorkbook(InputStream in, boolean xlsx) throws IOException
    {
        return xlsx ? new XSSFWorkbook(in) : new HSSFWorkbook(new POIFSFileSystem(in));
    }


    private static void closeQuietly(Closeable closeable)
    {
        try
        {
            if (closeable != null) closeable.close();
        }
        catch (IOException ioe)
        {
            // ignore
        }
    }
}
