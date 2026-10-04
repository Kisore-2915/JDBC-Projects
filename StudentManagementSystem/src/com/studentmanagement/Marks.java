package com.studentmanagement;

public class Marks {
	 private int markId;
	    private int studentId;
	    private String subject;
	    private int marks;

	    public Marks() {
	    }

	    public Marks(int markId, int studentId,
	                 String subject, int marks) {

	        this.markId = markId;
	        this.studentId = studentId;
	        this.subject = subject;
	        this.marks = marks;
	    }

	    public Marks(int studentId,
	                 String subject, int marks) {

	        this.studentId = studentId;
	        this.subject = subject;
	        this.marks = marks;
	    }

	    public int getMarkId() {
	        return markId;
	    }

	    public void setMarkId(int markId) {
	        this.markId = markId;
	    }

	    public int getStudentId() {
	        return studentId;
	    }

	    public void setStudentId(int studentId) {
	        this.studentId = studentId;
	    }

	    public String getSubject() {
	        return subject;
	    }

	    public void setSubject(String subject) {
	        this.subject = subject;
	    }

	    public int getMarks() {
	        return marks;
	    }

	    public void setMarks(int marks) {
	        this.marks = marks;
	    }
}
