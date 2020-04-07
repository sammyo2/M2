import java.awt.*;
import javax.swing.*;
import java.awt.geom.Line2D;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;

public class UniverseMap extends JPanel {

    private static Integer[] xCoord;
    private static Integer[] yCoord;
    private static String[] names;
    private JTable table;
    private CoordInputTableModel model;
    private Display display;

    public UniverseMap(Game g) {

        xCoord = new Integer[g.getUniverse().getRegionSize()];
        for (int i = 0; i < g.getUniverse().getRegionSize(); i++) {
            xCoord[i] = g.getUniverse().getRegion(i).getX();
        }

        yCoord = new Integer[g.getUniverse().getRegionSize()];
        for (int i = 0; i < g.getUniverse().getRegionSize(); i++) {
            yCoord[i] = g.getUniverse().getRegion(i).getY();
        }

        names = new String[g.getUniverse().getRegionSize()];
        for (int i = 0; i < g.getUniverse().getRegionSize(); i++) {
            names[i] = g.getUniverse().getRegion(i).getName();
        }

        JFrame window = new JFrame("Universe Map");
        UniverseMap content = new UniverseMap();
        window.setContentPane(content);
        window.pack();
        window.setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation((screenSize.width - window.getWidth()) / 2,
                (screenSize.height - window.getHeight()) / 2);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    public static class Applet extends JApplet {
        public void init() {
            UniverseMap content = new UniverseMap();
            setContentPane(content);
        }
    }

    private class CoordInputTableModel extends AbstractTableModel {

        public int getColumnCount() {
            return 4;
        }

        public int getRowCount() {
            return xCoord.length;
        }

        public Object getValueAt(int row, int col) {
            if (col == 0) {
                return (row + 1);
            } else if (col == 1) {
                return xCoord[row];
            } else if (col == 2) {
                return yCoord[row];
            } else {
                return names[row];
            }
        }

        public Class<?> getColumnClass(int col) {
            if (col == 0 || col == 1 || col == 2) {
                return Integer.class;
            } else {
                return String.class;
            }
        }

        public String getColumnName(int col) {
            if (col == 0) {
                return "Num";
            } else if (col == 1) {
                return "X";
            } else if (col == 2) {
                return "Y";
            } else {
                return "Name";
            }
        }

        public void setValueAt(Object obj, int row, int col) {
            if (col == 1) {
                xCoord[row] = (Integer) obj;
            } else if (col == 2) {
                yCoord[row] = (Integer) obj;
            } else {
                names[row] = (String) obj;
            }
            fireTableCellUpdated(row, col);
        }
    }

    private class Display extends JPanel {

        public void paintComponent(Graphics g) {

            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            double min = -201;
            double max = 201;
            int count = model.getRowCount();
            for (int i = 0; i < count; i++) {
                Integer x = (Integer) model.getValueAt(i, 1);
                Integer y = (Integer) model.getValueAt(i, 2);
            }

            g2.translate(getWidth() / 2, getHeight() / 2);
            g2.scale(getWidth() / (max - min), -getHeight() / (max - min));
            g2.translate(-(max + min) / 2, -(max + min) / 2);

            double pixelWidth = (max - min) / getWidth();
            double pixelHeight = (max - min) / getHeight();

            g2.setStroke(new BasicStroke(0));

            g2.setColor(Color.BLUE);
            g2.draw(new Line2D.Double(min, 0, max, 0));
            g2.draw(new Line2D.Double(0, min, 0, max));
            if (max - min < 100) {
                int tick = (int) min;
                while (tick <= max) {
                    g2.draw(new Line2D.Double(tick, 0, tick, 3 * pixelHeight));
                    g2.draw(new Line2D.Double(0, tick, 3 * pixelWidth, tick));
                    tick++;
                }
            }

            g2.setColor(Color.RED);
            for (int i = 0; i < count; i++) {
                Integer x = (Integer) model.getValueAt(i, 1);
                Integer y = (Integer) model.getValueAt(i, 2);
                if (x != null && y != null) {
                    g2.draw(new Line2D.Double(x - 3 * pixelWidth, y, x + 3 * pixelWidth, y));
                    g2.draw(new Line2D.Double(x, y - 3 * pixelHeight, x, y + 3 * pixelHeight));
                }
            }

        }
    }

    public UniverseMap() {

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        model = new CoordInputTableModel();
        table = new JTable(model);
        table.setRowHeight(25);
        table.setShowGrid(true);
        table.setGridColor(Color.BLACK);
        table.setPreferredScrollableViewportSize(new Dimension(250, 300));
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getTableHeader().setReorderingAllowed(false);
        add(new JScrollPane(table), BorderLayout.WEST);

        for  (int i = 0; i < 10; i++) {
            model.setValueAt(xCoord[i], i, 1);
            model.setValueAt(yCoord[i], i, 2);
        }

        display = new Display();
        display.setPreferredSize(new Dimension(600, 600));
        display.setBackground(Color.WHITE);
        add(display, BorderLayout.CENTER);

        model.addTableModelListener(new TableModelListener() {
            public void tableChanged(TableModelEvent e) {
                display.repaint();
            }
        });
    }
}