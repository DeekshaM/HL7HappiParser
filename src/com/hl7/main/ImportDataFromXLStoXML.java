package com.hl7.main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vmanchala
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ImportDataFromXLStoXML {

public ImportDataFromXLStoXML() {
}

public void displayFromExcel(String xlsPath) {
    InputStream inputStream = null;
    try {
        inputStream = new FileInputStream(xlsPath);
    } catch (FileNotFoundException e) {
        System.out.println("File not found in the specified path.");
        e.printStackTrace();
    }

    POIFSFileSystem fileSystem = null;

    try {
        // Initializing the XML document
        DocumentBuilderFactory factory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();
        Element rootElement = document.createElement("categories");
        document.appendChild(rootElement);
        fileSystem = new POIFSFileSystem(inputStream);
        HSSFWorkbook workBook = new HSSFWorkbook(fileSystem);
        Integer count = workBook.getNumberOfSheets();
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < count; i++) {
            HSSFSheet sheet = workBook.getSheetAt(i);
            Iterator<?> rows = sheet.rowIterator();
            while (rows.hasNext()) {
                HSSFRow row = (HSSFRow) rows.next();
                Iterator<?> cells = row.cellIterator();
                ArrayList<String> rowData = new ArrayList<String>();
                while (cells.hasNext()) {
                    HSSFCell cell = (HSSFCell) cells.next();
                    String richTextString = cell.getStringCellValue();
                    System.out.println("String: " + richTextString);
                    rowData.add(richTextString);
                } // end while
                data.add(rowData);
            } // end while
        }

        int numOfProduct = data.size();
        for (int i = 0; i < numOfProduct; i++) {
            Element productElement = document.createElement("categoryName");
            rootElement.appendChild(productElement);

            int index = 0;
            for (String s : data.get(i)) {
                String headerString = data.get(0).get(index);
                if (data.get(0).get(index).equals("image link")) {
                    headerString = "image_link";
                }

                if (data.get(0).get(index).equals("product type")) {
                    headerString = "product_type";
                }

                Element headerElement = document
                        .createElement(headerString);
                productElement.appendChild(headerElement);
                headerElement.appendChild(document.createTextNode(s));
                index++;
            }
        }

        TransformerFactory tFactory = TransformerFactory.newInstance();

        Transformer transformer = tFactory.newTransformer();
        // Add indentation to output
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(
                "{http://xml.apache.org/xslt}indent-amount", "2");

        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File("products.xml"));
        // StreamResult result = new StreamResult(System.out);
        transformer.transform(source, result);

    } catch (IOException e) {
        System.out.println("IOException " + e.getMessage());
    } catch (ParserConfigurationException e) {
        System.out
                .println("ParserConfigurationException " + e.getMessage());
    } catch (TransformerConfigurationException e) {
        System.out.println("TransformerConfigurationException "
                + e.getMessage());
    } catch (TransformerException e) {
        System.out.println("TransformerException " + e.getMessage());
    }
}

public static void main(String[] args) {
    ImportDataFromXLStoXML poiExample = new ImportDataFromXLStoXML();
    String xlsPath = "C:\\Users\\vmanchala\\Desktop\\Patient Demographics.csv";///home/vpanchal/installApps/workspace/QTOExport/output/Ptest.xls";
    poiExample.displayFromExcel(xlsPath);
}
}