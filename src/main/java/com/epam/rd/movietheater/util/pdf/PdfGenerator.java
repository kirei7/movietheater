package com.epam.rd.movietheater.util.pdf;

import java.util.Map;

public interface PdfGenerator {
    byte[] generatePdf(Map<String, Object> model, String viewName);
}
