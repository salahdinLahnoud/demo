package com.example.demo;
import org.hibernate.validator.constraints.NotBlank;

public class SearchCriteria {

    @NotBlank(message = "name can't empty!")
    String name;
//    @NotBlank(message = "bedrijf can't empty!")
//    String bedrijf;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//	public String getBedrijf() {
//		return bedrijf;
//	}
//
//	public void setBedrijf(String bedrijf) {
//		this.bedrijf = bedrijf;
//	}
//    
}
