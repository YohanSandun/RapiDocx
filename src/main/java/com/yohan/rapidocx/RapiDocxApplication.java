package com.yohan.rapidocx;

import java.io.FileOutputStream;
import java.io.IOException;

public class RapiDocxApplication {
    public static void main(String[] args) throws IOException {

        DocumentProperties properties = DocumentProperties.builder().build();

        HtmlToDocxConverter converter = new HtmlToDocxConverter();
        byte[] docx = converter.convert("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>Simple HTML Page</title>\n" +
                "    <style>\n" +
                "        .body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            margin: 20px;\n" +
                "            background-color: rgb(200,200,200);\n" +
                "        }\n" +
                "        header {\n" +
                "            background: rgb(57,154,142);\n" +
                "            color: #ffffff;\n" +
                "            padding: 10px 20px;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "        .main {\n" +
                "            background: #ffffff;\n" +
                "            padding: 20px;\n" +
                "            border-radius: 5px;\n" +
                "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
                "        }\n" +
                "        .footer {\n" +
                "            margin-top: 20px;\n" +
                "            text-align: center;\n" +
                "            color: #399a8e;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body class=\"body\">\n" +
                "    <header>\n" +
                "        <h1>Welcome to My Simple HTML Page</h1>\n" +
                "    </header>\n" +
                "    <div class=\"main\">\n" +
                "        <h2>About This Page</h2>\n" +
                "        <p>This is a simple HTML page created as an example. It includes a header, main content section, and a footer.</p>\n" +
                "        \n" +
                "        <h3>Features</h3>\n" +
                "        <ul>\n" +
                "            <li>Basic HTML structure</li>\n" +
                "            <li>CSS for styling</li>\n" +
                "            <li>Responsive design</li>\n" +
                "        </ul>\n" +
                "\n" +
                "        <h3>Contact Information</h3>\n" +
                "        <p>If you have any questions, feel free to reach out at <a href=\"mailto:example@example.com\">example@example.com</a>.</p>\n" +
                "    </div>\n" +
                "    <div class=\"footer\">\n" +
                "        <p>&copy; 2024 My Simple HTML Page</p>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>", properties);

        FileOutputStream outputStream = new FileOutputStream("D:\\test.docx");
        outputStream.write(docx);
        outputStream.close();
    }
}
