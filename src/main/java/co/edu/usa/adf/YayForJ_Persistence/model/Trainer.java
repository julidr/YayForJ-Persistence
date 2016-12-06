package co.edu.usa.adf.YayForJ_Persistence.model;

import java.util.Date;

import co.edu.usa.adf.YayForJ_Persistence.annotation.Entity;
import co.edu.usa.adf.YayForJ_Persistence.annotation.Id;
import co.edu.usa.adf.YayForJ_Persistence.annotation.Table;

@Entity
@Table(name="trainers")
public class Trainer {
	
	@Id private int id;
	private String name;
	private String lastname;
	private int age;
	private Date birthday;
	
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
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "Trainer [id=" + id + ", name=" + name + ", lastname=" + lastname + ", age=" + age + ", birthday="
				+ birthday + "]";
	}
}
