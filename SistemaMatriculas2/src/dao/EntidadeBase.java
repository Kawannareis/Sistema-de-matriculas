package dao;

public abstract class EntidadeBase {
    protected int id;

    public EntidadeBase(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}