package org.tronhook;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.jooby.Env;
import org.jooby.Jooby.Module;
import org.tronhook.api.TronHook;

import com.google.inject.Binder;
import com.typesafe.config.Config;

public class TronHookNodeModule implements Module{

	@Override
	public void configure(Env env, Config conf, Binder binder) throws Throwable {
		
		
		binder.bind(TronHook.class).toProvider(()->{
			
			TronHook hook;
			try {
				Class<?> clazz = Class.forName("org.tronhook.hook.AccountSyncElasticHook");
				Constructor<?> cons = clazz.getConstructor(Config.class);

				hook = (TronHook) cons.newInstance(conf);
				return hook;
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		});
		
	}

}
