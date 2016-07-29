 package com.cerner.dcm.audit.comparision;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.wb.swt.SWTResourceManager;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.experimental.chart.swt.ChartComposite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;


public class GuiForComparisionOfDcwAndAudit {

    protected Shell shlTestingTool;
    private Text dcwFilePath;
    private Text auditFilePath;
    private Label lblEnterNumberOf;
    private Text numberOfColumnsInDcw;
    private Label lblNoofcolumns;
    private Text numberOfColumnsInAudit;
    private Label lblPrimarycolumn;
    private Text primaryKeyColumnOfDcw;
    private Label lblPrimarycolumn_1;
    private Text primaryKeyColumnOfAudit;
    private Button btnCompare;
    private Label label;
    private Label label_1;
    private Label label_2;
    private Label label_4;
    private Button btnOpen;
    private Label label_5;
    private Label lblNewLabel;
    private Label lblChooseAuditFile;
    private Label lblReport;
    /**private Label lblPercentageOfMatch;
    private Label lblPercentageOfMismatch;
    private Label label_6;
    private Label label_7;
    private Label lblNumberOfDcw;
    private Label lblNumberOfAudit;
    private Label label_10;
    private Label label_11;
    private Label lblInDomainAs;
    private Label lblAddToDomain;
    private Label label_12;
    private Label label_13;**/
    private Table table;
    private Button button;
     
    /**
     * Launch the application.
     * @param args
     */
    public static void main(String[] args) {
        try {
            GuiForComparisionOfDcwAndAudit window = new GuiForComparisionOfDcwAndAudit();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void displayFiles(String[] files) {
        for (int i = 0; files != null && i < files.length; i++) {
        dcwFilePath.setText(files[i]);
        dcwFilePath.setEditable(true);
        }
      }
    public void displayFiles1(String[] files) {
        for (int i = 0; files != null && i < files.length; i++) {
        auditFilePath.setText(files[i]);
        auditFilePath.setEditable(true);
        }
      }

    /**
     * Open the window.
     * @throws IOException 
     */
    public void open() throws IOException {
        Display display = Display.getDefault();
        createContents();
        shlTestingTool.open();
        shlTestingTool.layout();
        while (!shlTestingTool.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }

    /**
     * Create contents of the window.
     * @throws IOException 
     */
    protected void createContents() throws IOException {
        shlTestingTool = new Shell();
        shlTestingTool.setBackground(SWTResourceManager.getColor(0, 153, 204));
        shlTestingTool.setImage(null);
        shlTestingTool.setSize(1747, 842);
        shlTestingTool.setText("VComp");
        
        dcwFilePath = new Text(shlTestingTool, SWT.BORDER);
        dcwFilePath.setBackground(SWTResourceManager.getColor(204, 255, 255));
        dcwFilePath.setBounds(13, 173, 333, 31);
        
        Button btnDcwButton = new Button(shlTestingTool, SWT.FLAT);
        btnDcwButton.setForeground(SWTResourceManager.getColor(153, 153, 153));
        btnDcwButton.setFont(SWTResourceManager.getFont("Segoe UI Semibold", 10, SWT.NORMAL));
        btnDcwButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                FileDialog dialog = new FileDialog(shlTestingTool, SWT.NULL);
                String path = dialog.open();
                if (path != null) {

                File file = new File(path);
                if (file.isFile())
                displayFiles(new String[] { file.toString()});
                else
                displayFiles(file.list());

                }
                
                }
        });
        
        btnDcwButton.setBounds(395, 167, 105, 40);
        btnDcwButton.setText("Browse");
        
        auditFilePath = new Text(shlTestingTool, SWT.BORDER);
        auditFilePath.setBackground(SWTResourceManager.getColor(204, 255, 255));
        auditFilePath.setBounds(560, 173, 340, 31);
        
        Button btnAuditButton = new Button(shlTestingTool, SWT.NONE);
        btnAuditButton.setFont(SWTResourceManager.getFont("Segoe UI Semibold", 10, SWT.NORMAL));
        btnAuditButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                FileDialog dialog = new FileDialog(shlTestingTool, SWT.NULL);
                String path = dialog.open();
                if (path != null) {

                File file = new File(path);
                if (file.isFile())
                displayFiles1(new String[] { file.toString()});
                else
                displayFiles1(file.list());

                }
            }
        });
        btnAuditButton.setBounds(940, 168, 105, 40);
        btnAuditButton.setText("Browse");
        
        lblEnterNumberOf = new Label(shlTestingTool, SWT.CENTER);
        lblEnterNumberOf.setBackground(SWTResourceManager.getColor(51, 153, 204));
        lblEnterNumberOf.setForeground(SWTResourceManager.getColor(255, 255, 255));
        lblEnterNumberOf.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.BOLD | SWT.ITALIC));
        lblEnterNumberOf.setBounds(60, 283, 195, 36);
        lblEnterNumberOf.setText("No of Columns");
        
        numberOfColumnsInDcw = new Text(shlTestingTool, SWT.BORDER);
        numberOfColumnsInDcw.setBackground(SWTResourceManager.getColor(204, 255, 255));
        numberOfColumnsInDcw.setBounds(364, 282, 80, 31);
        
        lblNoofcolumns = new Label(shlTestingTool, SWT.CENTER);
        lblNoofcolumns.setBackground(SWTResourceManager.getColor(51, 153, 204));
        lblNoofcolumns.setForeground(SWTResourceManager.getColor(255, 255, 255));
        lblNoofcolumns.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.BOLD | SWT.ITALIC));
        lblNoofcolumns.setText("No of columns");
        lblNoofcolumns.setBounds(580, 283, 202, 30);
        
        numberOfColumnsInAudit = new Text(shlTestingTool, SWT.BORDER);
        numberOfColumnsInAudit.setBackground(SWTResourceManager.getColor(204, 255, 255));
        numberOfColumnsInAudit.setBounds(867, 282, 80, 31);
        
        lblPrimarycolumn = new Label(shlTestingTool, SWT.CENTER);
        lblPrimarycolumn.setBackground(SWTResourceManager.getColor(51, 153, 204));
        lblPrimarycolumn.setForeground(SWTResourceManager.getColor(255, 255, 255));
        lblPrimarycolumn.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.BOLD | SWT.ITALIC));
        lblPrimarycolumn.setBounds(37, 378, 252, 35);
        lblPrimarycolumn.setText("Primary Key Column");
        
        primaryKeyColumnOfDcw = new Text(shlTestingTool, SWT.BORDER);
        primaryKeyColumnOfDcw.setBackground(SWTResourceManager.getColor(204, 255, 255));
        primaryKeyColumnOfDcw.setBounds(364, 377, 80, 31);
        
        lblPrimarycolumn_1 = new Label(shlTestingTool, SWT.CENTER);
        lblPrimarycolumn_1.setBackground(SWTResourceManager.getColor(51, 153, 204));
        lblPrimarycolumn_1.setForeground(SWTResourceManager.getColor(255, 255, 255));
        lblPrimarycolumn_1.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.BOLD | SWT.ITALIC));
        lblPrimarycolumn_1.setText("Primary Key Column");
        lblPrimarycolumn_1.setBounds(570, 378, 259, 35);
        
        primaryKeyColumnOfAudit = new Text(shlTestingTool, SWT.BORDER);
        primaryKeyColumnOfAudit.setBackground(SWTResourceManager.getColor(204, 255, 255));
        primaryKeyColumnOfAudit.setBounds(867, 377, 80, 31);
        
        label_4 = new Label(shlTestingTool, SWT.BORDER);
        label_4.setBackground(SWTResourceManager.getColor(204, 255, 255));
        label_4.setBounds(37, 630, 965, 25);
        
        Label label_3 = new Label(shlTestingTool, SWT.BORDER);
        label_3.setBackground(SWTResourceManager.getColor(204, 255, 255));
        label_3.setBounds(793, 551, 209, 25);
        
        /**label_10 = new Label(shlTestingTool, SWT.BORDER);
        label_10.setBackground(SWTResourceManager.getColor(204, 255, 255));
        label_10.setBounds(1557, 91, 81, 25);
        
        label_11 = new Label(shlTestingTool, SWT.BORDER);
        label_11.setBackground(SWTResourceManager.getColor(204, 255, 255));
        label_11.setBounds(1557, 131, 81, 25);
        
        label_12 = new Label(shlTestingTool, SWT.BORDER);
        label_12.setBackground(SWTResourceManager.getColor(204, 255, 255));
        label_12.setBounds(1557, 256, 81, 25);
        
        label_13 = new Label(shlTestingTool, SWT.BORDER);
        label_13.setBackground(SWTResourceManager.getColor(204, 255, 255));
        label_13.setBounds(1557, 297, 81, 25);**/
        
        btnCompare = new Button(shlTestingTool, SWT.NONE);
        btnCompare.setFont(SWTResourceManager.getFont("Segoe UI Semibold", 10, SWT.NORMAL));
        btnCompare.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                try {
                    ComparingDcwAndAudit.Comparision(numberOfColumnsInDcw.getText(), numberOfColumnsInAudit.getText(), primaryKeyColumnOfDcw.getText(), primaryKeyColumnOfAudit.getText(), dcwFilePath.getText(), auditFilePath.getText());
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                label_3.setText("Comparision done");
                label_4.setText("result.xls");
                
                /**label_6.setText(ComparingDcwAndAudit.okPercentage);
                label_7.setText(ComparingDcwAndAudit.mismatchPercentage);
                label_10.setText(Integer.toString(ComparingDcwAndAudit.totalNumberOfDcwRows));
                label_11.setText(Integer.toString(ComparingDcwAndAudit.totalNumberOfAuditRows));
                label_12.setText(ComparingDcwAndAudit.inDomainPercentage);
                label_13.setText(ComparingDcwAndAudit.addToDomainPercentage);**/
                
                                
                table = new Table(shlTestingTool, SWT.BORDER | SWT.FULL_SELECTION);
                table.setBounds(1249, 77, 313, 220);
                table.setHeaderVisible(true);
                table.setLinesVisible(true);
                
                String[] titles = {"", ""};
                for (int i=0; i<titles.length; i++) {
                    TableColumn column = new TableColumn (table, SWT.NONE);
                    column.setText (titles [i]);
                }   
               
                TableItem item = new TableItem (table, SWT.NONE);
                item.setText (0, "Number of DCW Rows");
                item.setText (1, Integer.toString(ComparingDcwAndAudit.totalNumberOfDcwRows));
                TableItem item1 = new TableItem (table, SWT.NONE);
                item1.setText (0, "Number of AUDIT Rows");
                item1.setText (1, Integer.toString(ComparingDcwAndAudit.totalNumberOfAuditRows));
                TableItem item2 = new TableItem (table, SWT.NONE);
                item2.setText (0, "OK Percentage");
                item2.setText (1, ComparingDcwAndAudit.okPercentage+ "%");
                TableItem item3 = new TableItem (table, SWT.NONE);
                item3.setText (0, "Mismatch Percentage");
                item3.setText (1, ComparingDcwAndAudit.mismatchPercentage+ "%");
                TableItem item4 = new TableItem (table, SWT.NONE);
                item4.setText (0, "In Domain As Percentage");
                item4.setText (1, ComparingDcwAndAudit.inDomainPercentage+ "%");
                TableItem item5 = new TableItem (table, SWT.NONE);
                item5.setText (0, "Add to Domain Percentage");
                item5.setText (1, ComparingDcwAndAudit.addToDomainPercentage + "%");
                
                for (int i=0; i<titles.length; i++) {
                    table.getColumn (i).pack ();
                }
                
                                
                DefaultPieDataset dataset = new DefaultPieDataset();
                
                dataset.setValue("Add to Domain "+ComparingDcwAndAudit.addToDomainPercentage+"%", Double.parseDouble(ComparingDcwAndAudit.addToDomainPercentage));
                dataset.setValue("In Domain As "+ComparingDcwAndAudit.inDomainPercentage+"%", Double.parseDouble(ComparingDcwAndAudit.inDomainPercentage));
                dataset.setValue("OK "+ComparingDcwAndAudit.okPercentage+"%", Double.parseDouble(ComparingDcwAndAudit.okPercentage));
                
                JFreeChart chart = ChartFactory.createPieChart(
                        "PieChart",  // chart title
                        dataset,             // data
                        true,               // include legend
                        true,
                        false
                    );

                    PiePlot plot = (PiePlot) chart.getPlot();
                    plot.setSectionOutlinesVisible(true);
                    plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 15));
                    plot.setCircular(false);
                    plot.setLabelGap(0);
                
                final ChartComposite frame = new ChartComposite(shlTestingTool, SWT.NONE, chart, true);
                //frame.setForeground(SWTResourceManager.getColor(0, 153, 204));
                frame.setBounds(1178, 339, 490, 420);
                //frame.setBackground(SWTResourceManager.getColor(0, 153, 204));
            }
        });
        btnCompare.setBounds(57, 547, 105, 47);
        btnCompare.setText("Compare");
        
        label = new Label(shlTestingTool, SWT.SEPARATOR | SWT.HORIZONTAL);
        label.setBounds(10, 325, 849, -33);
        
        label_1 = new Label(shlTestingTool, SWT.SEPARATOR | SWT.VERTICAL);
        label_1.setBounds(526, 0, 2, 500);
        
        label_2 = new Label(shlTestingTool, SWT.SEPARATOR | SWT.HORIZONTAL);
        label_2.setBounds(0, 498, 1085, 2);
        
        CLabel lblDcw = new CLabel(shlTestingTool, SWT.CENTER);
        lblDcw.setForeground(SWTResourceManager.getColor(255, 255, 255));
        lblDcw.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.BOLD));
        lblDcw.setBackground(SWTResourceManager.getColor(0, 153, 204));
        lblDcw.setBounds(186, 21, 139, 31);
        lblDcw.setText("DCW");
        
        CLabel lblAudit = new CLabel(shlTestingTool, SWT.CENTER);
        lblAudit.setForeground(SWTResourceManager.getColor(255, 255, 255));
        lblAudit.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.BOLD));
        lblAudit.setBackground(SWTResourceManager.getColor(0, 153, 204));
        lblAudit.setBounds(761, 21, 105, 31);
        lblAudit.setText("Audit");
        
        btnOpen = new Button(shlTestingTool, SWT.NONE);
        btnOpen.setFont(SWTResourceManager.getFont("Segoe UI Semibold", 10, SWT.NORMAL));
        btnOpen.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                Program.launch("result.xls");
            }
        });
        btnOpen.setBounds(778, 693, 224, 47);
        btnOpen.setText("Open Comparision File");
        
        label_5 = new Label(shlTestingTool, SWT.SEPARATOR | SWT.VERTICAL);
        label_5.setBounds(1083, 0, 2, 786);
        
        lblNewLabel = new Label(shlTestingTool, SWT.CENTER);
        lblNewLabel.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.BOLD | SWT.ITALIC));
        lblNewLabel.setBackground(SWTResourceManager.getColor(0, 153, 204));
        lblNewLabel.setForeground(SWTResourceManager.getColor(255, 255, 255));
        lblNewLabel.setBounds(60, 142, 224, 25);
        lblNewLabel.setText("Choose DCW File");
        
        lblChooseAuditFile = new Label(shlTestingTool, SWT.CENTER);
        lblChooseAuditFile.setBackground(SWTResourceManager.getColor(51, 153, 204));
        lblChooseAuditFile.setForeground(SWTResourceManager.getColor(255, 255, 255));
        lblChooseAuditFile.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.BOLD | SWT.ITALIC));
        lblChooseAuditFile.setText("Choose Audit File");
        lblChooseAuditFile.setBounds(605, 142, 224, 25);
        
        lblReport = new Label(shlTestingTool, SWT.CENTER);
        lblReport.setFont(SWTResourceManager.getFont("Times New Roman", 14, SWT.BOLD | SWT.ITALIC));
        lblReport.setForeground(SWTResourceManager.getColor(255, 255, 255));
        lblReport.setBackground(SWTResourceManager.getColor(0, 153, 204));
        lblReport.setBounds(1324, 21, 176, 40);
        lblReport.setText("Report");
        
        button = new Button(shlTestingTool, SWT.NONE);
        button.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ArrangingColumns.ArrangeColumns();
            }
        });
        button.setText("Aranging Columns");
        button.setFont(SWTResourceManager.getFont("Segoe UI Semibold", 10, SWT.NORMAL));
        button.setBounds(60, 693, 224, 47);
        
        /**lblPercentageOfMatch = new Label(shlTestingTool, SWT.CENTER);
        lblPercentageOfMatch.setBackground(SWTResourceManager.getColor(51, 153, 204));
        lblPercentageOfMatch.setForeground(SWTResourceManager.getColor(255, 255, 255));
        lblPercentageOfMatch.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.BOLD | SWT.ITALIC));
        lblPercentageOfMatch.setBounds(1186, 174, 252, 35);
        lblPercentageOfMatch.setText("Percentage of Match");
        
        lblPercentageOfMismatch = new Label(shlTestingTool, SWT.CENTER);
        lblPercentageOfMismatch.setBackground(SWTResourceManager.getColor(51, 153, 204));
        lblPercentageOfMismatch.setForeground(SWTResourceManager.getColor(255, 255, 255));
        lblPercentageOfMismatch.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.BOLD | SWT.ITALIC));
        lblPercentageOfMismatch.setText("Percentage of Mismatch");
        lblPercentageOfMismatch.setBounds(1186, 215, 286, 42);
        
        label_6 = new Label(shlTestingTool, SWT.BORDER);
        label_6.setBackground(SWTResourceManager.getColor(204, 255, 255));
        label_6.setBounds(1557, 175, 81, 25);
        
        label_7 = new Label(shlTestingTool, SWT.BORDER);
        label_7.setBackground(SWTResourceManager.getColor(204, 255, 255));
        label_7.setBounds(1557, 216, 81, 25);
        
        lblNumberOfDcw = new Label(shlTestingTool, SWT.CENTER);
        lblNumberOfDcw.setText("Number of DCW Rows");
        lblNumberOfDcw.setForeground(SWTResourceManager.getColor(255, 255, 255));
        lblNumberOfDcw.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.BOLD | SWT.ITALIC));
        lblNumberOfDcw.setBackground(SWTResourceManager.getColor(51, 153, 204));
        lblNumberOfDcw.setBounds(1199, 90, 252, 35);
        
        lblNumberOfAudit = new Label(shlTestingTool, SWT.CENTER);
        lblNumberOfAudit.setText("Number of Audit Rows");
        lblNumberOfAudit.setForeground(SWTResourceManager.getColor(255, 255, 255));
        lblNumberOfAudit.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.BOLD | SWT.ITALIC));
        lblNumberOfAudit.setBackground(SWTResourceManager.getColor(51, 153, 204));
        lblNumberOfAudit.setBounds(1188, 131, 263, 42);
        
        lblInDomainAs = new Label(shlTestingTool, SWT.CENTER);
        lblInDomainAs.setText("In Domain Percentage");
        lblInDomainAs.setForeground(SWTResourceManager.getColor(255, 255, 255));
        lblInDomainAs.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.BOLD | SWT.ITALIC));
        lblInDomainAs.setBackground(SWTResourceManager.getColor(51, 153, 204));
        lblInDomainAs.setBounds(1186, 255, 280, 35);
        
        lblAddToDomain = new Label(shlTestingTool, SWT.CENTER);
        lblAddToDomain.setText("Add to Domain Percentage");
        lblAddToDomain.setForeground(SWTResourceManager.getColor(255, 255, 255));
        lblAddToDomain.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.BOLD | SWT.ITALIC));
        lblAddToDomain.setBackground(SWTResourceManager.getColor(51, 153, 204));
        lblAddToDomain.setBounds(1199, 296, 286, 42);**/

       
    }
}
