package org.tronhook;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.jooby.Env;
import org.jooby.Jooby.Module;
import org.tronhook.api.ITronHook;
import org.tronhook.api.TronHook;

import com.google.inject.Binder;
import com.typesafe.config.Config;

public class TronHookNodeModule implements Module{

	@Override
	public void configure(Env env, Config conf, Binder binder) throws Throwable {
		
		Class<ITronHook> clazz = (Class<ITronHook>) Class.forName("org.tronhook.hook.AccountSyncElasticHook");
		
		binder.bind(ITronHook.class).to(clazz);
		
//		binder.bind(ITronHook.class).toProvider(()->{
//			
//			ITronHook hook;
//			try {
//				Class<?> clazz = Class.forName("org.tronhook.hook.AccountSyncElasticHook");
//				Constructor<?> cons = clazz.getConstructor(Config.class);
//
//				hook = (ITronHook) cons.newInstance(conf);
//				
//				System.out.println("========>"+ hook.getClass());
//				
//				return hook;
//			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			return null;
//		});
		
	}

}
