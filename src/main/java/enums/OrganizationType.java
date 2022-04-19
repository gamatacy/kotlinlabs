package Enums;

import Collection.Classes.Organization;

public enum OrganizationType {
    COMMERCIAL,
    PUBLIC,
    TRUST,
    OPEN_JOINT_STOCK_COMPANY;

    public static OrganizationType equals(String str){
        for (OrganizationType organizationType : OrganizationType.values()){
            if (organizationType.name().equalsIgnoreCase(str)){
                return organizationType;
            }
        }
        return null;
    }
}
