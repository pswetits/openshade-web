package models;

import models.table.*;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.io.File;

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

    public JTable2Pdf(JTable table) throws Exception {
        this.table = table;
        createTable();
    }


    public void createTable() {
        frame = new JFrame();
        scrollPane = new JScrollPane(table);
        frame.setContentPane(scrollPane);
        frame.pack();



        
        try{
            BufferedImage bi = ScreenImage.createImage(scrollPane);
            ScreenImage.writeImage(bi, "Screen-Image.jpg");
        } catch(Exception e){
            e.printStackTrace();
        } 
    }
}



