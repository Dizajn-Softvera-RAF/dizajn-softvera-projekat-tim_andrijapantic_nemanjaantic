package app.view.painters;

import app.model.classcontent.ClassContent;
import app.model.classcontent.EnumType;
import app.model.diagcomposite.DiagramElement;
import app.model.diagimplementation.interclass.EnumComp;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class EnumPainter extends ElementPainter{

    protected Rectangle2D.Double shape;

    public EnumPainter(EnumComp element) {
        setElement(element);
        this.setName(element.getName());
        setShape(shape);
    }


    @Override
    public void paint(Graphics2D g2) {
        g2.setFont(new Font("Arial", Font.PLAIN, 11));
        g2.setColor(new Color(element.getCurrentColor()));
        g2.setStroke(new BasicStroke(((EnumComp)getElement()).getStroke()));
        this.shape =  createShape((EnumComp) getElement(), g2);
        Font originalFont = g2.getFont();
        setShape(shape);
        g2.setFont(new Font("Arial", Font.ITALIC, 12));
        g2.fill(shape);
        g2.setColor(Color.BLACK);
        g2.draw(shape);
        g2.drawLine((int) shape.getX(), (int) shape.getY()+30, (int) ((int) shape.getX()+shape.getWidth()), (int) shape.getY()+30);

        ispisiContent(g2, (EnumComp) getElement(), (int) (shape.getX() + 10), (int) (shape.getY()+50));
        g2.setFont(new Font("Arial", Font.BOLD, 13));
        g2.drawString(element.getName(), (int) shape.getX()+20, (int) shape.getY()+20);
        g2.setFont(originalFont);
        ((EnumComp) element).getConnectionsDots().clear();
        int sredinaX = (int) (shape.x + shape.width / 2);
        int sredinaY = (int) (shape.y + shape.height / 2);
        addConnectionDots(sredinaX, sredinaY, ((EnumComp) element).getConnectionsDots());
    }

    @Override
    public boolean elementAt(DiagramElement element, Point pos) {
        return getShape().contains(pos);
    }

    private Rectangle2D.Double createShape(EnumComp enumComp, Graphics2D g2) {
        int width = calculateWidth(enumComp, g2) + 50;
        int height = 40 + enumComp.getContent().size() * 20;
        return new Rectangle2D.Double(enumComp.getPosition().getX() - width / 2, enumComp.getPosition().getY() - height / 2, width, height);
    }

    private int calculateWidth(EnumComp enumComp, Graphics2D g2) {
        int currWidth = 140;
        for (ClassContent classContent : enumComp.getContent()) {
            if (classContent instanceof EnumType) {
                FontMetrics fontMetrics = g2.getFontMetrics();
                int stringWidth = fontMetrics.stringWidth(((EnumType) classContent).enumerableString());
                currWidth = Math.max(currWidth, stringWidth);
            }

        }
        return currWidth;
    }
    private void ispisiContent(Graphics2D g2, EnumComp enumComp, int startX, int startY) {
        for (ClassContent classContent : enumComp.getContent()) {
            if (classContent instanceof EnumType) {
                g2.drawString(((EnumType) classContent).enumerableString(), startX, startY);
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
