package com.eazybank.config;

class MyGeneric<T>{
    private T obj;
    MyGeneric(T obj){
        this.obj = obj;
    }

    public T getGenericValue(){
        return obj;
    }
}

public class GenericClassDemo {
    public static void main(String[] args) {
        MyGeneric generic1 = new MyGeneric("Amit");
        System.out.println(generic1.getGenericValue());

        MyGeneric generic2 = new MyGeneric(1231);
        System.out.println(generic2.getGenericValue());

        MyGeneric generic3 = new MyGeneric(156.98);
        System.out.println(generic3.getGenericValue());

    }
}
