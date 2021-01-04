package fr.insa.messenger.client.ui.utils;

import java.awt.*;
import javax.swing.text.JTextComponent;

/**
 * @author Damien MOLINA
 */
public class Placeholder {

    /**
     * Graphic instance to draw the placeholder.
     */
    private Graphics2D graphic ;

    /**
     * JComponent instance.
     */
    private final JTextComponent component ;

    /**
     * @see this.calculateX()
     */
    private float alphaX1 ;

    /**
     * @see this.calculateX()
     */
    private float alphaX2 ;

    /**
     * @see this.calculateY()
     */
    private float alphaY ;

    /**
     * Make a new Placeholder instance.
     *
     * @param component : JComponent instance.
     */
    public Placeholder(JTextComponent component) {
        this.component = component ;
    }

    /**
     * Set the graphic instance.
     *
     * @param graphic : drawer instance.
     */
    public void setGraphic(final Graphics2D graphic) {
        this.graphic = graphic ;
    }

    /**
     * Set the graphic instance.
     *
     * @param graphic : drawer instance.
     */
    public void setGraphic(final Graphics graphic) {
        this.setGraphic((Graphics2D) graphic) ;
    }

    /**
     * Draw the given message.
     *
     * @param message : message to draw.
     */
    public void draw(String message) {
        this.graphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON) ;
        this.graphic.setColor(this.component.getDisabledTextColor()) ;
        this.graphic.drawString(message, this.calculateX(), this.calculateY()) ;
    }

    /**
     * Determine whether the placeholder
     * should be drawn.
     *
     * @return True or False
     */
    public boolean shouldDraw() {
        return this.component.getText().length() <= 0 ;
    }

    /**
     * Set the coefficient to correctly
     * locate the placeholder text.
     *
     * @param x1 : see this.calculateX()
     * @param x2 : see this.calculateX()
     * @param y  : see this.calculateY()
     */
    public void setAlpha(float x1, float x2, float y) {
        this.alphaX1 = x1 ;
        this.alphaX2 = x2 ;
        this.alphaY  = y ;
    }

    /**
     * Calculate the Placeholder-X position.
     *
     * @return the X location.
     */
    private float calculateX() {
        return this.alphaY * this.component.getInsets().left ;
    }

    /**
     * Calculate the Placeholder-Y position.
     *
     * @return the Y location.
     */
    private float calculateY() {
        float result = this.alphaX1 * this.graphic.getFontMetrics().getMaxAscent() ;

        if(this.alphaX2 != 0) {
            result += this.component.getInsets().top / this.alphaX2 ;
        }

        return result ;
    }

}
