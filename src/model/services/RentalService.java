package model.services;

import model.entities.CarRental;
import model.entities.Invoice;

public class RentalService {
	
	private Double pricePerDay;
	private Double pricePerHours;
	
	private BrazilTaxServices taxService;

	public RentalService(Double pricePerDay, Double pricePerHours, BrazilTaxServices taxService) {
		this.pricePerDay = pricePerDay;
		this.pricePerHours = pricePerHours;
		this.taxService = taxService;
	}

	public Double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(Double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public Double getPricePerHours() {
		return pricePerHours;
	}

	public void setPricePerHours(Double pricePerHours) {
		this.pricePerHours = pricePerHours;
	}

	public BrazilTaxServices getTaxService() {
		return taxService;
	}

	public void setTaxService(BrazilTaxServices taxService) {
		this.taxService = taxService;
	}
	
	public void processInvoice(CarRental carRental) {
		long t1 = carRental.getStart().getTime();
		long t2 = carRental.getFinish().getTime();
		
		double hours = (double) (t2-t1)/1000/60/60;
		double basicPayment;
		
		if(hours <= 12.0) {
			basicPayment = Math.ceil(hours) * pricePerHours;
		}
		else {
			basicPayment = Math.ceil(hours / 24) * pricePerDay; 
		}
		
		double tax = taxService.tax(basicPayment);
	
		carRental.setInvoice(new Invoice(basicPayment, tax));
	}
	
}
