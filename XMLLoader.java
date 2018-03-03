/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasaxparser;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author tessmccorkle
 */
public class XMLLoader {

    public static Node load(File xmlCourseFile) throws Exception {
        Node root = new Node();
        
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            
            DefaultHandler handler;
            handler = new DefaultHandler() {
                Node node = null;
                Stack stack = new Stack();
                Node currentNode;
                int i = 0;
                
                @Override
                public void startDocument() throws SAXException {
                    System.out.println("StartDocument");
                }

                @Override
                public void endDocument() throws SAXException {
                    System.out.println("EndDocument");
                }
                
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes)throws SAXException{
                    Node node = new Node();
                    node.name = qName;
                    System.out.println(attributes.getLength());
                    
                    System.out.println(qName);
                    
                    if(qName == null){
                           System.out.println("name is null");
                    }
                    
                    node.attributes = attributes;
                   
                    System.out.println("printing qName from attributes list" + node.attributes.getQName(0));
                    
                    stack.push(node);
                    
                    if(currentNode != null){
                        currentNode.properties.put(localName, qName);
                    } else{
                        currentNode = node;
                    }
                }
                
                @Override
                public void endElement(String uri, String localName, String qName){
                    Node poppedNode = (Node)stack.pop();
                    System.out.println(poppedNode);
                    System.out.println("In endElement: poppedNode.name = " + poppedNode.name);
                    System.out.println("In endElement: poppedNode.content = " + poppedNode.content);
                    poppedNode.content = poppedNode.content.trim();
                    if (stack.isEmpty()) {
                        currentNode = null;
                    } else {
                        currentNode = (Node) stack.pop();
                    }
                  
                }
                
                @Override
                public void characters(char ch[], int start, int length) throws SAXException{
                    if(node != null){
                            node.content = new String(ch, start, length);
                            System.out.println("inside characters " + node.content);
                    }
                }
            };
            
            saxParser.parse(xmlCourseFile.getAbsoluteFile(), handler);
            
        } catch (Exception e){ 
            throw e;
        }
        
        return root;
        }
}
