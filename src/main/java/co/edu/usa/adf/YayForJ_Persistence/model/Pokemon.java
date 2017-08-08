package co.edu.usa.adf.YayForJ_Persistence.model;


import co.edu.usa.adf.YayForJ_Persistence.annotation.Column;
import co.edu.usa.adf.YayForJ_Persistence.annotation.Entity;
import co.edu.usa.adf.YayForJ_Persistence.annotation.Id;
import co.edu.usa.adf.YayForJ_Persistence.annotation.Table;

@Entity
@Table(name="pokemon")
public class Pokemon {

    @Id(isAutoincremental = false, name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "first_type")
    private String firstType;
    @Column(name = "second_type")
    private String secondType;
    @Column(name = "sex")
    private String sex;
    @Column(name = "trainer_id")
    private int trainer_id;

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

    public String getFirstType() {
        return firstType;
    }

    public void setFirstType(String firstType) {
        this.firstType = firstType;
    }

    public String getSecondType() {
        return secondType;
    }
    public void setSecondType(String secondType) {
        this.secondType = secondType;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getTrainer_id() {
        return trainer_id;
    }

    public void setTrainer_id(int trainer_id) {
        this.trainer_id = trainer_id;
    }

    @Override
    public String toString() {
        return "Pokemon [id=" + id + ", name=" + name + ", firstType=" + firstType + ", secondType=" + secondType
                + ", sex=" + sex + ", trainer_id=" + trainer_id + "]";
    }

}