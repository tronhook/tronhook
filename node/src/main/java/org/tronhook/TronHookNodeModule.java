package org.tronhook;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.jooby.Env;
import org.jooby.Jooby.Module;
import org.tronhook.api.ITronHook;

import com.google.inject.Binder;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.typesafe.config.Config;

public class TronHookNodeModule implements Module{
	
	private final String TRON_HOOK_ID_FIELD_NAME= "TRON_HOOK_ID";
	
	public TronHookNodeModule() {
		
	}

	
	@Override
	public void configure(Env env, Config conf, Binder binder) throws Throwable {
		

			    
		env.onStart((r)->{
			
			ITronHook hook = r.require(ITronHook.class);
			
			if (hook!=null) {
				hook.onNodeStart();
			}
			
		});
		
		env.onStop((r)->{
			
			ITronHook hook = r.require(ITronHook.class);
			
			if (hook!=null) {
				hook.onNodeStop();
			}			
			
		});
		
		
		
		
		binder.bind(String.class)
        .annotatedWith(Names.named("hook.id"))
        .toProvider(()->{
        	
			try {
				Class<?> clazz = Class.forName(conf.getString("hook"));

				Constructor<?> cons = clazz.getConstructor(Config.class);

				Field hookIdField = clazz.getField(TRON_HOOK_ID_FIELD_NAME);

				if (hookIdField!=null) {
					String hookId = (String) hookIdField.get(hookIdField);

					return hookId;
				}
				
				return null;
			} catch (IllegalAccessException | ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalArgumentException | NoSuchFieldException e) {

				e.printStackTrace();
			}
        	
			return null;
        });
		
		binder.bind(ITronHook.class).toProvider(()->{
			
			ITronHook hook;
			try {
				Class<?> clazz = Class.forName(conf.getString("hook"));


				Constructor<?> cons = clazz.getConstructor(Config.class);

				Field hookIdField = clazz.getField(TRON_HOOK_ID_FIELD_NAME);
				
				Config hookConfig = null;

				if (hookIdField!=null) {
					String hookId = (String) hookIdField.get(hookIdField);

					hookConfig = conf.getConfig(hookId);

				}
						

				hook = (ITronHook) cons.newInstance(hookConfig);
				
				return hook;
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException | NoSuchFieldException e) {

				e.printStackTrace();
			}
			
			return null;
		}).in(Singleton.class);
	

	}

}
