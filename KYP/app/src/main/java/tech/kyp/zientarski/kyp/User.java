package tech.kyp.zientarski.kyp;

/**
 * Created by xavy_ on 11/12/2017.
 */

public class User {

    private static User instance = null;

    public String name;
    public String id;
    public int gender; //1 - M, 2 - F, 3 - Other
    public int race;
    public int religion;
    public int politics;

    public static User getInstance() {
        if(instance == null) {
            instance = new User();
        }
        return instance;
    }


    public void clearData() {
        name = "";
        id = "";
        gender = -1;
        race = -1;
        religion = -1;
        politics = -1;
    }


}
