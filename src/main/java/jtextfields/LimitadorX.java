package jtextfields;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class LimitadorX extends PlainDocument {
    private final int quantidadeMax;

    public LimitadorX(int maxLen){
       super();
       quantidadeMax = maxLen;
    }
    
    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if(str == null || getLength() == quantidadeMax){
           return;
        }
        int totalquantia = (getLength() + str.length());
        if(totalquantia <= quantidadeMax){
           super.insertString(offs, str.replaceAll("[a-z][^0-9]", ""), a);
           return;
        }
        String nova = str.substring(0, getLength()-quantidadeMax);
        super.insertString(offs, nova, a);
    }
}