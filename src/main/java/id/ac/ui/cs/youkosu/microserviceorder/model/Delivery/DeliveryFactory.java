package id.ac.ui.cs.youkosu.microserviceorder.model.Delivery;

public class DeliveryFactory {
    public static Delivery getDelivery(String method) {
        Delivery delivery = new Delivery();
        switch (method) {
            case "JTE":
                delivery.setDeliveryMethod(new JTEDelivery());
                break;
            case "SiWuzz":
                delivery.setDeliveryMethod(new SiWuzzDelivery());
                break;
            case "Gobek":
                delivery.setDeliveryMethod(new GobekDelivery());
                break;
            default:
                throw new IllegalArgumentException("Invalid delivery method: " + method);
        }
        return delivery;
    }
}
