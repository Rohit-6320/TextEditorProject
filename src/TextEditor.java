import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    // Declaring properties of text editor
    JFrame frame;

    JMenuBar menubar;

    JMenu file, edit;

    JMenuItem newFile, openFile, saveFile;

    JMenuItem cut, copy, paste, selectAll, close;

    JTextArea textArea;

    TextEditor()
    {
        // Initialize frame
        frame=new JFrame();

        // Initialize menubar
        menubar=new JMenuBar();

        textArea=new JTextArea();

        file=new JMenu("File");
        edit=new JMenu("Edit");

        newFile=new JMenuItem("New Window");
        openFile=new JMenuItem("Open File");
        saveFile=new JMenuItem("Save File");

        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        cut=new JMenuItem("Cut");
        copy=new JMenuItem("Copy");
        paste=new JMenuItem("Paste");
        selectAll=new JMenuItem("Select All");
        close=new JMenuItem("Close");

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        // Add menu to menubar
        menubar.add(file);
        menubar.add(edit);

        frame.setJMenuBar(menubar);

        JPanel panel=new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));

        panel.add(textArea, BorderLayout.CENTER);
        JScrollPane scrollPane=new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane);

        frame.add(panel);

//        frame.add(textArea);
        // Set dimensions
        frame.setBounds(350,175,800,475);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        if(actionEvent.getSource()==cut)
        {
            textArea.cut();
        }

        if(actionEvent.getSource()==copy)
        {
            textArea.copy();
        }

        if(actionEvent.getSource()==paste)
        {
            textArea.paste();
        }

        if(actionEvent.getSource()==selectAll)
        {
            textArea.selectAll();
        }

        if(actionEvent.getSource()==close)
        {
            System.exit(0);
        }

        if(actionEvent.getSource()==openFile)
        {
            // Open file chooser
            JFileChooser fileChooser=new JFileChooser("C:");
            int choseOption=fileChooser.showOpenDialog(null);

            // If we click on open button
            if(choseOption==JFileChooser.APPROVE_OPTION)
            {
                File file= fileChooser.getSelectedFile();
                String filePath=file.getPath();

                try{
                    FileReader fileReader=new FileReader(filePath);
                    BufferedReader bufferReader=new BufferedReader(fileReader);
                    String intermediate="", output="";

                    while((intermediate=bufferReader.readLine())!=null)
                    {
                        output+=intermediate+"\n";
                    }

                    // Set output to text area
                    textArea.setText(output);
                }
                catch (FileNotFoundException fileNotFoundException)
                {
                    fileNotFoundException.printStackTrace();
                }
                catch(IOException ioException)
                {
                    ioException.printStackTrace();
                }
            }
        }

        if(actionEvent.getSource()==saveFile)
        {
            JFileChooser fileChooser=new JFileChooser("C");
            int chooseOption=fileChooser.showSaveDialog(null);
            if(chooseOption==JFileChooser.APPROVE_OPTION)
            {
                File file=new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try
                {
                    FileWriter fileWriter=new FileWriter(file);

                    BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);

                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch(IOException ioException)
                {
                    ioException.printStackTrace();
                }
            }
        }

        if(actionEvent.getSource()==newFile)
        {
            TextEditor newTextEditor=new TextEditor();
        }
    }
    public static void main(String[] args) {
        TextEditor textEditor=new TextEditor();
    }
}