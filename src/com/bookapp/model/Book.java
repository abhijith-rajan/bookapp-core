package com.bookapp.model;

public class Book {
	private int id;
	private String name;
	private int price;
	private String author;
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", price=" + price + ", author=" + author + "]";
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
}
