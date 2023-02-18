package ua.bv.vacations.vacationitems;

import java.util.Random;

class VacationItem {
    int id;
    String name;
    String category;
    int[] days;
    byte transType;
    Boolean includeFood;

    VacationItem(String name, String cat, int[]day, byte transType,Boolean food){
            Random rand = new Random();
            this.id = rand.nextInt(100,999);
            this.name = name;
            this.category = cat;
            this.days = day;
            this.transType = transType;
            this.includeFood = food;
    }

    @Override
    public String toString(){
        return "--------------------------------" +
                this.name
                + "--------------------------------";
    }
}
