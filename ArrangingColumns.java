package com.cerner.dcm.audit.comparision;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.Component;

public class ArrangingColumns extends JFrame {

    DefaultListModel<String> dcwColumnNames = new DefaultListModel<String>();
    DefaultListModel<String> arrangedDcwColumns = new DefaultListModel<String>();
    JList<String> dragFromDcw;

    DefaultListModel<String> auditColumnNames = new DefaultListModel<String>();
    DefaultListModel<String> arrangedAuditColumns = new DefaultListModel<String>();
    JList<String> dragFromAudit;

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel panel_1;

    /**
     * Launch the application.
     */
    public static void ArrangeColumns(){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ArrangingColumns frame = new ArrangingColumns();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public ArrangingColumns() {
        super("Arranging Columns");
        
        String[] ColumnNamesInDCW=ComparingDcwAndAudit.headerLineInDcw.split(",");
        for(int i=ColumnNamesInDCW.length-1; i>=0;i--)
        {
            dcwColumnNames.add(0,i+". "+ColumnNamesInDCW[i]);
        }
        
        String[] ColumnNamesInAudit=ComparingDcwAndAudit.headerLineInAudit.split(",");
        for(int j=ColumnNamesInAudit.length-1; j>=0;j--)
        {
            auditColumnNames.add(0,j+". "+ColumnNamesInAudit[j]);
        }
        
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setVisible(true);
        setBounds(100, 100, 1363, 669);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(51, 153, 204));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Copy the column names and place matching columns side by side");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Andalus", Font.BOLD, 24));
        lblNewLabel.setBounds(317, 0, 721, 49);
        contentPane.add(lblNewLabel);
        
        JButton btnNewButton = new JButton("Match Columns");
        btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnNewButton.setBounds(565, 53, 181, 33);
        contentPane.add(btnNewButton);
        
        JLabel lblNewLabel_1 = new JLabel("DCW Columns");
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 22));
        lblNewLabel_1.setBounds(392, 55, 151, 26);
        contentPane.add(lblNewLabel_1);
        
        JLabel lblAuditColumns = new JLabel("Audit Columns");
        lblAuditColumns.setForeground(Color.WHITE);
        lblAuditColumns.setFont(new Font("Times New Roman", Font.BOLD, 22));
        lblAuditColumns.setBounds(772, 55, 151, 26);
        contentPane.add(lblAuditColumns);
        
        JLabel lblNewLabel_2 = new JLabel("DCW Column names");
        lblNewLabel_2.setFont(new Font("Andalus", Font.BOLD, 23));
        lblNewLabel_2.setForeground(Color.WHITE);
        lblNewLabel_2.setBounds(50, 52, 236, 33);
        contentPane.add(lblNewLabel_2);
        
        JLabel lblAuditColumnNames = new JLabel("Audit Column names");
        lblAuditColumnNames.setForeground(Color.WHITE);
        lblAuditColumnNames.setFont(new Font("Andalus", Font.BOLD, 23));
        lblAuditColumnNames.setBounds(1045, 59, 236, 33);
        contentPane.add(lblAuditColumnNames);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(51, 153, 204));
        panel.setBounds(15, 94, 290, 492);
        dragFromDcw = new JList<String>(dcwColumnNames);
        dragFromDcw.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        dragFromDcw.setVisibleRowCount(30);
        dragFromDcw.setTransferHandler(new FromTransferHandlerDcw());
        dragFromDcw.setDragEnabled(true);
        dragFromDcw.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JLabel label = new JLabel("");
        panel.add(label);
        JScrollPane sp = new JScrollPane(dragFromDcw);
        panel.add(sp);
        contentPane.add(panel);
        
        JList<String> moveToDcw = new JList<String>(arrangedDcwColumns);
        moveToDcw.setVisibleRowCount(30);
        moveToDcw.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        moveToDcw.setTransferHandler(new ToTransferHandlerDcw(TransferHandler.MOVE));
        moveToDcw.setDropMode(DropMode.INSERT);
        
        panel_1 = new JPanel();
        panel_1.setBackground(new Color(51, 153, 204));
        panel_1.setBounds(369, 94, 273, 492);
        label = new JLabel("");
        panel_1.add(label);
        sp = new JScrollPane(moveToDcw);
        panel_1.add(sp);
        contentPane.add(panel_1);
        
        JPanel panel_3 = new JPanel();
        panel_3.setBackground(new Color(51, 153, 204));
        panel_3.setBounds(991, 94, 290, 492);
        dragFromAudit = new JList<String>(auditColumnNames);
        dragFromAudit.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        dragFromAudit.setVisibleRowCount(30);
        dragFromAudit.setTransferHandler(new FromTransferHandlerAudit());
        dragFromAudit.setDragEnabled(true);
        dragFromAudit.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JLabel label_1 = new JLabel("");
        panel_3.add(label_1);
        JScrollPane scrollPane = new JScrollPane(dragFromAudit);
        panel_3.add(scrollPane);
        contentPane.add(panel_3);
        
        JList<String> moveToAudit = new JList<String>(arrangedAuditColumns);
        moveToAudit.setVisibleRowCount(30);
        moveToAudit.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        moveToAudit.setTransferHandler(new ToTransferHandlerAudit(TransferHandler.MOVE));
        moveToAudit.setDropMode(DropMode.INSERT);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(51, 153, 204));
        panel_2.setBounds(681, 94, 273, 492);
        JLabel label_2 = new JLabel("");
        panel_2.add(label_2);
        JScrollPane scrollPane_1 = new JScrollPane(moveToAudit);
        panel_2.add(scrollPane_1);
        contentPane.add(panel_2);
        
        
  
    }
    
    class FromTransferHandlerDcw extends TransferHandler {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        public int getSourceActions(JComponent comp) {
            return COPY_OR_MOVE;
        }

        private int index = 0;

        public Transferable createTransferable(JComponent comp) {
            index = dragFromDcw.getSelectedIndex();
            if (index < 0 || index >= dcwColumnNames.getSize()) {
                return null;
            }

            return new StringSelection(dragFromDcw.getSelectedValue());
        }
        
        public void exportDone(JComponent comp, Transferable trans, int action) {
            if (action != MOVE) {
                return;
            }

            dcwColumnNames.removeElementAt(index);
        }
    }
    
    class FromTransferHandlerAudit extends TransferHandler {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        public int getSourceActions(JComponent comp) {
            return COPY_OR_MOVE;
        }

        private int index = 0;

        public Transferable createTransferable(JComponent comp) {
            index = dragFromAudit.getSelectedIndex();
            if (index < 0 || index >= auditColumnNames.getSize()) {
                return null;
            }

            return new StringSelection(dragFromAudit.getSelectedValue());
        }
        
        public void exportDone(JComponent comp, Transferable trans, int action) {
            if (action != MOVE) {
                return;
            }

            auditColumnNames.removeElementAt(index);
        }
    }
    
    class ToTransferHandlerDcw extends TransferHandler {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        int action;
        
        public ToTransferHandlerDcw(int action) {
            this.action = action;
        }
        
        public boolean canImport(TransferHandler.TransferSupport support) {
            // for the demo, we'll only support drops (not clipboard paste)
            if (!support.isDrop()) {
                return false;
            }

            // we only import Strings
            if (!support.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                return false;
            }

            boolean actionSupported = (action & support.getSourceDropActions()) == action;
            if (actionSupported) {
                support.setDropAction(action);
                return true;
            }

            return false;
        }
    
    public boolean importData(TransferHandler.TransferSupport support) {
        // if we can't handle the import, say so
        if (!canImport(support)) {
            return false;
        }

        // fetch the drop location
        JList.DropLocation dl = (JList.DropLocation)support.getDropLocation();

        int index = dl.getIndex();

        // fetch the data and bail if this fails
        String data;
        try {
            data = (String)support.getTransferable().getTransferData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException e) {
            return false;
        } catch (java.io.IOException e) {
            return false;
        }

        JList<String> list = (JList<String>)support.getComponent();
        DefaultListModel<String> model = (DefaultListModel<String>)list.getModel();
        model.insertElementAt(data, index);

        Rectangle rect = list.getCellBounds(index, index);
        list.scrollRectToVisible(rect);
        list.setSelectedIndex(index);
        list.requestFocusInWindow();

        return true;
    }
    }
    
    class ToTransferHandlerAudit extends TransferHandler {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        int action;
        
        public ToTransferHandlerAudit(int action) {
            this.action = action;
        }
        
        public boolean canImport(TransferHandler.TransferSupport support) {
            // for the demo, we'll only support drops (not clipboard paste)
            if (!support.isDrop()) {
                return false;
            }

            // we only import Strings
            if (!support.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                return false;
            }

            boolean actionSupported = (action & support.getSourceDropActions()) == action;
            if (actionSupported) {
                support.setDropAction(action);
                return true;
            }

            return false;
        }
    
    public boolean importData(TransferHandler.TransferSupport support) {
        // if we can't handle the import, say so
        if (!canImport(support)) {
            return false;
        }

        // fetch the drop location
        JList.DropLocation dl = (JList.DropLocation)support.getDropLocation();

        int index = dl.getIndex();

        // fetch the data and bail if this fails
        String data;
        try {
            data = (String)support.getTransferable().getTransferData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException e) {
            return false;
        } catch (java.io.IOException e) {
            return false;
        }

        JList<String> list = (JList<String>)support.getComponent();
        DefaultListModel<String> model = (DefaultListModel<String>)list.getModel();
        model.insertElementAt(data, index);

        Rectangle rect = list.getCellBounds(index, index);
        list.scrollRectToVisible(rect);
        list.setSelectedIndex(index);
        list.requestFocusInWindow();

        return true;
    }
    }
}
