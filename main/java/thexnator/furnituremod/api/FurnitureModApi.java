package thexnator.furnituremod.api;

public class FurnitureModApi {
	public static interface ApiProvider {
		public <T extends IApiInterface> T getApi(Class<T> cls);

		public <T extends IApiInterface> boolean isApiPresent(Class<T> cls);
	}

	private FurnitureModApi() {}

	private static ApiProvider provider;

	// FurnitureMod will use this method to provide actual implementation
	public static void init(ApiProvider provider) {
		if (FurnitureModApi.provider != null) throw new IllegalStateException("API already initialized");
		FurnitureModApi.provider = provider;
	}

	/**
	 * @deprecated Use {@link ApiHolder}.
	 */
	@Deprecated
	public static <T extends IApiInterface> T getApi(Class<T> cls) {
		if (provider == null) throw new IllegalStateException("API not initialized");
		return provider.getApi(cls);
	}

	public static <T extends IApiInterface> boolean isApiPresent(Class<T> cls) {
		if (provider == null) throw new IllegalStateException("API not initialized");
		return provider.isApiPresent(cls);
	}
}
