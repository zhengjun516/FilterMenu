package com.filtermenu;

import java.util.ArrayList;
import java.util.List;

import com.filtermenu.model.City;
import com.filtermenu.model.District;
import com.filtermenu.model.JobType;

public class DataManager {

	
	public static List<City> getCities(){
		List<City> cities = new ArrayList<City>();
		for(int i=0;i<20;i++){
			City city = new City();
			city.id = i;
			city.name = "城市"+i;
			List<District> districts = new ArrayList<District>();
			for(int j=0;j<i;j++){
				District district = new District();
				district.id = j;
				district.name = "区域"+j;
				districts.add(district);
			}
			city.districts = districts;
			cities.add(city);
		}
		return cities;
	}
	
	
	public static List<JobType> getJobTypes(){
		List<JobType> jobTypes = new ArrayList<JobType>();
		for(int i=0;i<20;i++){
			JobType jobType = new JobType();
			jobType.id = i;
			jobType.name = "类型"+i;
			jobTypes.add(jobType);
		}
		return jobTypes;
	}
}
