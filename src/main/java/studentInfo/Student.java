package studentInfo;

public class Student {

    private final String[] info;

    public Student(String[] info){
        this.info = info;
        //info: 0 - ID, 1 - First Name, 2 - Last Name, 3 - Major, 4 - Year
    }

    public String[] getInfo(){
        return this.info;
    }
}
