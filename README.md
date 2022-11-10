# Reproducer for java.lang.NoSuchMethodException: org.acme.RequestLogger$GeneratedServerRequestFilter$filter.<init>()

Startup fails with
```shell
2022-11-10 10:07:32,362 ERROR [io.qua.run.boo.StartupActionImpl] (Quarkus Main Thread) Error running Quarkus: java.lang.reflect.InvocationTargetException
	at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:119)
	at java.base/java.lang.reflect.Method.invoke(Method.java:577)
	at io.quarkus.runner.bootstrap.StartupActionImpl$1.run(StartupActionImpl.java:104)
	at java.base/java.lang.Thread.run(Thread.java:833)
Caused by: java.lang.ExceptionInInitializerError
	at java.base/jdk.internal.misc.Unsafe.ensureClassInitialized0(Native Method)
	at java.base/jdk.internal.misc.Unsafe.ensureClassInitialized(Unsafe.java:1160)
	at java.base/jdk.internal.reflect.MethodHandleAccessorFactory.ensureClassInitialized(MethodHandleAccessorFactory.java:300)
	at java.base/jdk.internal.reflect.MethodHandleAccessorFactory.newConstructorAccessor(MethodHandleAccessorFactory.java:103)
	at java.base/jdk.internal.reflect.ReflectionFactory.newConstructorAccessor(ReflectionFactory.java:236)
	at java.base/java.lang.reflect.Constructor.acquireConstructorAccessor(Constructor.java:546)
	at java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:496)
	at java.base/java.lang.reflect.ReflectAccess.newInstance(ReflectAccess.java:128)
	at java.base/jdk.internal.reflect.ReflectionFactory.newInstance(ReflectionFactory.java:341)
	at java.base/java.lang.Class.newInstance(Class.java:677)
	at io.quarkus.runtime.Quarkus.run(Quarkus.java:69)
	at io.quarkus.runtime.Quarkus.run(Quarkus.java:43)
	at io.quarkus.runtime.Quarkus.run(Quarkus.java:123)
	at io.quarkus.runner.GeneratedMain.main(Unknown Source)
	at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:104)
	... 3 more
Caused by: java.lang.RuntimeException: Failed to start quarkus
	at io.quarkus.runner.ApplicationImpl.<clinit>(Unknown Source)
	... 18 more
Caused by: java.lang.IllegalArgumentException: Unable to create class 'org.acme.RequestLogger$GeneratedServerRequestFilter$filter'. To fix the problem, make sure this class is a CDI bean.
	at io.quarkus.resteasy.reactive.common.runtime.ArcBeanFactory.createInstance(ArcBeanFactory.java:41)
	at org.jboss.resteasy.reactive.server.core.startup.RuntimeInterceptorDeployment.createInterceptorInstances(RuntimeInterceptorDeployment.java:155)
	at org.jboss.resteasy.reactive.server.core.startup.RuntimeInterceptorDeployment.<init>(RuntimeInterceptorDeployment.java:64)
	at org.jboss.resteasy.reactive.server.core.startup.RuntimeDeploymentManager.deploy(RuntimeDeploymentManager.java:97)
	at io.quarkus.resteasy.reactive.server.runtime.ResteasyReactiveRecorder.createDeployment(ResteasyReactiveRecorder.java:180)
	at io.quarkus.deployment.steps.ResteasyReactiveProcessor$setupDeployment208273900.deploy_0(Unknown Source)
	at io.quarkus.deployment.steps.ResteasyReactiveProcessor$setupDeployment208273900.deploy(Unknown Source)
	... 19 more
Caused by: java.lang.RuntimeException: java.lang.NoSuchMethodException: org.acme.RequestLogger$GeneratedServerRequestFilter$filter.<init>()
	at io.quarkus.arc.runtime.BeanContainerImpl$DefaultInstanceFactory.create(BeanContainerImpl.java:86)
	at io.quarkus.resteasy.reactive.common.runtime.ArcBeanFactory.createInstance(ArcBeanFactory.java:27)
	... 25 more
Caused by: java.lang.NoSuchMethodException: org.acme.RequestLogger$GeneratedServerRequestFilter$filter.<init>()
	at java.base/java.lang.Class.getConstructor0(Class.java:3617)
	at java.base/java.lang.Class.getDeclaredConstructor(Class.java:2786)
	at io.quarkus.arc.runtime.BeanContainerImpl$DefaultInstanceFactory.create(BeanContainerImpl.java:78)
	... 26 more
```