/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JDesktopPane;
import javax.swing.border.Border;
 
/**
 *
 * @author Valentin Barros
 */
public class EscritorioPersonalizado extends JDesktopPane{
    private BufferedImage img;

    public EscritorioPersonalizado() {
        try{
            img = ImageIO.read(getClass().getResourceAsStream("/Imagenes/Fondo2.png"));
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(img, 0,0, null);
    }
}
