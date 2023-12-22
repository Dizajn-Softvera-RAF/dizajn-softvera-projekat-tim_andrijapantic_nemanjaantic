package app.controller;

import app.view.tabs.TabbedPane;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ScreenshotAction extends AbstractClassyAction{
    public ScreenshotAction() {
        putValue(SMALL_ICON, loadIcon("/images/screenshot.png"));
        putValue(NAME, "Screenshot");
        putValue(SHORT_DESCRIPTION, "Screenshot");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        BufferedImage image = new BufferedImage(TabbedPane.getInstance().getSelectedTab().getDiagramView().getWidth(), TabbedPane.getInstance().getSelectedTab().getDiagramView().getHeight(), BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics = image.createGraphics();

        TabbedPane.getInstance().getSelectedTab().getDiagramView().paint(graphics);

        JFileChooser getFile = new JFileChooser();
        getFile.setCurrentDirectory(new File(System.getProperty("user.home") + File.separator +"Desktop"));
        FileNameExtensionFilter filter1 = new FileNameExtensionFilter("*.Images", "jpg", "png");
        getFile.addChoosableFileFilter(filter1);
        int res = getFile.showSaveDialog(null);

        if (res == JFileChooser.APPROVE_OPTION) {
            File setFile1 = getFile.getSelectedFile();
            try {
                ImageIO.write(image, "png", setFile1);
                System.out.println("Exportovana slika dijagrama u: " + setFile1);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
