package com.example.demo;

import java.util.List;

public class AjaxResponseBody {

    String msg;
    List<Users> result;
    List<Klant> klant;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<Users> getResult() {
		return result;
	}
	public void setResult(List<Users> result) {
		this.result = result;
	}
	
	public List<Klant> getKlant() {
		return klant;
	}
	public void setKlant(List<Klant> klant) {
		this.klant = klant;
	}
	@Override
	public String toString() {
		return "AjaxResponseBody [msg=" + msg + ", result=" + result + "]";
	}
}
