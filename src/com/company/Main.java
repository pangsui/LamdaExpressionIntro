package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        new Thread(()->{
            System.out.println("implementation with lamda expression reduces a lot of codes");
            System.out.println("line 2");
            System.out.format("line %d\n", 3);
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
        }).start();

        Employee pangsui = new Employee("Pangsui",30);
        Employee lingeh = new Employee("Lingeh", 29);
        Employee abubakar = new Employee("Abubakar",35);
        Employee thalut = new Employee("Thalut",28);
        Employee mahmud = new Employee("Mahmud",31);

        List<Employee> employees = new ArrayList<Employee>();
        employees.add(thalut);
        employees.add(pangsui);
        employees.add(lingeh);
        employees.add(mahmud);
        employees.add(abubakar);


        for (Employee employee : employees){
            new Thread(()->{
                System.out.println("name inside lamda expression "+ employee.getName());
                System.out.println("age inside lamda expression "+ employee.getAge());
            }).start();
        }

        System.out.println("===============================================");
        System.out.println("using the forEach array method with lamda expression");
        employees.forEach(employee -> {
            System.out.println(employee.getName());
            System.out.println(employee.getAge());
        });

        System.out.println("===============================================");
//        Collections.sort(employees, new Comparator<Employee>() {
//            @Override
//            public int compare(Employee employee1, Employee employee2) {
//                return employee1.getName().compareTo(employee2.getName());
//            }
//        });


        Collections.sort(employees,(employee1, employee2) ->
                employee1.getName().compareTo(employee2.getName()));
        for (Employee employee : employees){
            System.out.println(employee.getName()+ ":"+employee.getAge());
        }
        System.out.println("===========================================");
//        String sillyString = doStringStuff(new UpperConcat() {
//            @Override
//            public String upperAndConcat(String s1, String s2) {
//                return s1.toUpperCase() + s2.toUpperCase();
//            }
//        },
//        employees.get(0).getName() , employees.get(1).getName());
//        System.out.println(sillyString);
        UpperConcat uc = ( s1, s2) ->{
            String result = s1.toUpperCase() + s2.toUpperCase();
            return result;
        };

        String sillyString = doStringStuff(uc,employees.get(0).getName(),employees.get(1).getName());
        System.out.println(sillyString);

        AnotherClass anotherClass = new AnotherClass();
        String s = anotherClass.doSomething();
        System.out.println(s);
    }

    public final static String doStringStuff(UpperConcat uc, String s1, String s2){
        return uc.upperAndConcat(s1,s2);
    }


}

class Employee{
    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

interface UpperConcat{
    public String upperAndConcat(String s1, String s2);
}
class AnotherClass{
    public String doSomething() {
       final int i = 0;
        UpperConcat uc = (s1,s2)->{
            System.out.println("The lamda expression class is "+getClass().getSimpleName());
            System.out.println("The value of i inside lamda expression is " + i);
            String result = s1.toUpperCase() + s2.toUpperCase();
            return result;
        };
        System.out.println("The upperClass name is "+getClass().getSimpleName());
        return Main.doStringStuff(uc,"string1","string2");
//        return Main.doStringStuff(new UpperConcat() {
//            @Override
//            public String upperAndConcat(String s1, String s2) {
//                return s1.toUpperCase() + s2.toUpperCase();
//            }
//        }," string1"," string2");
    }
    public void printValue(){
        int number = 25;
        Runnable r = () ->{
            try{
                Thread.sleep(1000);
            }catch ( InterruptedException E ){
                E.printStackTrace();
            }
            System.out.println("the value of number is "+number);
        };
        new Thread(r).start();
    }
}