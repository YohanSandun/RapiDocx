package com.yohan.rapidocx.utils;

import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipHelper {
    public static void addFileToZip(String fileName,
                              String fileContent,
                              ZipOutputStream zipOutputStream)
            throws IOException {
        ZipEntry contentTypesEntry = new ZipEntry(fileName);
        zipOutputStream.putNextEntry(contentTypesEntry);
        zipOutputStream.write(fileContent.getBytes());
        zipOutputStream.closeEntry();
    }
}
