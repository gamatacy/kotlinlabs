package Enums;

public enum UnitOfMeasure {
    KILOGRAMS,
    METERS,
    GRAMS,
    MILLIGRAMS;

    public static UnitOfMeasure equals(String str){
        for (UnitOfMeasure uom : UnitOfMeasure.values()){
            if (uom.name().equalsIgnoreCase(str)){
                return uom;
            }
        }
        return null;
    }
}
