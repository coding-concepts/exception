package ui;

import exception.ValidationError;
import exception.ValidationException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

/**
 * Created by sniper825 on 11/26/17.
 */
public class Search implements IScreen {
    private JPanel TitlePanel;
    private JPanel SearchPanel;
    private JPanel ResultsPanel;
    private JLabel Title;
    private JTextField searchBar;
    private JButton searchButton;
    private JList ResultsList;
    private JPanel MainPanel;
    private JPanel BookInfoPanel;
    private JLabel CoverArt;
    private JLabel TitleLabel;
    private JLabel AuthorLabel;
    private JLabel IdLabel;
    private JLabel CopiesLabel;
    private JButton UpdateButton;
    private JButton LoansButton;
    private JButton issueButton;

    public Search() {
        ResultsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String[] bookInfo = ResultsList.getSelectedValue().toString().split(" - ");
                //System.out.println(ResultsList.getSelectedValue().toString().split("-")[1]);
                try{
                    IdLabel.setText("ID: " + bookInfo[0]);
                    TitleLabel.setText(bookInfo[1]);
                    AuthorLabel.setText("Author: " + bookInfo[2]);
                    CoverArt.setIcon(getCoverArt(bookInfo[0]));
                } catch(java.lang.ArrayIndexOutOfBoundsException ex) {
                    // This is because of a bad value somewhere and should not happen.
                    System.out.println("When " + ResultsList.getSelectedValue().toString() + " is split at ' - ', it should contain 3 parts");
                }
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ResultsList.setListData(search(searchBar));
            }
        });
    }

    public JPanel getMainPanel() {
        return MainPanel;
    }

    public static Icon getCoverArt(String id) {
        ImageIcon icon = new ImageIcon("covers/NotFound.png");
        if(new File("covers/" + id + ".jpg").exists()){
            icon = new ImageIcon("covers/" + id + ".jpg");
        } else {
            // todo: make a service that retrieves cover art from internet
        }
        return icon;
    }
}
