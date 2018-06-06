package xpxpxp.vdiodemo.sqlite;

public class Ceshi {
    private int id;
    private String name;
    private int age;
    private long date;

    public Ceshi(int id, String name, int age, long date) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
