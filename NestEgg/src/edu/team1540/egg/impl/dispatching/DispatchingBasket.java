package edu.team1540.egg.impl.dispatching;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.team1540.common.core.bluetooth.Address;
import org.team1540.common.core.bluetooth.ConnectedThreadPreserver;
import org.team1540.common.core.cache.CachedList;
import org.team1540.common.core.schema.Schema;
import org.team1540.common.system.ConnectionHandler;

import edu.team1540.egg.core.FragmentBasket;

public class DispatchingBasket<S extends Schema> extends FragmentBasket {

	public DispatchingBasket(final String dispatching, final Address address, final ConnectionHandler h, final S start, final BasketDrawCallbacks a, final String title, final DispatchingFragment<S>... fragments) {
		super(a, title, fragments);
		final AtomicReference<S> currentSchema = new AtomicReference<S>(start);
		final ConnectedThreadPreserver callback = new ConnectedThreadPreserver(h, null);
		final List<String> cachingList;
		try {
			cachingList = new CachedList<String>(dispatching, new File(fragments[0].getActivity().getFilesDir(), "dispatching"));
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		h.connectTo(address, callback);
		for (final DispatchingFragment<S> dispatchFragment : fragments) {
			dispatchFragment.setup(callback, currentSchema, cachingList);
		}
	}
}
