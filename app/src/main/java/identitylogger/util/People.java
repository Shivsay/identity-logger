package identitylogger.util;

public class People {
    int dbID;
    String name;

    public People(int dbID, String name) {
        this.dbID = dbID;
        this.name = name;
    }

    //the JList renderer calls toString to get th evalue to show
    @Override
    public String toString() {
        return this.name;
    }

    public int getDBID() {
        return this.dbID;
    }
}
