package org.tronhook;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.jooby.Env;
import org.jooby.Jooby.Module;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.tronhook.api.ITronHook;
import org.tronhook.job.JobTest;

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
		

		
		
		
		
//		SchedulerFactory sf = new StdSchedulerFactory();
//		Scheduler sched = sf.getScheduler();
//		
//			Class<? extends Job> jobClass = JobTest.class;
//			
//			for (int i = 0; i < 10; i++) {
//
//				  JobBuilder job1 = JobBuilder.newJob(jobClass)
//					        .withIdentity(
//					            JobKey.jobKey(jobClass.getSimpleName()+"-"+i, jobClass.getPackage().getName())
//					        );
//		  
//				  
//					    SimpleTrigger trigger = TriggerBuilder.newTrigger()
//					        .withIdentity(
//					            TriggerKey.triggerKey(jobClass.getSimpleName()+"-"+i, jobClass.getPackage().getName())
//					        )
//					        .withSchedule(SimpleScheduleBuilder
//					                .simpleSchedule().withIntervalInSeconds(2+i).repeatForever())
//					        
//					        .build()
//					        ;
//				
//				sched.scheduleJob(job1.build(), trigger);
//				
//			}
//
//
//		
//		sched.start();	  
			    
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
