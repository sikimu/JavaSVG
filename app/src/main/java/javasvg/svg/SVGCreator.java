// BEGIN: ed8c6549bwf9
package javasvg.svg;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

public class SVGCreator {

    public static void createSVG(String filePath, ArrayList<JavaSVG> svgs) throws Exception {
        // Get a DOMImplementation.
        DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();

        // Create an instance of org.w3c.dom.Document.
        String svgNS = "http://www.w3.org/2000/svg";
        Document document = domImpl.createDocument(svgNS, "svg", null);

        // Create an instance of the SVG Generator.
        SVGGraphics2D svgGenerator = new SVGGraphics2D(document);

        // Set the background color.
        svgGenerator.setBackground(Color.WHITE);

        // Set the drawing area.
        Dimension size = new Dimension(1024, 1024);
        Rectangle2D.Double drawingArea = new Rectangle2D.Double(0, 0, size.width, size.height);
        svgGenerator.setSVGCanvasSize(size);

        for (JavaSVG svg : svgs) {
            svgGenerator.setPaint(Color.RED);
            svgGenerator.fill(drawingArea);
            svgGenerator.setPaint(Color.BLACK);
            svgGenerator.drawString("svgNS", 0, 100);
        }

        // Write the SVG file.
        Writer out = new OutputStreamWriter(new FileOutputStream(new File(filePath)), "UTF-8");
        svgGenerator.stream(out, true /* use css */);
        out.flush();
        out.close();
    }
}
// END: ed8c6549bwf9