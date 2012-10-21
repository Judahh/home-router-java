/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import connection.RouterHandler;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxEditor;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
/**
 *
 * @author JH
 */
//public class SuggestionBoxModel  extends AbstractListModel implements ComboBoxModel, KeyListener, ItemListener{
//    ArrayList<String> db = new ArrayList<>();
//    ArrayList<String> data = new ArrayList<>();
//    String selection;
//    JComboBox cb;
//    ComboBoxEditor cbe;
//    int currPos = 0;
//
//    public SuggestionBoxModel(JComboBox jcb){
//        cb = jcb;
//        cbe = jcb.getEditor();
////here we add the key listener to the text field that the combobox is wrapped around
//        cbe.getEditorComponent().addKeyListener(this);
//
////set up our "database" of items - in practice you will usuallu have a proper db.
////        db.add("aluminium");
////        db.add("aluminium chloride");
////        db.add("iron");
////        db.add("iron oxide (2+)");
////        db.add("iron oxide (3+)");
////        db.add("sodium");
////        db.add("sodium chloride");
////        db.add("titanium");
////        db.add("selenium");
////        db.add("potassium");
////        db.add("polonium");
//    }
//
//    public void updateModel(String in){
//        data.clear();
////lets find any items which start with the string the user typed, and add it to the popup list
////here you would usually get your items from a database, or some other storage...
//        for(String s:db)
//            if(s.startsWith(in))
//                data.add(s);
//
//        super.fireContentsChanged(this, 0, data.size());
//
////this is a hack to get around redraw problems when changing the list length of the displayed popups
//        cb.hidePopup();
//        cb.showPopup();
//        if(data.size() != 0)
//            cb.setSelectedIndex(0);
//    }
//
//    @Override
//    public int getSize(){
//        return data.size();
//    }
//    @Override
//    public Object getElementAt(int index){
//        return data.get(index);
//    }
//    @Override
//    public void setSelectedItem(Object anItem){
//        selection = (String) anItem;
//    }
//    @Override
//    public Object getSelectedItem(){
//        return selection;
//    }
//    @Override
//    public void keyTyped(KeyEvent e){}
//    @Override
//    public void keyPressed(KeyEvent e){}
//
//    @Override
//    public void keyReleased(KeyEvent evt){
//        int key = evt.getKeyCode();
//        String string = cbe.getItem().toString();
//        JTextField textField = (JTextField)cbe.getEditorComponent();
//        currPos = textField.getCaretPosition();
//
//        if(key == KeyEvent.CHAR_UNDEFINED){
//            if(evt.getKeyCode() != KeyEvent.VK_ENTER ){
//                cbe.setItem(string);
//                textField.setCaretPosition(currPos);
//            }
//        }else if(key == KeyEvent.VK_ENTER)
//            cb.setSelectedIndex(cb.getSelectedIndex());
//        else{
//            updateModel(cb.getEditor().getItem().toString());
//            cbe.setItem(string);
//            textField.setCaretPosition(currPos);
//        }
//    }
//    
//    @Override
//    public void itemStateChanged(ItemEvent e){
//        cbe.setItem(e.getItem().toString());
//        cb.setSelectedItem(e.getItem());
//    }
//}
