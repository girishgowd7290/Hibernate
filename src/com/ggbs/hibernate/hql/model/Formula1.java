package com.ggbs.hibernate.hql.model;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Formula1 {

	@Id
	private int carNumber;
	private String raceDriver;
	private String raceTeam;
	public int getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(int carNumber) {
		this.carNumber = carNumber;
	}
	public String getRaceDriver() {
		return raceDriver;
	}
	public void setRaceDriver(String raceDriver) {
		this.raceDriver = raceDriver;
	}
	public String getRaceTeam() {
		return raceTeam;
	}
	public void setRaceTeam(String raceTeam) {
		this.raceTeam = raceTeam;
	}
	@Override
	public String toString() {
		return "Formula1 [carNumber=" + carNumber + ", raceDriver=" + raceDriver + ", raceTeam=" + raceTeam + "]";
	}
	
}
