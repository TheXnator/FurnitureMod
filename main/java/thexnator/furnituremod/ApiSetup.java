package thexnator.furnituremod;

import net.minecraftforge.fml.common.discovery.ASMDataTable;
import thexnator.furnituremod.access.ApiFactory;
import thexnator.furnituremod.access.ApiProviderBase;
import thexnator.furnituremod.access.ApiProviderRegistry;
import thexnator.furnituremod.api.ApiHolder;
import thexnator.furnituremod.api.FurnitureModApi;
import thexnator.furnituremod.api.IApiInterface;

public class ApiSetup {

	private static class ApiProviderAdapter extends ApiProviderBase<IApiInterface> implements thexnator.furnituremod.api.FurnitureModApi.ApiProvider {
		public ApiProviderAdapter(ApiProviderRegistry<IApiInterface> apiRegistry) {
			super(apiRegistry);
		}
	}

	private final ApiProviderRegistry<IApiInterface> registry = new ApiProviderRegistry<IApiInterface>(IApiInterface.class);

	ApiSetup() {}

	public void setupApis() {
		registry.freeze();
	}

	public void installHolderAccess(ASMDataTable table) {
		ApiFactory.instance.createApi(ApiHolder.class, IApiInterface.class, table, registry);
	}

	void injectProvider() {
		try {
			FurnitureModApi.init(new ApiProviderAdapter(registry));
		} catch (Throwable t) {
			final String apiSource = getApiSource();
			throw new IllegalStateException(String.format("Failed to register FurnitureMod API provider (ApiAccess source: %s)", apiSource), t);
		}
	}

	private static String getApiSource() {
		try {
			return thexnator.furnituremod.api.FurnitureModApi.ApiProvider.class.getProtectionDomain().getCodeSource().getLocation().toString();
		} catch (Throwable t) {
			Log.severe(t, "Failed to get FurnitureMod API source");
			return "<unknown, see logs>";
		}
	}

}
