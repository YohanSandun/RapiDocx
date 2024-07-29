package com.yohan.rapidocx;

import com.yohan.rapidocx.utils.HtmlHelper;
import com.yohan.rapidocx.utils.ZipHelper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipOutputStream;

public class HtmlToDocxConverter {

    public byte[] convert(String sourceHtml, DocumentProperties properties)
            throws IOException {

        Document document = Jsoup.parse(sourceHtml);
        replaceSemanticElementsWithDivs(document);
        convertInlineCSSIntoClasses(document);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);

        ZipHelper.addFileToZip(
                "[Content_Types].xml",
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                        "<Types xmlns=\"http://schemas.openxmlformats.org/package/2006/content-types\">\n" +
                        "  <Default Extension=\"rels\" ContentType=\n" +
                        "    \"application/vnd.openxmlformats-package.relationships+xml\" />\n" +
                        "  <Override PartName=\"/word/document.xml\" ContentType=\n" +
                        "    \"application/vnd.openxmlformats-officedocument.wordprocessingml.document.main+xml\"/>\n" +
                        "  <Override PartName=\"/word/afchunk.mht\" ContentType=\"message/rfc822\"/>\n" +
                        "</Types>\n",
                zipOutputStream);

        ZipHelper.addFileToZip(
                "_rels/.rels",
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                        "<Relationships xmlns=\"http://schemas.openxmlformats.org/package/2006/relationships\">\n" +
                        "  <Relationship\n" +
                        "      Type=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships/officeDocument\"\n" +
                        "      Target=\"/word/document.xml\" Id=\"R09c83fafc067488e\" />\n" +
                        "</Relationships>",
                zipOutputStream);

        ZipHelper.addFileToZip(
                "word/document.xml",
                properties.getAsXml(),
                zipOutputStream);

        ZipHelper.addFileToZip("word/afchunk.mht", convertHtmlToMht(document.html()), zipOutputStream);

        ZipHelper.addFileToZip(
                "word/_rels/document.xml.rels",
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                        "<Relationships xmlns=\"http://schemas.openxmlformats.org/package/2006/relationships\">\n" +
                        "  <Relationship Type=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships/aFChunk\"\n" +
                        "    Target=\"/word/afchunk.mht\" Id=\"htmlChunk\" />\n" +
                        "</Relationships>",
                zipOutputStream);

        zipOutputStream.flush();
        zipOutputStream.close();

        byte[] zipData = outputStream.toByteArray();
        outputStream.close();
        return zipData;
    }

    private void replaceSemanticElementsWithDivs(Document document) {
        String[] elements = {
                "main", "header", "footer"
        };
        for (String element : elements) {
            replaceElementsWithDivs(document, element);
        }
    }

    private void replaceElementsWithDivs(Document document, String elementToReplace) {
        Elements elements = document.getElementsByTag(elementToReplace);
        for (Element element : elements) {
            Element divElement = new Element("div");
            divElement.attributes().addAll(element.attributes());
            divElement.html(element.html());
            element.replaceWith(divElement);
        }
    }

    private Document convertInlineCSSIntoClasses(Document document) {
        Map<String, String> cssClassMap = new HashMap<>();
        int classCounter = 1;

        Elements elements = document.select("[style]");

        for (Element element: elements) {
            String style = element.attr("style");
            String convertedStyle = HtmlHelper.convertHslToRgb(style);
            String className = cssClassMap.get(convertedStyle);

            if (className == null) {
                className = "class" + classCounter++;
                cssClassMap.put(convertedStyle, className);
            }

            element.removeAttr("style");
            element.addClass(className);
        }

        StringBuilder classes = new StringBuilder();
        for (Map.Entry<String, String> entry : cssClassMap.entrySet()) {
            classes.append(".")
                    .append(entry.getValue())
                    .append(" {")
                    .append(entry.getKey())
                    .append("}\n");
        }

        Element styleTag = document.head().getElementsByTag("style").first();
        if (styleTag != null) {
            styleTag.append(classes.toString());
        } else {
            document.head().append("<style>\n" + classes + "\n</style>\n");
        }
        return document;
    }

    private String convertHtmlToMht(String html) {
        html = html.replaceAll("=", "=3D");
        return "MIME-Version: 1.0\n" +
                "Content-Type: multipart/related;\n" +
                "    type=\"text/html\";\n" +
                "    boundary=\"----=mhtDocumentPart\"\n" +
                "\n" +
                "\n" +
                "------=mhtDocumentPart\n" +
                "Content-Type: text/html;\n" +
                "    charset=\"utf-8\"\n" +
                "Content-Transfer-Encoding: quoted-printable\n" +
                "Content-Location: file:///C:/fake/document.html\n" +
                "\n" +
                html +
                "\n" +
                "\n" +
                "\n" +
                "------=mhtDocumentPart--\n";
    }

}
