package lionsCheckpointE.GUI;

import javax.swing.*;
import java.awt.*;

//https://tips4java.wordpress.com/2008/10/12/background-panel/
public class BackgroundPanel2 extends JPanel
{
    private Image background;

    public BackgroundPanel2(Image background)
    {
        this.background = background;
        setLayout( new BorderLayout() );
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        //g.drawImage(background, 0, 0, null); // image full size
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null); // image scaled
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(background.getWidth(this), background.getHeight(this));
    }
}