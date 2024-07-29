package com.yohan.rapidocx;

public class DocumentProperties {
    public enum Orientation {
        PORTRAIT("portrait"), LANDSCAPE("landscape");

        private final String description;

        Orientation(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return description;
        }
    }

    private Integer pageWidth;
    private Integer pageHeight;
    private Orientation orientation;
    private Integer marginTop;
    private Integer marginLeft;
    private Integer marginRight;
    private Integer marginBottom;
    private Integer marginHeader;
    private Integer marginFooter;
    private Integer marginGutter;

    public DocumentProperties(Builder builder) {
        this.pageWidth = builder.pageWidth;
        this.pageHeight = builder.pageHeight;
        this.orientation = builder.orientation;
        this.marginTop = builder.marginTop;
        this.marginLeft = builder.marginLeft;
        this.marginRight = builder.marginRight;
        this.marginBottom = builder.marginBottom;
        this.marginHeader = builder.marginHeader;
        this.marginFooter = builder.marginFooter;
        this.marginGutter = builder.marginGutter;
    }

    public String getAsXml() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<w:document\n" +
                "  xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\"\n" +
                "  xmlns:m=\"http://schemas.openxmlformats.org/officeDocument/2006/math\"\n" +
                "  xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\"\n" +
                "  xmlns:wp=\"http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing\"\n" +
                "  xmlns:a=\"http://schemas.openxmlformats.org/drawingml/2006/main\"\n" +
                "  xmlns:ns6=\"http://schemas.openxmlformats.org/schemaLibrary/2006/main\"\n" +
                "  xmlns:c=\"http://schemas.openxmlformats.org/drawingml/2006/chart\"\n" +
                "  xmlns:ns8=\"http://schemas.openxmlformats.org/drawingml/2006/chartDrawing\"\n" +
                "  xmlns:dgm=\"http://schemas.openxmlformats.org/drawingml/2006/diagram\"\n" +
                "  xmlns:pic=\"http://schemas.openxmlformats.org/drawingml/2006/picture\"\n" +
                "  xmlns:ns11=\"http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing\"\n" +
                "  xmlns:dsp=\"http://schemas.microsoft.com/office/drawing/2008/diagram\"\n" +
                "  xmlns:ns13=\"urn:schemas-microsoft-com:office:excel\"\n" +
                "  xmlns:o=\"urn:schemas-microsoft-com:office:office\"\n" +
                "  xmlns:v=\"urn:schemas-microsoft-com:vml\"\n" +
                "  xmlns:w10=\"urn:schemas-microsoft-com:office:word\"\n" +
                "  xmlns:ns17=\"urn:schemas-microsoft-com:office:powerpoint\"\n" +
                "  xmlns:odx=\"http://opendope.org/xpaths\"\n" +
                "  xmlns:odc=\"http://opendope.org/conditions\"\n" +
                "  xmlns:odq=\"http://opendope.org/questions\"\n" +
                "  xmlns:odi=\"http://opendope.org/components\"\n" +
                "  xmlns:odgm=\"http://opendope.org/SmartArt/DataHierarchy\"\n" +
                "  xmlns:ns24=\"http://schemas.openxmlformats.org/officeDocument/2006/bibliography\"\n" +
                "  xmlns:ns25=\"http://schemas.openxmlformats.org/drawingml/2006/compatibility\"\n" +
                "  xmlns:ns26=\"http://schemas.openxmlformats.org/drawingml/2006/lockedCanvas\">\n" +
                "  <w:body>\n" +
                "    <w:altChunk r:id=\"htmlChunk\" />\n" +
                "    <w:sectPr>\n" +
                "      <w:pgSz w:w=\""+pageWidth+"\" w:h=\""+pageHeight+"\" w:orient=\""+orientation+"\" />\n" +
                "      <w:pgMar w:top=\""+marginTop+"\"\n" +
                "               w:right=\""+marginRight+"\"\n" +
                "               w:bottom=\""+marginBottom+"\"\n" +
                "               w:left=\""+marginLeft+"\"\n" +
                "               w:header=\""+marginHeader+"\"\n" +
                "               w:footer=\""+marginFooter+"\"\n" +
                "               w:gutter=\""+marginGutter+"\"/>\n" +
                "    </w:sectPr>\n" +
                "  </w:body>\n" +
                "</w:document>\n";
    }

    public Integer getPageWidth() {
        return pageWidth;
    }

    public void setPageWidth(Integer pageWidth) {
        this.pageWidth = pageWidth;
    }

    public Integer getPageHeight() {
        return pageHeight;
    }

    public void setPageHeight(Integer pageHeight) {
        this.pageHeight = pageHeight;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public Integer getMarginTop() {
        return marginTop;
    }

    public void setMarginTop(Integer marginTop) {
        this.marginTop = marginTop;
    }

    public Integer getMarginLeft() {
        return marginLeft;
    }

    public void setMarginLeft(Integer marginLeft) {
        this.marginLeft = marginLeft;
    }

    public Integer getMarginRight() {
        return marginRight;
    }

    public void setMarginRight(Integer marginRight) {
        this.marginRight = marginRight;
    }

    public Integer getMarginBottom() {
        return marginBottom;
    }

    public void setMarginBottom(Integer marginBottom) {
        this.marginBottom = marginBottom;
    }

    public Integer getMarginHeader() {
        return marginHeader;
    }

    public void setMarginHeader(Integer marginHeader) {
        this.marginHeader = marginHeader;
    }

    public Integer getMarginFooter() {
        return marginFooter;
    }

    public void setMarginFooter(Integer marginFooter) {
        this.marginFooter = marginFooter;
    }

    public Integer getMarginGutter() {
        return marginGutter;
    }

    public void setMarginGutter(Integer marginGutter) {
        this.marginGutter = marginGutter;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer pageWidth = 12240;
        private Integer pageHeight = 15840;
        private Orientation orientation = Orientation.PORTRAIT;
        private Integer marginTop = 1440;
        private Integer marginLeft = 1440;
        private Integer marginRight = 1440;
        private Integer marginBottom = 1440;
        private Integer marginHeader = 720;
        private Integer marginFooter = 720;
        private Integer marginGutter = 0;

        public Builder setPageWidth(Integer pageWidth) {
            this.pageWidth = pageWidth;
            return this;
        }

        public Builder setPageHeight(Integer pageHeight) {
            this.pageHeight = pageHeight;
            return this;
        }

        public Builder setOrientation(Orientation orientation) {
            this.orientation = orientation;
            return this;
        }

        public Builder setMarginTop(Integer marginTop) {
            this.marginTop = marginTop;
            return this;
        }

        public Builder setMarginLeft(Integer marginLeft) {
            this.marginLeft = marginLeft;
            return this;
        }

        public Builder setMarginRight(Integer marginRight) {
            this.marginRight = marginRight;
            return this;
        }

        public Builder setMarginBottom(Integer marginBottom) {
            this.marginBottom = marginBottom;
            return this;
        }

        public Builder setMarginHeader(Integer marginHeader) {
            this.marginHeader = marginHeader;
            return this;
        }

        public Builder setMarginFooter(Integer marginFooter) {
            this.marginFooter = marginFooter;
            return this;
        }

        public Builder setMarginGutter(Integer marginGutter) {
            this.marginGutter = marginGutter;
            return this;
        }

        public DocumentProperties build() {
            return new DocumentProperties(this);
        }
    }
}
