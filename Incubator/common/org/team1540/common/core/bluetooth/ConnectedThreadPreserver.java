package org.team1540.common.core.bluetooth;

import java.util.concurrent.atomic.AtomicReference;

import org.team1540.common.core.pattern.Callback;
import org.team1540.common.system.ConnectedThread;
import org.team1540.common.system.ConnectionHandler;

public class ConnectedThreadPreserver implements Callback<ConnectedThread> {

	private final Address target;
	private final ConnectionHandler handler;
	private final AtomicReference<ConnectedThread> refrence;

	public ConnectedThreadPreserver(ConnectionHandler h, ConnectedThread c) {
		handler = h;
		refrence = new AtomicReference<ConnectedThread>(c);
		target = c.getTarget();
	}

	public ConnectedThread get() {
		if (refrence.get() == null) {
			callback(null);
		}
		return refrence.get();
	}

	@Override
	public void callback(ConnectedThread value) {
		handler.connectTo(target, new Callback<ConnectedThread>() {
			@Override
			public void callback(ConnectedThread value) {
				refrence.set(value);
			}
		});
	}
}
