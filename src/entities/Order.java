package entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.enums.OrderStatus;

public class Order {
	
	public static SimpleDateFormat birth = new SimpleDateFormat("dd/MM/yyyy");
	public static SimpleDateFormat momentOrder = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	private Date moment;
	private OrderStatus status;

	List<OrderItem> orderItem = new ArrayList<>();
	private Client client;

	public Order() {
	}

	public Order(Date moment, OrderStatus status, Client client) {
		super();
		this.moment = moment;
		this.status = status;
		this.client = client;
	}

	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<OrderItem> getOrderItem() {
		return orderItem;
	}
	
	public void addItem(OrderItem item)
	{
		orderItem.add(item);
	}
	
	public void removeItem(OrderItem item)
	{
		orderItem.remove(item);
	}
	
	public double total()
	{
		double sum = 0;
		for (OrderItem c: orderItem)
		{
			sum += c.subTotal();
		}
		return sum;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Order Moment: " + momentOrder.format(moment) + "\n");
		sb.append("Order Status " + status + "\n");
		sb.append("Client: " + client.getName() + "(" + birth.format(client.getBirthDate()) + ") - ");
		sb.append(client.getEmail() + "\n");
		sb.append("Order items: \n");
		for (OrderItem c : orderItem)
		{
			sb.append(c.getProduct().getName() + ", $" + String.format("%.2f", c.getPrice()) + ", Quantity: "
					+ c.getQuantity() + ", Subtotal: $" + String.format("%.2f", c.subTotal()) + "\n");
		}
		sb.append("Total Price: $" + String.format("%.2f", total()));
		return sb.toString();
	}

}
