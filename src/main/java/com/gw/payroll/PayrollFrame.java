/*
 * Created by JFormDesigner on Thu Jul 25 16:24:16 CST 2019
 */

package com.gw.payroll;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import javax.swing.*;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.intellij.uiDesigner.core.*;
import net.miginfocom.swing.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 * @author unknown
 */
public class PayrollFrame extends JFrame {
    private static final String ERROR_TITLE = "错误";
    public PayrollFrame() {
        initComponents();
        this.setVisible(true);
        this.setResizable(false);
    }

    private void okButtonMouseClicked(MouseEvent e) {
        JFileChooser jf = new JFileChooser();
        jf.showOpenDialog(this);//显示打开的文件对话框
        File f =  jf.getSelectedFile();//使用文件类获取选择器选择的文件
        Optional<File> opt = Optional.ofNullable(f);
        if(opt.isPresent()){
            try {
                CalSetting setting = getCalSetting();
                CalPayRoll cal = new CalPayRoll(opt.get(),setting);
                cal.cal();
            } catch (IOException|InvalidFormatException e1) {
                showErrorMessage("解析excel出错");
            } catch (ParamNullException e1) {
                showErrorMessage("有必填参数未填");
            }
        }else{
            showErrorMessage("请选择文件");
        }

    }

    private CalSetting getCalSetting() throws ParamNullException {
        CalSetting setting = new CalSetting();
        Map<ColType,String> settingMap = Maps.newEnumMap(ColType.class);
        Optional<String> nameOpt = Optional.ofNullable(namePosField.getText());
        Optional<String> genderOpt = Optional.ofNullable(genderPosField.getText());
        checkParam(nameOpt,settingMap,ColType.Name);
        checkParam(genderOpt,settingMap,ColType.GENDER);
        setting.setColTypeMap(settingMap);
        return setting;
    }
    private void checkParam(Optional<String> opt,Map<ColType,String> settingMap,ColType type) throws ParamNullException {
        if(opt.isPresent()){
            if(!Strings.isNullOrEmpty(opt.get())){
                settingMap.put(type,opt.get());
            }else {
                throw new ParamNullException();
            }
        }else{
            throw new ParamNullException();
        }
    }
    private void showErrorMessage(String message){
        JOptionPane.showMessageDialog(this, message, ERROR_TITLE,JOptionPane.WARNING_MESSAGE);
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - gw
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        paramPanel = new JPanel();
        namePosField = new JTextField();
        genderPosField = new JTextField();
        nameColPosLabel = new JLabel();
        genderColPosLabel = new JLabel();
        buttonBar = new JPanel();
        calButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax .
            swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e" , javax. swing .border
            . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "D\u0069al\u006fg"
            , java .awt . Font. BOLD ,12 ) ,java . awt. Color .red ) ,dialogPane. getBorder
            () ) ); dialogPane. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java
            . beans. PropertyChangeEvent e) { if( "\u0062or\u0064er" .equals ( e. getPropertyName () ) )throw new RuntimeException
            ( ) ;} } );
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new BorderLayout());

                //======== paramPanel ========
                {
                    paramPanel.setLayout(null);
                    paramPanel.add(namePosField);
                    namePosField.setBounds(135, 5, 62, namePosField.getPreferredSize().height);
                    paramPanel.add(genderPosField);
                    genderPosField.setBounds(135, 45, 62, genderPosField.getPreferredSize().height);

                    //---- nameColPosLabel ----
                    nameColPosLabel.setText("\u59d3\u540d\u6240\u5728\u5217\u4f4d\u7f6e");
                    paramPanel.add(nameColPosLabel);
                    nameColPosLabel.setBounds(new Rectangle(new Point(30, 10), nameColPosLabel.getPreferredSize()));

                    //---- genderColPosLabel ----
                    genderColPosLabel.setText("\u6027\u522b\u6240\u5728\u5217\u4f4d\u7f6e");
                    paramPanel.add(genderColPosLabel);
                    genderColPosLabel.setBounds(new Rectangle(new Point(30, 50), genderColPosLabel.getPreferredSize()));

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < paramPanel.getComponentCount(); i++) {
                            Rectangle bounds = paramPanel.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = paramPanel.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        paramPanel.setMinimumSize(preferredSize);
                        paramPanel.setPreferredSize(preferredSize);
                    }
                }
                contentPanel.add(paramPanel, BorderLayout.CENTER);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setLayout(new MigLayout(
                    "insets dialog,alignx right",
                    // columns
                    "[button,fill]" +
                    "[button,fill]",
                    // rows
                    null));

                //---- calButton ----
                calButton.setText("\u8ba1\u7b97");
                calButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        okButtonMouseClicked(e);
                    }
                });
                buttonBar.add(calButton, "cell 0 0");

                //---- cancelButton ----
                cancelButton.setText("\u53d6\u6d88");
                buttonBar.add(cancelButton, "cell 1 0");
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        setSize(500, 285);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - gw
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JPanel paramPanel;
    private JTextField namePosField;
    private JTextField genderPosField;
    private JLabel nameColPosLabel;
    private JLabel genderColPosLabel;
    private JPanel buttonBar;
    private JButton calButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
