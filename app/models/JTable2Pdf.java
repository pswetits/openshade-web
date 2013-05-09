package models;

import models.table.*;
import models.s3.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.io.*;

import javax.swing.*;
import javax.imageio.ImageIO;

/*
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
*/

public class JTable2Pdf {
    private JTable table;
    private JFrame frame;
    JScrollPane scrollPane;
    String fileName;
    File outputfile;

    public JTable2Pdf(JTable table, String fileName) throws Exception {
        this.table = table;
        this.fileName = fileName;

        createTable();
    }


    public void createTable() {
        frame = new JFrame();
        scrollPane = new JScrollPane(table);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setFillsViewportHeight(true);
        //scrollPane.setPreferredSize();
        scrollPane.getHorizontalScrollBar().setVisible(false);
        frame.setContentPane(scrollPane);
        frame.pack();



        
        try{
            BufferedImage bi = ScreenImage.createImage(scrollPane);
            //file = new File(bi);

            //ScreenImage.writeImage(bi, fileName + ".jpg");
            outputfile = new File(fileName+".jpg");
            ImageIO.write(bi, "png", outputfile);
            upload();
        } catch(Exception e){
            e.printStackTrace();
        } 
    } 

    public void upload(){
        //S3Driver s3 = new S3Driver(outputfile, fileName);
    }

}



