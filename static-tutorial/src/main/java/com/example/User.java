package com.example;

public class User {
	
	public static final String DEFAULT_USER;
	static {
		DEFAULT_USER = "Java 21";
		// more funny code possible..
	}
	
    private String forename;
    private String surname;
    private String address;

    // Constructor
    public User(String forename, String surname, String address) {
        this.forename = forename;
        this.surname = surname;
        this.address = address;
    }

    // Default Constructor
    public User() {
    }

    // Getters
    public String getForename() {
        return forename;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    // Setters
    public void setForename(String forename) {
        this.forename = forename;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // toString Method for Debugging/Printing
    @Override
    public String toString() {
        return "User{" +
                "forename='" + forename + '\'' +
                ", surname='" + surname + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
