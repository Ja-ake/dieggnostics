package edu.team1540.egg.impl.dispatching;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.team1540.common.core.bluetooth.ConnectedThreadPreserver;
import org.team1540.common.core.pattern.Callback;
import org.team1540.common.core.pattern.Tuple;
import org.team1540.common.core.schema.Schema;

import edu.team1540.egg.core.ScoutingFragment;

public abstract class DispatchingFragment<S extends Schema> extends ScoutingFragment implements Callback<Tuple<Boolean, String>> {

	private ConnectedThreadPreserver c;
	private AtomicReference<S> schema;

	public DispatchingFragment(final int layoutID) {
		super(layoutID);
	}

	protected void setup(final ConnectedThreadPreserver c, final AtomicReference<S> schema, final List<String> backups) {
		this.c = c;
		this.schema = schema;
		c.callbacks = new Callback<Tuple<Boolean, String>>() {
			@Override
			public void callback(Tuple<Boolean, String> value) {
				if (value.a) {
					backups.add(value.b);
				}
			}
		};
	}

	public S getSchema() {
		return schema.get();
	}

	public void setSchema(final S s) {
		schema.set(s);
	}

	public boolean submitSchema() {
		if (c.get() == null) {
			return false;
		}
		c.get().sendMessage(Schema.serilizeSchema(schema.get()), null);
		return true;
	}
}
