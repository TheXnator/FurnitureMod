package thexnator.furnituremod.network.event;

import com.google.common.base.Preconditions;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.LoaderState;
import thexnator.furnituremod.datastore.DataStoreBuilder;
import thexnator.furnituremod.datastore.IDataVisitor;
import thexnator.furnituremod.network.IdSyncManager;
import thexnator.furnituremod.utils.io.TypeRW;

public class NetworkEventManager {

	public static class RegistrationContext {
		private int currentId = 0;

		private final DataStoreBuilder<String, Integer> builder;

		private RegistrationContext() {
			this.builder = IdSyncManager.INSTANCE.createDataStore("events", String.class, Integer.class);

			this.builder.setDefaultKeyReaderWriter();
			this.builder.setValueReaderWriter(TypeRW.VLI_SERIALIZABLE);
		}

		public RegistrationContext register(Class<? extends NetworkEvent> cls) {
			Preconditions.checkState(Loader.instance().isInState(LoaderState.PREINITIALIZATION), "This method can only be called in pre-initialization state");

			builder.addEntry(cls.getName(), currentId++);
			return this;
		}

		void register(IDataVisitor<String, Integer> eventIdVisitor) {
			builder.addVisitor(eventIdVisitor);
			builder.register();
		}
	}

	private NetworkEventManager() {}

	public static final NetworkEventManager INSTANCE = new NetworkEventManager();

	private final NetworkEventRegistry registry = new NetworkEventRegistry();

	private final NetworkEventDispatcher dispatcher = new NetworkEventDispatcher(registry);

	private RegistrationContext registrationContext = new RegistrationContext();

	public RegistrationContext startRegistration() {
		Preconditions.checkState(Loader.instance().isInState(LoaderState.PREINITIALIZATION), "This method can only be called in pre-initialization state");
		return registrationContext;
	}

	public void finalizeRegistration() {
		registrationContext.register(registry);
		registrationContext = null;
	}

	public NetworkEventDispatcher dispatcher() {
		return dispatcher;
	}
}
