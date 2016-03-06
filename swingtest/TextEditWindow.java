/**
 * ʹ��JAVA��д���ڳ���
 */

package swingtest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.TextField;
import java.io.*;
import javax.swing.JFileChooser; 


public class TextEditWindow extends JFrame {
    
    private TextArea textArea;
    
    TextEditWindow(){
        this.setTitle("Main Window");
        
        Dimension minSize = new Dimension(800, 600);
	this.setMinimumSize(minSize);

	// set menu
	MenuBar menuBar = new MenuBar();
	Menu fileMenu = new  Menu("File");
    MenuItem saveMenuItem = new MenuItem("Save");
    saveMenuItem.addActionListener(new SaveActionListener());
    fileMenu.add(saveMenuItem);
	Menu editMenu = new Menu("Edit");
	Menu viewMenu = new Menu("View");
	Menu runMenu = new Menu("Run");
	Menu toolsMenu = new Menu("Tools");
	Menu helpMenu = new Menu("Help");
	MenuItem aboutMenuItem = new MenuItem("About");
    aboutMenuItem.addActionListener(new AboutActionListener());
	helpMenu.add(aboutMenuItem);
	menuBar.add(fileMenu);
	menuBar.add(editMenu);
	menuBar.add(viewMenu);
	menuBar.add(runMenu);
	menuBar.add(toolsMenu);
	menuBar.add(helpMenu);
	
	this.setMenuBar(menuBar);
    
    this.textArea = new TextArea();
    textArea.setEditable(true);
    this.add(textArea);
    
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setVisible(true);
        
    }
    
	public static void main(String[] args) {
        new TextEditWindow();

	
	}
    
    private class AboutActionListener implements ActionListener {   

	public void actionPerformed(ActionEvent e){

			JDialog aboutDialog = new JDialog(TextEditWindow.this, "About");
			String aboutInfo = "This is a java program written by Steven. 2016/02/20.";
			Label label = new Label(aboutInfo);
			aboutDialog.add(label);
            aboutDialog.setMinimumSize(new Dimension(400, 300));
            aboutDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			aboutDialog.setVisible(true);



			// do nothing
			// 
		
	}
    }
    
    private class SaveActionListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {

            String str1 = new String(TextEditWindow.this.textArea.getText());
            if(str1.isEmpty()) {
                JOptionPane.showMessageDialog(TextEditWindow.this, "Text is now EMPTY!");
            }
            else {
                // for debugging
                System.out.println(str1);
                
                JFileChooser fc = new JFileChooser();
                int returnVal = fc.showOpenDialog(TextEditWindow.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    
                    String fileName = new String(fc.getSelectedFile().getName());
                    System.out.println("Now save the file.");
                    
                    File of = new File(fileName);
                    try {
                    of.createNewFile();
                    } catch(IOException e) {
                        JOptionPane.showMessageDialog(TextEditWindow.this, "Create File Failed!");
                    }
                    try {
                    OutputStream out = new FileOutputStream(of);
                        try {
                            out.write(str1.getBytes());
                            out.close();
                        }
                        catch (IOException e) {
                            JOptionPane.showMessageDialog(TextEditWindow.this, "Writing File Failed!");
                        }
                    } catch(FileNotFoundException e) {
                        JOptionPane.showMessageDialog(TextEditWindow.this, "Output Stream creation failed!");
                    }

                   
                }
     
            }
        }
    }
}
