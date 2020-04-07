import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonEditor extends DefaultCellEditor {

    protected JButton button;
    private String label;
    private boolean isPushed;
    private Game game;
    private int regionIndex;
    private JFrame frame;
    private ShipScreen sS;

    public ButtonEditor(JCheckBox checkBox, Game g, int regionIndex, JFrame frame, ShipScreen sS) {
        super(checkBox);
        this.game = g;
        this.regionIndex = regionIndex;
        this.sS = sS;
        button = new JButton();
        button.setOpaque(true);
        this.frame = frame;
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }

    public Object getCellEditorValue() {
        if (isPushed) {
            if (label.startsWith("B")) {
                frame.dispose();
                TransactionScreen tS = new TransactionScreen(game, regionIndex, label, sS);
            } else {
                frame.dispose();
                TransactionScreen tS = new TransactionScreen(game, regionIndex, label, sS);
            }
        }
        isPushed = false;
        return new String(label);
    }

    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}
