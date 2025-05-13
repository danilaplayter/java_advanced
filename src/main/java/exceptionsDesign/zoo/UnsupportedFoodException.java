package exceptionsDesign.zoo;

public class UnsupportedFoodException extends FeedingException {

  private final String animalName;
  private final String foodType;

  public UnsupportedFoodException(String animalName, String foodType, String message) {
    super(message);
    this.animalName = animalName;
    this.foodType = foodType;
  }

  public String getAnimalName() {
    return animalName;
  }

  public String getFoodType() {
    return foodType;
  }
}
