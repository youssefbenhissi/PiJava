/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validation;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 *
 * @author HP
 */
public class TextfielValidation {
    public static boolean  isTextfielValidation(TextField tf){
        boolean b = false;
        if(tf.getText().length() !=0 || !tf.getText().isEmpty())
            b= true;
        
        return b;
    }
    public static boolean  isTextfielValidation( TextField tf ,Label lb,String Messagerreur){
        boolean b = true;
        String msg=null;
        tf.getStyleClass().remove("errer");
        if(!isTextfielValidation(tf)){
            
            b= false;
            msg=Messagerreur;
            tf.getStyleClass().add("errer");
        }
        lb.setText(msg);
        
        return b;
    }
}
