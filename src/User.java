/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mudit
 */
class User {
    private int sid,price;
    private String name,audi,start;
    public User(int sid,int price,String name,String audi,String start){
        
        this.sid=sid;
        this.name=name;
        this.price=price;
        this.audi=audi;
        this.start=start;
        
    }
    public int getsid(){
        return sid;
    }
    public int getprice(){
        return price;
    }
    public String getName(){
        return name;
    }
    public String getAudi(){
        return audi;
    }
    public String getStart(){
        return start;
    }
}
