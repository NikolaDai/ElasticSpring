package com.w3dai.dao;

import java.io.IOException;

public class ImportDataFromFilesTest {
    public static void main(String[] args) throws IOException {
        ImportDataFromFiles aImportData = new ImportDataFromFiles();
        aImportData.ImportData("test.txt");
    }
}
