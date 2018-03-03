/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasaxparser;

import java.util.ArrayList;
import java.util.HashMap;
import org.xml.sax.Attributes;

/**
 *
 * @author tessmccorkle
 */
public class Node {
    public String name = "";
    public String content = "";
    public HashMap properties = new HashMap();  //has key value pairs, one will be a string, the other a node
    public Attributes attributes;
    
    
    public Node(){
      
    }
    
//    
}
