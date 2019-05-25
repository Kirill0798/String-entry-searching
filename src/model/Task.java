package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Task {
    private String first;
    private String second;
    private String result;

    public Task(){

    }

    public Task(String first, String second){
        this.first = first;
        this.second = second;
    }



    public void setFirst(String first){
        this.first = first;
    }

    public String getFirst(){
        return first;
    }

    public void setSecond(String second){
        this.second = second;
    }

    public String getSecond(){
        return second;
    }

    public void setResult(String result){
        this.result = result;
    }

    public String getResult(){
        return result;
    }
}
