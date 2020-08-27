package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxServices;
import model.services.RentalService;


public class Program {

	public static void main(String[] args) throws ParseException{
		Locale.setDefault(Locale.US);
		Scanner scanner = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");
		
		System.out.println("Enter tental data:");
		System.out.print("Car model: ");
		String carModel = scanner.nextLine();
		System.out.print("Pickup (dd/MM/yyyy HH:ss): ");
		Date start = sdf.parse(scanner.nextLine());
		System.out.print("Return  (dd/MM/yyyy HH:ss): ");
		Date finish = sdf.parse(scanner.nextLine());
		
		CarRental car = new CarRental(start, finish, new Vehicle(carModel));
		
		System.out.print("Enter price per hours: ");
		double pricePerHours = scanner.nextDouble();
		System.out.print("Enter price per day: ");
		double pricePerDay = scanner.nextDouble();
		
		RentalService rentalService = new RentalService(pricePerDay, pricePerHours, new BrazilTaxServices());
		
		rentalService.processInvoice(car);
		
		System.out.println("Invoice: ");
		System.out.println("Basic payment: " + String.format("%.2f",car.getInvoice().getBasicPayment()));
		System.out.println("Tax: " + String.format("%.2f",car.getInvoice().getTax()));
		System.out.println("Total payment: " + String.format("%.2f",car.getInvoice().getTotalPayment()));
		
		
		scanner.close();
	}

}
