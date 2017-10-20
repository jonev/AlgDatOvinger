package oving4Trer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

/**
 * Created by JoneSkole on 04.09.2017.
 */
public class o4 {
    public static void main(String[] args) {
        // for (int i = 3; i < 100; i*=5) {
        //     TimeTaking t1 = new TimeTaking(40);
        //     for (int j = 0; j < 40; j++) {
        //         CircleList c1 = new CircleList(100000);
        //         t1.start();
        //         int who = c1.whoSurvives(i);
        //         t1.stop();
        //         //System.out.println("Antall " + i + " span " + 100 + " Nr " + who + " overlever");
        //     }
        //     System.out.println("Span " + i + " tidsforbruk " + t1.finish());
        // }
        // CircleList c2 = new CircleList(10);
        // System.out.println("10 stk, span 4, overlever: " + c2.whoSurvives(4));
//
        CircleList c3 = new CircleList(10000);
        System.out.println("overlever: " + c3.whoSurvives(12));

        StringStack s = new StringStack(5);


    }
}

// oppgave 2
class SimpleCompiler{ // jUnit tester
    private StringStack stack = new StringStack(10);

    public ArrayList<String>  compile(String filepath){
        ArrayList<String> errors = new ArrayList<String>();
        FileReader fileReader = null;
        BufferedReader reader = null;
        try {
            fileReader = new FileReader(filepath);
            reader = new BufferedReader(fileReader);
            try {
                String line;
                int linenumber = 1;
                while ((line = reader.readLine()) != null){
                    for (Character c: line.toCharArray()) {
                        // insert to stack
                        if(nextSymbol(c)) errors.add("Error on " + c.toString() + " at line " + linenumber);
                    }
                    linenumber++;
                }
            } catch (Exception r){

            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
                reader.close();
            }catch (Exception f){

            }

        }
        while(!stack.empty()){
            errors.add("Error on " + stack.pop());
        }
        return errors;
    }

    public boolean nextSymbol(Character c){
        switch (c){
            case '{':
                stack.push(c);
                break;
            case '}':
                if(stack.empty() ||  !stack.getLast().equals('{')) {
                    return true;
                } else {
                    stack.pop();
                    return false;
                }
            case '[':
                stack.push(c);
                break;
            case ']':
                if(stack.empty() ||  !stack.getLast().equals('[')) {
                    return true; // error
                } else {
                    stack.pop();
                    return false;
                }
            case '(':
                stack.push(c);
                break;
            case ')':
                if(stack.empty() ||  !stack.getLast().equals('(')) {
                    return true;
                } else {
                    stack.pop();
                    return false;
                }
            default:
                return false;
        }
        return false;
    }


}

// oppgave 1
class StringStack{
    private Character[] stack;
    private int number = 0;

    public StringStack(int startsize){
        stack = new Character[startsize];
    }

    public void push(Character n){
        if(number >= stack.length) expandStack();
        stack[number++] = n;
    }

    public Character pop(){
        if(number <= 0) return null;
        return stack[--number];
    }

    public void expandStack(){
        int newLength = stack.length*2;
        Character[] newStack = new Character[newLength];
        for (int i = 0; i < stack.length; i++) {
            newStack[i] = stack[i];
        }
        stack = newStack;
    }

    public Character getLast(){
        return get(number);
    }

    public Character get(int index){
        if(index < 1 || index > number) return null;
        return stack[index-1];
    }

    public boolean empty(){
        return (number == 0);
    }
}


class Node {
    Node next;
    Object value;

    public Node(int value) {
        this.value = value;
    }

    public Node(String value) {
        this.value =  value;
    }
    public void setNext(Node next) {
        this.next = next;
    }

    public Object getValue(NodeType nodeType) {
        switch (nodeType){
            case integer:
                return (int)value;
            case String:
                return (String)value;
        }
        return null;
    }

    public Node getNext() {
        return next;
    }
}

enum NodeType{
    String, integer
}

class CircleList{
    private int size = 1;
    Node head;

    public CircleList(int size) {
        if(size < 1) throw new IllegalArgumentException("Size can not be smaller than 1");
        head = new Node(1);
        head.setNext(head);
        Node last = head;
        for (int i = 2; i <= size; i++) {
            Node n = new Node(i);
            insert(last, n);
            last = n;
        }
    }

    public int whoSurvives(int span){
        if(span < 2) throw new IllegalArgumentException();
        Node last = getHead();
        Node current = last.getNext();
        int count = 2;
        while (getSize() > 1){ // n
            if(count%span==0){
                delete(last, current);
                current = last.getNext();
            } else {
                last = current;
                current = current.getNext();
            }
            count++;
        }
        System.out.println("Antall if'er: " + count);
        return (int)head.getValue(NodeType.integer);
        // return (int)head.getNext().getValue(NodeType.integer);
    } // 1 +1 +1 + ((antFolk - 1)*(span*(1 + 1 + 2 + 1)) = 3 + ((antFolk - 1) * (4span) = 4spanAntFolk - 4span + 3
    // 4nm - 4m + 3
    //

    public void insert(Node last, Node insert){
        insert.setNext(last.getNext());
        last.setNext(insert);
        size++;
        //System.out.println("Setter inn " + insert.getPlacenr());
    }

    public void delete(Node last, Node tobedeleted){
        if(head.equals(tobedeleted)) {
            head = tobedeleted.getNext();
            last.setNext(head);
        } else {
            last.setNext(tobedeleted.getNext());
        }

        tobedeleted.setNext(null);
        size--;
        //System.out.println("Sletter " + tobedeleted.getPlacenr());
    }

    public void delete(int personnr){
        if(personnr <= 0) return;
        Node last = head;
        Node current = head.getNext();
        while (true){
            if((int)current.getValue(NodeType.integer) == personnr){
                delete(last, current);
                break;
            }
            last = current;
            current = current.getNext();
        }
    }

    public int getSize() {
        return size;
    }

    public Node getHead() {
        return head;
    }
}