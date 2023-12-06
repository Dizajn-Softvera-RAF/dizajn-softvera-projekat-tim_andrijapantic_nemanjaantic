package app.view.painters;

import app.model.classcontent.Attribute;
import app.model.classcontent.ClassContent;
import app.model.classcontent.EnumType;
import app.model.classcontent.Method;
import app.model.diagcomposite.DiagramElement;
import app.model.diagimplementation.interclass.Interface;
import app.model.diagimplementation.interclass.Klasa;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class ClassPainter extends ElementPainter{
    protected Rectangle2D.Double shape;



    public ClassPainter(Klasa element) {
        setElement(element);
        this.setName(element.getName());
        setShape(shape);
    }

    @Override
    public void paint(Graphics2D g2) {
        g2.setColor(element.getCurrentColor());
        g2.setStroke(new BasicStroke(((Klasa)getElement()).getStroke()));
        this.shape = createShape((Klasa) getElement(), g2);
        setShape(shape);
        Font originalFont = g2.getFont();
        g2.fill(shape);
        g2.setColor(Color.BLACK);
        g2.draw(shape);
        g2.drawLine((int) shape.getX(), (int) shape.getY()+30, (int) ((int) shape.getX()+shape.getWidth()), (int) shape.getY()+30);
        ispisiContent(g2, (Klasa)getElement(), (int) (shape.getX() + 10), (int) (shape.getY()+50));
        g2.setFont(new Font("Arial", Font.BOLD, 13));
        g2.drawString(element.getName(), (int) shape.getX()+20, (int) shape.getY()+20);
        g2.setFont(originalFont);
        ((Klasa)element).getConnectionsDots().clear();
        int sredinaX = (int) (shape.x + shape.width / 2);
        int sredinaY = (int) (shape.y + shape.height / 2);
        addConnectionDots(sredinaX, sredinaY, ((Klasa) element).getConnectionsDots());
    }

    @Override
    public boolean elementAt(DiagramElement element, Point pos) {
        return getShape().contains(pos);
    }

    private Rectangle2D.Double createShape(Klasa klasa, Graphics2D g2) {
        int x = calculateWidth(klasa, g2) + 20;
        int y = 40 + klasa.getContent().size() * 20;
        return new Rectangle2D.Double(klasa.getPosition().getX() - x/2, klasa.getPosition().getY() - y/2, x, y);
    }

    private int calculateWidth(Klasa klasa, Graphics2D g2) {
        int currWidth = 140;
        for (ClassContent classContent : klasa.getContent()) {
            if (classContent instanceof Attribute) {
                FontMetrics fontMetrics = g2.getFontMetrics();
                int stringWidth = fontMetrics.stringWidth(((Attribute)(classContent)).getAttributeString());
                currWidth = Math.max(currWidth, stringWidth);
            } else if (classContent instanceof Method) {
                FontMetrics fontMetrics = g2.getFontMetrics();
                int stringWidth = fontMetrics.stringWidth(((Method) classContent).getMethodString());
                currWidth = Math.max(currWidth, stringWidth);
            }

        }
        return currWidth;
    }
    private void ispisiContent(Graphics2D g2, Klasa klasa, int startX, int startY) {
        for (ClassContent classContent : klasa.getContent()) {
            if (classContent instanceof Attribute) {
                g2.drawString(((Attribute) classContent).getAttributeString(), startX, startY);
            } else if (classContent instanceof Method) {
                g2.drawString(((Method) classContent).getMethodString(), startX, startY);
            }
            startY += 20;
        }
    }

    private void addConnectionDots(int sredinaX, int sredinaY, ArrayList<Point> connectionDots) {
        connectionDots.add(new Point(sredinaX, (int) shape.y));
        connectionDots.add(new Point((int) (shape.x + shape.width), sredinaY));
        connectionDots.add(new Point(sredinaX, (int) (shape.y + shape.height)));
        connectionDots.add(new Point((int) shape.x, sredinaY));
    }

}
