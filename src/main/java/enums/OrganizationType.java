package enums;

public enum OrganizationType {
    COMMERCIAL,
    PUBLIC,
    TRUST,
    OPEN_JOINT_STOCK_COMPANY;

    public static OrganizationType equals(String str){
        for (OrganizationType organizationType : OrganizationType.values()){
            if (organizationType.name().equals(str)){
                return organizationType;
            }
        }
        return null;
    }
}
