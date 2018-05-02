package com.epam.rd.movietheater.util.pdf;

import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.util.Map;

@Component
public class ThymeleafPdfGenerator implements PdfGenerator {

    private ISpringTemplateEngine templateEngine;

    @Autowired
    public ThymeleafPdfGenerator(ISpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    public byte[] generatePdf(Map<String, Object> model, String viewName) {
        Context context = createViewContext(model);
        String html = templateEngine.process(viewName, context);
        return pdfFromString(html);
    }

    private Context createViewContext(Map<String, Object> model) {
        Context context = new Context();
        model.forEach(context::setVariable);
        return context;
    }

    private byte[] pdfFromString(String content) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(content);
            renderer.layout();
            renderer.createPDF(outputStream);
            return outputStream.toByteArray();
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }
}
