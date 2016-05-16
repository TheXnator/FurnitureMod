package thexnator.furnituremod.api;

public enum RecipeType {
	
	OVEN("oven"),
	WARDROBE("wardrobe");
	
	private String name;
	
	private RecipeType(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
}